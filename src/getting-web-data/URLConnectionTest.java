import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            String urlName;
            if (args.length > 0) urlName = args[0];
            else  urlName = "https://horstmann.com";

            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();

            if (args.length > 2) {
                String username = args[1];
                String password = args[2];
                String input = username + ":" + password;
                Base64.Encoder encoder = Base64.getEncoder();
                String encoding = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
                connection.setRequestProperty("Authorization", "Basic " + encoding);
            }

            connection.connect();

            Map<String, List<String>> headers = connection.getHeaderFields();
            for (String key: headers.keySet()) {
                for (String header: headers.get(key)) {
                    System.out.println(key + ":" + header);
                }
            }

            System.out.println("----------------------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModified: " + connection.getLastModified());
            System.out.println("----------------------");

            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = "UTF-8";

            try(var in = new Scanner(connection.getInputStream(), encoding)) {
                for (int n = 1; in.hasNextLine() && n <= 10; n++) {
                    System.out.println(in.nextLine());
                }

                if (in.hasNextLine()) System.out.println("...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}