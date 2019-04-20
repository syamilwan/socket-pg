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
		PrintWriter output = new PrintWriter(sock.getOutputStream(),true);
		//BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		System.out.println("\n\nConnected\n");
		
		//while(stop==false){	**removed loop**
			//prompt user input
			System.out.print("\nRecipient: ");
			String recipient = (";"+input.next());
			System.out.print("\nTitle: ");
			String title = (";"+input.next());
			System.out.print("\nMessages: ");
			String message = (";"+input.next());
			
			//Output to server
		   	output.println(recipient + title + message);
			output.flush();
		   			   
			//prompt user stop loop
		   	/*System.out.println("\nSend another Email? (Y/N)");
			newEmail= input.next().charAt(0);
			if(newEmail == 'N' || newEmail == 'n'){
				stop = true;
				
			}*/ 
		//}

		System.out.println("\n\nDisconnected\n");
		output.close();
		sock.close();
		
	    } 
	catch(Exception e) {
		e.printStackTrace();
	}
    }
}
