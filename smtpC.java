import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class smtpC {

    public static void main(String[] args) throws IOException {
    final int PORT_NUMBER = 25;
    final String HOSTNAME = "192.168.59.71";
    
    //Attempt to connect
    try {
        Socket sock = new Socket(HOSTNAME, PORT_NUMBER);
        PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
	Scanner input = new Scanner(System.in);
        //BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	
	System.out.println("\n\tSession Start\n\n");
        
	//Output to server
        System.out.println("\nRecipient: ");
	out.print(input.next());
	System.out.println("\nTitle: ");
	out.print(input.next());
	System.out.println("\nMessages: ");
	out.print(input.next());
	out.flush();
        
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
