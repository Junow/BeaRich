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
import org.jfree.ui.RefineryUtilities;

import javax.swing.JButton;

import javax.swing.*;

public class Client {



	public static void main(String[] args) throws Exception {
		Login log = new Login();
		
		String title = "BeARich";
		String id="";
		
		while(true) {
			id = log.getID();
			System.out.println("wating....");
			if(id.compareTo("")!=0) {
				System.out.println("break");
				break;
			}
		}
		
		
		chatClient chat_client = new chatClient(title);
	
		chat_client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chat_client.frame.setVisible(true);
		RefineryUtilities.centerFrameOnScreen(chat_client.frame);
		chat_client.frame.pack();
		chat_client.chatMain(id);
	}

}
