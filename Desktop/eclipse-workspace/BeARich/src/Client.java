import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import javax.swing.JButton;

import javax.swing.*;

/**
 * A simple Swing-based client for the chat server.  Graphically
 * it is a frame with a text field for entering messages and a
 * textarea to see the whole dialog.
 *
 * The client follows the Chat Protocol which is as follows.
 * When the server sends "SUBMITNAME" the client replies with the
 * desired screen name.  The server will keep sending "SUBMITNAME"
 * requests as long as the client submits screen names that are
 * already in use.  When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start
 * sending the server arbitrary strings to be broadcast to all
 * chatters connected to the server.  When the server sends a
 * line beginning with "MESSAGE " then all characters following
 * this string should be displayed in its message area.
 */
public class Client implements ActionListener{

	private static TimeSeries series;
	
	BufferedReader in;
	PrintWriter out;
	JFrame frame = new JFrame("rich");
	JTextField textField = new JTextField(28);
	JTextArea messageArea = new JTextArea(8,40);
	static ChartPanel chart;
	static final Show_Graph show_graph = new Show_Graph("BeARich");
	
	/**
	 * Constructs the client by laying out the GUI and registering a
	 * listener with the textfield so that pressing Return in the
	 * listener sends the textfield contents to the server.  Note
	 * however that the textfield is initially NOT editable, and
	 * only becomes editable AFTER the client receives the NAMEACCEPTED
	 * message from the server.
	 */
	public Client() {
		
		// Layout GUI
		frame.setPreferredSize(new Dimension(1400,800)); // ok
		textField.setEditable(false);
		textField.setBackground(Color.white);
		
		messageArea.setEditable(false);
		messageArea.setBackground(Color.white);
		
		chart = new ChartPanel(show_graph.get_chart()); // get a chart from Show_Graph class. //ok
		chart.setBackground(Color.white);
		
		JPanel chartPanel = new JPanel(); chartPanel.setBackground(Color.white);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		JButton btn1 = new JButton();
		buttonPanel.add(btn1);
		chartPanel.add(chart);
		chart.setPreferredSize(new Dimension(980,750)); // center of frame,,,,, == chart size
		chart.setBackground(Color.blue);
		
		JPanel chatPanel = new JPanel(); chatPanel.setBackground(Color.white);
		JPanel scrollPanel = new JPanel(); scrollPanel.setBackground(Color.white);
		JPanel textPanel = new JPanel(); textPanel.setBackground(Color.white);
		chatPanel.setLayout(new BorderLayout());

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		chatPanel.setPreferredSize(new Dimension(350,800)); // chatPanel total size
		JScrollPane scrollPane = new JScrollPane(messageArea);
		
		scrollPanel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(340,800)); // chatting window container size
		scrollPanel.setPreferredSize(new Dimension(340,750)); // chatting window size
		textPanel.setPreferredSize(new Dimension(300,30)); // textfeild size
		textPanel.add(textField);
		

		chatPanel.add(scrollPanel,"North");
		chatPanel.add(textPanel,"South");
		
		frame.getContentPane().add(chatPanel, "East");
		
		frame.getContentPane().add(chartPanel,"Center");
		
		frame.getContentPane().add(buttonPanel, "West");
		
