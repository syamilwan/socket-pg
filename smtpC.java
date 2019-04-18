import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class client {

    public static void main(String[] args) throws IOException {
    final int PORT_NUMBER = 25
    final String HOSTNAME = "192.168.159.129";
    
    //Attempt to connect
    try {
        Socket sock = new Socket(HOSTNAME, PORT_NUMBER);
        PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	
	      System.out.println("\n\t***Connected to socket\n\n");
        
	      //Output to server
        out.println("\nEnter email address");
	      out.flush();
        
	      System.out.println("\t\t"+in.readLine());
	      System.out.println("\n\n\t***Disconnected from socket\n");
        
        out.close();
	      in.close();
        sock.close();
        
        } 
    catch(Exception e) {
        e.printStackTrace();
        }
    }
}
