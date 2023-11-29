
// HostC.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HostC 
{
    public static void main(String[] args) 
    {
        while (true) {
            int userRequest = 0;
            System.out.println("[1]: Request Temperature From Host A");
            userRequest = new Scanner(System.in).nextInt();

            if (userRequest == 1) {
                // Host C as a client requesting temperature from Host A
                try {
                    // Request temperature from Host A
                    String temperatureFromHostA = requestTemperatureFromHostA();

                    System.out.println("As Host C Temperature received from Host A: " + temperatureFromHostA);
                    System.out.println("------------------------------------------");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to request temperature from Host A
    private static String requestTemperatureFromHostA() throws IOException 
    {
        Socket socket = new Socket("192.168.43.96", 12347);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Request temperature from Host A
        out.println("GET_TEMPERATURE_From_C");

        // Receive and return temperature
        return in.readLine();
    }
}