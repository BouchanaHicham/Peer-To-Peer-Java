// HostA.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HostA {
    public static void main(String[] args) 
    {
        

        System.out.println("[1]: Request Temp From Host B");
        // Host A as a client requesting temperature from Host B
        int userRequest = new Scanner(System.in).nextInt();
        if (userRequest == 1)
        {
            try {
                // Introduce a delay before attempting to connect to HostB
                Thread.sleep(1000); // 1 second delay (adjust as needed)

                Socket socket = new Socket("192.168.1.9", 12345);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Request temperature
                out.println("GET_TEMPERATURE");

                // Receive and print temperature
                String temperature = in.readLine();
                System.out.println("Temperature received from Host B: " + temperature);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (true) 
        {
            // Host A as a server providing humidity for Host B
            try (ServerSocket serverSocket = new ServerSocket(12347);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                // Wait for humidity request from Host B
                String request = in.readLine();
                if ("GET_HUMIDITY".equals(request)) {
                    // Provide humidity
                    out.println("50%"); // Replace with actual humidity value
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
