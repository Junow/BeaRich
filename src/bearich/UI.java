package bearich;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface UI {

	void UIStart();

	String getServerAddress();
	String getName();
	String NULL();
	String ALREADY() ;
	
	void NameAccepted(String line);
	void Message(String line);
	void Whisper(String line);
	void No(String line);
	void You(String line);
	void Entrance(String line);
	void Null(String line);
	void Already(String line);
	void Out(String line);
	
	
}
