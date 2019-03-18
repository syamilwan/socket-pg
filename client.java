import java.io.*;

import java.net.*;



public class client {

    public static void main(String[] args) throws IOException {

    final int PORT_NUMBER = 8080;

    final String HOSTNAME = "192.168.159.129";



    //Attempt to connect

    try {

        Socket sock = new Socket(HOSTNAME, PORT_NUMBER);

            PrintWriter out = new PrintWriter(sock.getOutputStream(), 
true);

        //Output

        out.println("\n\t\tBonjour");

        out.flush();



        out.close();

        sock.close();

    } catch(Exception e) {

        e.printStackTrace();

    }

    }

}
