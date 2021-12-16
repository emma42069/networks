import java.io.*;
import java.net.*;
public class SocketHTTPClient {
    public static void main(String[] args) {
        String hostName = "www.google.com";
        int portNumber = 80;
        try {
            Socket socket = new Socket(hostName,
                portNumber);
            PrintWriter out =
                new PrintWriter(socket.getOutputStream(),
                    true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out.println("GET / HTTP/1.1\nHost: www.martinbroadhurst.com\
                n\ n ");
                String inputLine;
                while ((inputLine = in .readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
            catch (UnknownHostException e) {
                System.err.println("Don't know about host " +
                    hostName);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
                System.exit(1);
            }
        }
    }

    ALGORITHM :
    Steps:
    1. Create a URL object and pass url as string to download the webpage.
    URL example = new URL(pass url of webpage you want to download)
    2. Create Buffered Reader object and pass openStream(). Method of URL in Input Stream object.
    3. Create a string object to read each line one by one from stream.
    4. Write each line in html file where webpage will be downloaded.
    5. Close all objects.
    6. Catch exceptions if url failed to download.
    