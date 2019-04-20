import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class smtpClient {
    public static void main(String[] args) throws IOException {
    final int PORT_NUMBER = 999;
    final String HOSTNAME = "192.168.43.4";
    char newEmail;	    
    boolean stop = false;
    	//Attempt to connect
	try {
		Socket sock = new Socket(HOSTNAME, PORT_NUMBER);
		Scanner input = new Scanner(System.in);
		//BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		System.out.println("\n\nConnected\n");
		
		do{	PrintWriter output = new PrintWriter(sock.getOutputStream(),true);
			//Output to server
			System.out.print("\nRecipient: ");
			String recipient = ("\n ")input.next();
			System.out.print("\nTitle: ");
			String title = ("\n ")input.next();
			System.out.print("\nMessages: ");
			String message = ("\n ")input.next();
			
		   	output.println(recipient + title + message);
			output.flush();
		   	output.close();
			//prompt stop
		   	System.out.println("\nSend another Email? (Y/N)");
			newEmail= input.next().charAt(0);
			if(newEmail == 'N' || newEmail == 'n'){
				stop = true;}
		}while(stop!=true);

		//System.out.println("\t\t"+in.readLine());
		System.out.println("\n\nDisconnected\n");

		//in.close();
		sock.close();
	    } 
	catch(Exception e) {
		e.printStackTrace();
	}
    }
}
