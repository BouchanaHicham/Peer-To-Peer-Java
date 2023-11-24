// HostB.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HostB {
    public static void main(String[] args) {
        while (true) {
            
        
            // Host B as a server providing temperature for Host A
            try (ServerSocket serverSocket = new ServerSocket(12345);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                // Wait for temperature request from Host A
                String request = in.readLine();
                if ("GET_TEMPERATURE".equals(request)) {
                    // Provide temperature
                    out.println("25°C"); // Replace with actual temperature value
                }
                // Host A as a client requesting temperature from Host B
            } catch (IOException e) {
                e.printStackTrace();
            }

        int userRequest = 0;
        System.out.println("[1]: Request Humidity From Host A");
        userRequest = new Scanner(System.in).nextInt();

        if (userRequest == 1 )
        {
            // Host B as a client requesting humidity from Host A
            try (Socket socket = new Socket("192.168.1.9", 12347);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                // Request humidity
                out.println("GET_HUMIDITY");

                // Receive and print humidity
                String humidity = in.readLine();
                System.out.println("Humidity received from Host A: " + humidity);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

    }
}
