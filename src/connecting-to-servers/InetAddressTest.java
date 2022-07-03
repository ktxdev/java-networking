import java.io.*;
import java.net.*;
import java.util.*;

public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            // Getting IP address for host
            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress address: addresses) {
                byte[] addressBytes = address.getAddress();
                System.out.println("Address Bytes for " + address.getHostAddress());
                for(int i = 0; i < addressBytes.length; i++) {
                    if (i > 0)
                        System.out.print(".");
                    System.out.print(addressBytes[i]);
                }
                System.out.println();
            }
        } else {
            // Getting localhost
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
        }
    }
}