import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadedServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(9090)) {
            int i = 1;
            while(true) {
                Socket incomming = serverSocket.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedServerRequestHandler(incomming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadedServerRequestHandler implements Runnable {
    private Socket incoming;

    public ThreadedServerRequestHandler(Socket incoming) {
        this.incoming = incoming;
    }

    public void run() {
        try(var inStream = incoming.getInputStream();
            var outStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
            out.println("Hello! Enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE"))
                    done = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}