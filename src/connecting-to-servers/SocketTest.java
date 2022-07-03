import java.io.*;
import java.net.*;
import java.util.*;

public class SocketTest {
    private final static String HOST_NAME = "time-a.nist.gov";

    public static void main(String[] args) throws IOException {
        // Creating and using a socket connection
        try(
                Socket s = new Socket(HOST_NAME, 13);
                Scanner in = new Scanner(s.getInputStream(), "UTF-8")
        ) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }

        // Create socket without connection
        Socket socket = new Socket();
        // Using the socket to connect and setting a timeout connection
        socket.connect(new InetSocketAddress(HOST_NAME, 13), 10000);
    }
}