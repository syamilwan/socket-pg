import java.net.*;
import java.io.*;

public class icmp{
	public static void main(String[] args){
		try{
			String ip = "192.168.43.33";
			InetAddress inet = InetAddress.getByName(ip);
			System.out.println("ping " + ip);

			if(inet.isReachable(500))
				System.out.println(ip+" available");
			else
				System.out.println(ip+" not available");

		}catch(IOException i){
			System.out.println(i);

		}
	}
}
