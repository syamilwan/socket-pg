import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class smtpClient {

    public static void main(String[] args) throws IOException {
    final int PORT_NUMBER = 999;
    final String HOSTNAME = "192.168.59.72";
    boolean stop = false;
    //Attempt to connect
    try {
        Socket sock = new Socket(HOSTNAME, PORT_NUMBER);
        PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
	Scanner input = new Scanner(System.in);
        //BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	
	while(stop!=true){
		System.out.println("\n\tNew Email\n\n");
		
		//Output to server
		System.out.println("\nRecipient: ");
		out.print("\nRecipient: "+input.next());
		System.out.println("\nTitle: ");
		out.print("\nTitle: "+input.next());
		System.out.println("\nMessages: ");
		out.print("\nMessages: "+input.next());
		out.flush();
		}
	    
	//System.out.println("\t\t"+in.readLine());
	System.out.println("\n\n\tSession End\n");
	        
        out.close();
	//in.close();
        sock.close();
        
        } 
    catch(Exception e) {
        e.printStackTrace();
        }
    }
}
