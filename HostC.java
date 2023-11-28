// HostC.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HostC {
    public static void main(String[] args) {
        while (true) {
            int userRequest = 0;
            System.out.println("[1]: Request Temperature From Host B");
            userRequest = new Scanner(System.in).nextInt();

            if (userRequest == 1) {
                // Host C as a client requesting temperature from Host A
                try {
                    Socket socket = new Socket("192.168.1.9", 12345); 
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Request temperature
                    out.println("GET_TEMPERATURE");

                    // Receive and print temperature
                    String temperature = in.readLine();
                    System.out.println("As Host C Temperature received from Host B: " + temperature);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
