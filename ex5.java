// Program for Address Resolutuion Protocol (ARP) using TCP
// Client Program:
import java.io.*;
import java.net.*;
import java.util.*;
class Clientarp {
    public static void main(String args[]) {
        Try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Socket clsct = new Socket("127.0.0.1", 139);
            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
            System.out.println("Enter the Logical address(IP):");
            String
            str1 = in .readLine();
            dout.writeBytes(str1 + '\n');
            String str = din.readLine();
            System.out.println("The Physical Address is: " + str);
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Server Program:
import java.io.*;
import java.net.*;
import java.util.*;
class Serverarp {
    public static void main(String args[]) {
        try {
            ServerSocket obj = new ServerSocket(139);
            Socket obj1 = obj.accept();
            while (true) {
                DataInputStream din = new DataInputStream(obj1.getInputStream());
                DataOutputStream dout = new DataOutputStream(obj1.getOutputStream());
                String str = din.readLine();
                String
                ip[] = {
                    "165.165.80.80",
                    "165.165.79.1"
                };
                String mac[] = {
                    "6A:08:AA:C2",
                    "8A:BC:E3:FA"
                };
                for (int i = 0; i < ip.length; i++) {
                    if (str.equals(ip[i])) {
                        dout.writeBytes(mac[i] + '\n');
                        break;
                    }
                }
                obj.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // Program for Reverse Address Resolutuion Protocol (RARP) using UDP
    // Client Program :
import java.io.*;
import java.net.*;
import java.util.*;
class Clientrarp12 {
    public static void main(String args[]) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new
            byte[1024];
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the Physical address (MAC):");
            27
        }
        String str = in .readLine();
        sendbyte = str.getBytes();
        DatagramPacket sender = new
        DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
        client.send(sender);
        DatagramPacket receiver = new
        DatagramPacket(receivebyte, receivebyte.length);
        client.receive(receiver);
        String s = new String(receiver.getData());
        System.out.println("The Logical Address is(IP): " + s.trim());
        client.close();
    }
    catch (Exception e) {
        System.out.println(e);
    }
}
}

// Server Program:
import java.io.*;
import java.net.*;
import java.util.*;
class Serverrarp12 {
    public static void main(String args[]) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            while (true) {
                byte[] sendbyte = new byte[1024];
                byte[]
                receivebyte = new byte[1024];
                DatagramPacket
                receiver = new
                DatagramPacket(receivebyte, receivebyte.length);
                server.receive(receiver);
                String str = new String(receiver.getData());
                String s = str.trim();
                //System.out.println(s); String
                ip[] = {
                    "165.165.80.80",
                    "165.165.79.1"
                };
                String
                mac[] = {
                    "6A:08:AA:C2",
                    "8A:BC:E3:FA"
                };
                for (int i = 0; i < ip.length; i++) {
                    if (s.equals(mac[i])) {
                        sendbyte = ip[i].getBytes();
                        DatagramPacket sender = new
                        DatagramPacket(sendbyte, sendbyte.length, addr, port);
                        server.send(sender);
                        break;
                    }
                }
                break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

server
1. Create a server socket and bind it to port.
2. Listen for new connection and when a connection arrives, accept it.
3. Send servers date and time to the client.
4. Read clients IP address sent by the client.
5. Display the client details.
6. Repeat steps 2-5 until the server is terminated.
7. Close all streams.
8. Close the server socket.
9. Stop.
Client
1. Create a client socket and connect it to the server‟ s port number.
2. Retrieve its own IP address using built-in function.
3. Send its address to the server.
4. Display the date & time sent by the server.
5. Close the input and output streams.
6. Close the client socket.
7. Stop