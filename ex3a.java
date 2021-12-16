// Server Program:

import java.net.*;
import java.io.*;
public class Server {
    public static void main(String args[]) throws
    Exception {
        ServerSocket srs = new ServerSocket(1234);
        System.out.println("Server is running...");
        Socket ss = srs.accept();
        System.out.println("connection establised");
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
        while (true) {
            //System.out.println("server repeat as long as client not send null ");
            String s2, s3;
            while ((s2 = br.readLine()) != null) {
                System.out.println("Client said : " + s2);
                System.out.println("Enter text ");
                s3 = kb.readLine();
                //System.out.println("Answer send to client machine"); 
                dos.writeBytes(s3 + "\n");
            }
            System.out.println("Terminated..");
            ss.close();
            srs.close();
            dos.close();
            kb.close();
            System.exit(0);
        }
    }
}



//  Client Program:

import java.net.*;
import java.io.*;
public class Client {
    public static void main(String args[]) throws
    Exception {
        Socket cs = new Socket("localhost", 1234);
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        DataOutputStream dos = new
        DataOutputStream(cs.getOutputStream());
        System.out.println(" Enter text..");
        System.out.println(" if client 'quit' type exit");
        String s1, s4 = null;
        while (!(s1 = kb.readLine()).equals("exit")) {
            //System.out.println(" data send to server machine"); 
            dos.writeBytes(s1 + "\n");
            s4 = br.readLine();
            //System.out.println(" data receive from server machine ");
            System.out.println("Server said : " + s4);
            System.out.println("Enter text ");
        }
        System.out.println("Terminated..");
        cs.close();
        dos.close();
        kb.close();
    }
}

ALGORITHM:
1. Start the program
2. Establish a socket connection, The java.net.Socket class represents a Socket
3. Input streams and output streams are used to communicate over a socket connection for both
input and output the data.
4. Server makes a new Socket to communicate with the client.
5. The accept() method blocks, until a client connects to the server.
6. A ServerSocket which waits for the client requests (when a client makes a
new Socket())
7. getOutputStream() method is used to send the output through the socket.
8. Close the connection by closing the socket and the input stream.