		frame.setPreferredSize(new Dimension(1400,800)); // ok
//		frame.setVisible(false);
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Add Listeners
		textField.addActionListener(new ActionListener() {
			/**
			 * Responds to pressing the enter key in the textfield by sending
			 * the contents of the text field to the server.    Then clear
			 * the text area in preparation for the next message.
			 */
			public void actionPerformed(ActionEvent e) {
				out.println(textField.getText());
				textField.setText("");
			}
		});
	}

	/**
	 * Prompt for and return the address of the server.
	 */
	private String getServerAddress() {
		return JOptionPane.showInputDialog(
				frame,
				"Enter IP Address of the Server:",
				"Welcome to the Chatter",
				JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Prompt for and return the desired screen name.
	 */
	private String getName() {
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name:",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}
	private String NULL() {
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name(re-select the name):",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}
	/*
	 *  prompt for the user name is already used.
	 */
	private String ALREADY() {
		return JOptionPane.showInputDialog(
				frame,
				"Choose a screen name(already in use):",
				"Screen name selection",
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	public void run() throws IOException {
		
		
		
		String myName="";
		// Make connection and initialize streams
		String serverAddress = getServerAddress();
		//create socket.
		Socket socket = new Socket(serverAddress, 9001);
		//incoming
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//outgoing
		out = new PrintWriter(socket.getOutputStream(), true);
		// Process all messages from server, according to the protocol.
//		TimeSeries series;
		series = show_graph.getSeries(); ///////////////////////////////////////////////////////////////////
		
		String line="";

//		String before="";
		while (true) {

			line = in.readLine();
//			System.out.println(line);

			if (line.startsWith("SUBMITNAME")) {
				out.println(getName());
			} 
			else if (line.startsWith("NAMEACCEPTED")) { // 액셉티드 받고나서 그래프랑 텍스트필드 표현.
				myName = line.substring(12);
				frame.setVisible(true);
				frame.pack();
				textField.setEditable(true);
				
				//				System.out.println("test");
			} 
			else if (line.startsWith("MESSAGE")) {
				messageArea.append(line.substring(8) + "\n");
				//this code makes automatic scrolling.
				messageArea.setCaretPosition(messageArea.getDocument().getLength());
			} 
			//when whisper message is received.
			else if(line.startsWith("Whisper")) {
				messageArea.append(line.substring(7) +"\n");
				messageArea.setCaretPosition(messageArea.getDocument().getLength());
				messageArea.setDisabledTextColor(Color.red);
			}
			//when the person who user are talking to is not exist.
			else if(line.startsWith("NO")) {
				messageArea.append(line.substring(2) + "\n");
				messageArea.setCaretPosition(messageArea.getDocument().getLength());
			}
			//when the user attempts to whisper to itself.
			else if(line.startsWith("You")) {
				messageArea.append(line + "\n");
				messageArea.setCaretPosition(messageArea.getDocument().getLength());
			}
			//when a new user logged in.
			else if(line.startsWith("ENTRANCE")) {
				messageArea.append(line.substring(8) + " logged in! \n");
			}
			else if(line.startsWith("NULL")){
				while(true) {
					line = in.readLine();
					out.println(NULL());
					if(!(line.startsWith("NULL"))) {
						break;
					}
				}
			}
			//when the user name is already used. server request a new name that is not exist.
			else if(line.startsWith("ALREADY")) {
				while(true) {
					line = in.readLine();
					out.println(ALREADY());
					if(!(line.startsWith("ALREADY"))) {
						break;
					}
				}
			}
			//when a user logged out.
			else if(line.startsWith("OUT")) {
				messageArea.append(line.substring(3) + " logged out" + "\n");
				messageArea.setCaretPosition(messageArea.getDocument().getLength());
			}
			else if(line.startsWith("VIRTUAL")) {
				double price=Double.parseDouble(line.substring(7));
				System.out.println(Double.parseDouble(line.substring(7))-1000000);
//				show_graph.setdata(price);
//				show_graph.setdata(10000);
				show_graph.setdata(price);
//				series.add(new Millisecond(),price-1000000); // get the price
//				chart = new ChartPanel(show_graph.get_chart());
			}
			else if(line.startsWith("HALFVAL")) {
				double price = Double.parseDouble(line.substring(4));
				System.out.println(Double.parseDouble(line.substring(4))-1000000);
//				show_graph.setdata(price);
//				show_graph.setdata(10000);
				show_graph.setdata(price); // get the price
//				series.add(new Millisecond(), price-1000000);
//				chart = new ChartPanel(show_graph.get_chart());
			}
		}
	}

	
	/**
	 * Runs the client as an application with a closeable frame.
	 */
	
	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setVisible(true);
		client.frame.pack();
		client.run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}





