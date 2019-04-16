import java.io.*;
import java.net.*;
import java.util.Scanner; 

public class smtpClient {
    public static void main(String[] args) throws IOException {
    final int PORT_NUMBER = 999;
    final String HOSTNAME = "192.168.59.72";
    char newEmail;	    
    boolean stop = false;
    	//Attempt to connect
	try {
		Socket sock = new Socket(HOSTNAME, PORT_NUMBER);
		PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
		Scanner input = new Scanner(System.in);
		//BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		System.out.println("\n\nConnected\n");
		do{	System.out.println("\n\tSend New Email? (Y/N)\n\n");
			newEmail= input.next().charAt(0);
			if(newEmail == 'N' || newEmail == 'n'){
				stop = true;}
			//Output to server
			System.out.print("\nRecipient: ");
			out.println(input.next());
			System.out.print("\nTitle: ");
			out.println(input.next());
			System.out.print("\nMessages: ");
			out.println(input.next());
			out.flush();
		}while(stop!=true);

		//System.out.println("\t\t"+in.readLine());
		System.out.println("\n\nDisconnected\n");

		out.close();
		//in.close();
		sock.close();
	    } 
	catch(Exception e) {
		e.printStackTrace();
	}
    }
}
