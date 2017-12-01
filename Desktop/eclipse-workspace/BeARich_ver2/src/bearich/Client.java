package bearich;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

import javax.swing.JButton;

import javax.swing.*;

public class Client {


	
	
	
	public static void main(String[] args) throws Exception {
		
		String title = "BeARich";
		
		chatClient chat_client = new chatClient(title);
		chat_client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chat_client.frame.setVisible(true);
		chat_client.chatMain();
		
//		
		
	}
	
	

}




