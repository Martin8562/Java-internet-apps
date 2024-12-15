import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT_NUMBER = 12345;

        try {
            // Connect to the server
            Socket socket = new Socket(SERVER_ADDRESS, PORT_NUMBER);
            System.out.println("Connected to server: " + socket);

            // Setup input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            out.println("Hello, Server!");

            // Read and print the server's response
            String serverResponse = in.readLine();
            System.out.println("Server response: " + serverResponse);

            // Close the resources
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
