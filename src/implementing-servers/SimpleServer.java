import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {

        try (var serverSocket = new ServerSocket(9090); // Establish a server socket
             var incoming = serverSocket.accept(); // Wait for client connection
             var inStream = incoming.getInputStream();
             var outStream = incoming.getOutputStream();
             var in = new Scanner(inStream, "UTF-8")) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
            out.println("Hello! Enter BYE to exit");

            // Echo client input
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE"))
                    done = true;
            }
        }
    }
}