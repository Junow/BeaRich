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


/**
 * A multithreaded chat room server.  When a client connects the
 * server requests a screen name by sending the client the
 * text "SUBMITNAME", and keeps requesting a name until
 * a unique one is received.  After a client submits a unique
 * name, the server acknowledges with "NAMEACCEPTED".  Then
 * all messages from that client will be broadcast to all other
 * clients that have submitted a unique screen name.  The
 * broadcast messages are prefixed with "MESSAGE ".
 *
 * Because this is just a teaching example to illustrate a simple
 * chat server, there are a few features that have been left out.
 * Two are very useful and belong in production code:
 *
 *     1. The protocol should be enhanced so that the client can
 *        send clean disconnect messages to the server.
 *
 *     2. The server should do some logging.
 */
public class Server {


	/**
	 * The port that the server listens on.
	 */
	private static final int PORT = 9001;

	/**
	 * The set of all names of clients in the chat room.  Maintained
	 * so that we can check that new clients are not registering name
	 * already in use.
	 */
	private static HashMap<String,PrintWriter> names = new HashMap<String,PrintWriter>();

	/**
	 * The set of all the print writers for all the clients.  This
	 * set is kept so we can easily broadcast messages.
	 */
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	
	/**
	 * The appplication main method, which just listens on a port and
	 * spawns handler threads.
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	/**
	 * A handler thread class.  Handlers are spawned from the listening
	 * loop and are responsible for a dealing with a single client
	 * and broadcasting its messages.
	 */
	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		/**
		 * Constructs a handler thread, squirreling away the socket.
		 * All the interesting work is done in the run method.
		 */
		public Handler(Socket socket) {
			this.socket = socket;
		}

		/**
		 * Services this thread's client by repeatedly requesting a
		 * screen name until a unique one has been submitted, then
		 * acknowledges the name and registers the output stream for
		 * the client in a global set, then repeatedly gets inputs and
		 * broadcasts them.
		 */
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
}
