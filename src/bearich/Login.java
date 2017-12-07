package bearich;
//import java.awt.Dimension;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.jfree.ui.RefineryUtilities;

public class Login extends JFrame {
	//	public static void main(String[] args) {
	//		new Login();
	//	}
	String name="";
	String id="";
	public Login(){


		Container contentPane;
		JPanel pane;
		JButton btnLogin;
		JTextField userText;
		JLabel userName;
		JLabel userPassword;
		JPasswordField passText;
		JButton btnSignup;



		contentPane = this.getContentPane();
		setTitle("BeARich"); //처음에 패널을 만
		this.setPreferredSize(new Dimension(480,270));
		contentPane.setPreferredSize(new Dimension(300,300));
		contentPane.setVisible(true);

		//		setBounds(100,100,500,500); //panel 크기조
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 닫기버튼 눌렀을떄 어떻게 할 것인가.

		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(new Color(50,50,50));

		userName = new JLabel("ID");
		userPassword = new JLabel("Password");
		userText = new JTextField(10);
		passText = new JPasswordField(10); 
		btnSignup = new JButton("Sign Up");
		btnLogin = new JButton("Login"); btnLogin.setMnemonic('\n');

		//set color.
		userName.setForeground(Color.white);
		userPassword.setForeground(Color.white);
		userText.setBackground(new Color(220,220,220));
		passText.setBackground(new Color(220,220,220));
		btnSignup.setBackground(new Color(220,220,220));
		btnLogin.setBackground(new Color(220,220,220));
		btnSignup.setForeground(Color.black);
		btnLogin.setForeground(Color.black);

		//add components.
		pane.add(userName);
		pane.add(userPassword);
		pane.add(userText);
		pane.add(passText);
		pane.add(btnLogin);
		pane.add(btnSignup);


		// set layout
		userName.setBounds(90,50,80,25);
		userPassword.setBounds(90,80,80,25);
		userText.setBounds(180,50,160,25);
		passText.setBounds(180,80,160,25);
		btnLogin.setBounds(175,120,85,25);
		btnSignup.setBounds(260,120,85,25);

		this.pack();

		btnLogin.addActionListener (new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				id = userText.getText();
				char[] tmp = passText.getPassword();
				String pw = new String(tmp);
				boolean islogin = LoginService.LoginTest(id, pw);

				if(!islogin){
					JOptionPane.showMessageDialog(null,"FAIL!!");
				}
				else {
					dispose();

				}

				//				else {
				//					new WalletMain(id);
				//				}
				//				if(islogin){
				//					JOptionPane.showMessageDialog(null,"SUCCESS");
				//					dispose();
				//					new WalletMain(id);
				//					//로그인 성공하면 보여줄 창 생성.
				//					
				//				}
				//				else{
				//					JOptionPane.showMessageDialog(null,"FAIL!!");
				//				}
			}
		});

		btnSignup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new Signup();
			}
		});
		contentPane.add(pane);
		setVisible(true);
	}

	//	void gogo() throws Exception {
	//		chatClient chat_client = new chatClient("bearich");
	//		chat_client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//		chat_client.frame.setVisible(true);
	//		RefineryUtilities.centerFrameOnScreen(chat_client.frame);
	//		chat_client.chatMain();
	//	}

	public String getID() {
		return this.id;
	}
}
