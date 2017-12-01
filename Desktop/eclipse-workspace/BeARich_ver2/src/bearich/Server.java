package bearich;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.*;


public class Server {

	private static final int PORT = 9001;

	private static HashMap<String,PrintWriter> names = new HashMap<String,PrintWriter>();

	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	public static void main(String[] args) throws Exception {
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new ChatThread(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class ChatThread extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		public ChatThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			String dest="";
			try {

				// Create character streams for the socket.
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// Request a name from this client.  Keep requesting until
				// a name is submitted that is not already used.  Note that
				// checking for the existence of a name and adding the name
				// must be done while locking the set of names.
				while (true) {
					out.println("SUBMITNAME");
					name = in.readLine();
					if (name == null) {
						out.println("NULL");
						break;
					}

					// check for duplication of name.
					synchronized (names) {
						if (!names.containsKey(name)) {
							//the outputstream is stored in hash map with each name
							names.put(name,out);
							break;
						}
						else {
							out.println("ALREADY");
						}
					}
				}

				// Now that a successful name has been chosen, add the
				// socket's print writer to the set of all writers so
				// this client can receive broadcast messages.
				out.println("NAMEACCEPTED" + name);
				for (PrintWriter writer : writers) {
					writer.println("ENTRANCE" + name);
				}
				writers.add(out);

				
				
				///////// start PriceThread //////////////
				
				
				PriceThread task1 = new PriceThread(socket);
				task1 = new PriceThread(socket);
				task1.start();
				
				
				// Accept messages from this client and broadcast them.
				// Ignore other clients that cannot be broadcasted to.
				while (true) {
					String input = in.readLine();
					if (input == null) {
						return;
					}
					if(input.equalsIgnoreCase("/exit")) {
						// This client is going down!  Remove its name and its print
//						// writer from the sets, and close its socket.
						if (name != null) {
							for(PrintWriter writer : writers) {
								writer.println("OUT" + name);
							}
							names.remove(name);
						}
						if (out != null) {
							writers.remove(out);
						}
						try {
							socket.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
					// '/w' is whisper command.
					else if(input.startsWith("w/")) {
						dest = input.substring(2,input.indexOf(' '));
						String msg = input.substring(input.indexOf(' ')+1 );
						
						//if -> the name is already used. reject it and request another name.
						if (!names.containsKey(dest)) {
							names.get(name).println("NO" + dest + " is not exist");
						}
						//else -> send the message to destination user and the user who spoke it.
						else {
							// if the user attempts to whisper to itself.
							if(name.equals(dest)) {
								names.get(dest).println("You can't whisper to yourself choose another user");
							}
							else {
								names.get(name).println("Whisper" + "<to "+dest + "> : " + msg);
								names.get(dest).println("Whisper" + "<from "+name+"> : " + msg);
							}
						}
					}
					
					else {
						for (PrintWriter writer : writers) {
							writer.println("MESSAGE " + name + " : " + input);
						}
					}
				}
			} 
			catch (IOException e) {
				System.out.println(e.getStackTrace());
			} 
		}
	}
	
	public static class PriceThread extends Thread {
		private Socket socket;
		
		public PriceThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			long time1 = System.currentTimeMillis (); 
			long time2 = System.currentTimeMillis ();
			
			double btcPrice=0;
			double ethPrice=0;
			double dasPrice=0;
			double ltcPrice=0;
			double etcPrice=0;
			double xrpPrice=0;
			double bchPrice=0;
			double xmrPrice=0;
			double zecPrice=0;
			double qtuPrice=0;
			
			getPrice GP = new getPrice();
			
			while(true) {
				time2 = System.currentTimeMillis ();
				//5초에 한번씩만 업데이트할려고 함 time1 time2 차이
				if(Math.abs((time2-time1)/1000.0) >= 3) {
					time1 = System.currentTimeMillis (); 
//					btcPrice = GP.get_price(GP.connect("BTC"));
//					
//					for (PrintWriter writer : writers) {
//						writer.println("priceBTC"+btcPrice);
//					}
					sendPrice("BTC");
					sendPrice("ETH");
					sendPrice("DASH");
					sendPrice("LTC");
					sendPrice("ETC");
					sendPrice("XRP");
					sendPrice("BCH");
					sendPrice("XMR");
					sendPrice("ZEC");
					sendPrice("QTUM");
				}
			}
		}
		
		private void sendPrice(String currency) {
			getPrice GP = new getPrice();
			
			double price = GP.get_price(GP.connect(currency));
			
			for (PrintWriter writer : writers) {
				writer.println("price"+ currency.substring(0,3) +price);
			}
		}
		
		
	}
}