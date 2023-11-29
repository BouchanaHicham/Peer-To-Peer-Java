// HostA.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HostA {
    private static String temperatureFromHostB = "N/A"; // Variable to store the temperature

    public static void main(String[] args) {
        new Thread(() -> startClient()).start();
        new Thread(() -> startServer()).start();
    }

    private static void startClient() {
        while (true) {
            System.out.println("[1]: Request Temp From Host B");
            int userRequest = new Scanner(System.in).nextInt();
            if (userRequest == 1) {
                try {
                    Socket socket = new Socket("192.168.43.96", 12345);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // Request temperature
                    out.println("GET_TEMPERATURE");

                    // Receive and store temperature
                    temperatureFromHostB = in.readLine();
                    System.out.println("Temperature received from Host B: " + temperatureFromHostB);
                    System.out.println("------------------------------------------");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void startServer() {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12347);
                 Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                // Wait for humidity request
                String request = in.readLine();
                if ("GET_HUMIDITY".equals(request)) 
                {
                    // Provide humidity
                    out.println("50%");
                }
                
                else if ("GET_TEMPERATURE_From_C".equals(request)) 
                {
                    // Provide humidity
                    out.println(getTemperatureFromHostB());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    public static String getTemperatureFromHostB() {
        return temperatureFromHostB;
    }
}