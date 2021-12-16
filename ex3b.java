// Client Program:
import java.net.*;
import java.io.*;
public class Client1 {
    public static void main(String args[]) throws Exception {
        Socket cs = new Socket("localhost", 1234);
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
        System.out.println(" Enter text..");
        System.out.println(" if client 'quit' type exit");
        String s1, s4 = null;
        while (!(s1 = kb.readLine()).equals("exit")) {
            //System.out.println(" data send to server machine");
            dos.writeBytes(s1 + "\n");
            s4 = br.readLine();
            //System.out.println(" data receive from server machine");
            System.out.println("Server said : " + s4);
            System.out.println("Enter text ");
        }
        System.out.println("Terminated..");
        cs.close();
        dos.close();
        kb.close();
    }
}

// Server Program:

import java.net.*;
import java.io.*;
public class Server1 {
    public static void main(String args[]) throws Exception {
        ServerSocket srs = new ServerSocket(1234);
        System.out.println("Server is running...");
        Socket ss = srs.accept();
        System.out.println("connection establised");
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        DataOutputStream dos = new DataOutputStream(ss.getOutputStream());
        while (true) {
            //System.out.println("server repeat as long as client not send null");
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

ALGORITHM:
1.In any Client/Server Application, run the server before the client, because the server keeps
waiting for the client to be connected.
2. Server keeps listening for the client on an assigned IP & Port
3. For establishing connection client must know the IP & Port of the server.
4.When we start Client Application, It creates a connection to the server.
5. After the Successful connection Client & Server Applications can send & receive messages