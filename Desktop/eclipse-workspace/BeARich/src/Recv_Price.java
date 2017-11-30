import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Recv_Price extends Thread{



//	private String serverAddress="";
	private Socket socket;
	BufferedReader in;
	PrintWriter out;

	Recv_Price(Socket socket){
//		this.serverAddress=serverAddr;
		this.socket=socket;
		
		try {
//			socket = new Socket(serverAddress, 9001);

			//incoming
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//outgoing
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch(IOException e) {
			System.out.print(e.getStackTrace());
		}
		
	}
	public void run(){

		//		serverAddress = getServerAddress();
		//create socket.
		
		String line="";

		try {
			while(true) {
				line = in.readLine();

				if(line.startsWith("VIRTUAL")) {
					double price=Double.parseDouble(line.substring(7));
					//					series.add(new Millisecond(),price); // get the price
					System.out.println(price);
				}
				else if(line.startsWith("HALFVAL")) {
					double price = Double.parseDouble(line.substring(4));
					//					series.add(new Millisecond(), price); // get the price
					//					series.add(new Millisecond(), 1);
					System.out.println(price);
				}
			}

		}
		catch(IOException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
