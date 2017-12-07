package bearich;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame{
	
	public Signup(){
		
		Container contentPane = this.getContentPane();
		setTitle("SignUp");
		setBounds(100,100,1000,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		pane.setLayout(null);
		
		JLabel userName = new JLabel("ID");
		userName.setBounds(10,10,80,25);
		pane.add(userName);
		
		JTextField userText = new JTextField(10);
		userText.setBounds(100,10,160,25);
		pane.add(userText);
		
		JLabel userPassword = new JLabel("Password");
		userPassword.setBounds(10,40,80,25);
		pane.add(userPassword);
		
		JPasswordField passText = new JPasswordField(10);
		passText.setBounds(100,40,160,25);
		pane.add(passText);
		
		JButton btnSignup = new JButton("SIGN UP");
		btnSignup.setBounds(100,80,100,25);
		pane.add(btnSignup);
		
		JButton btnDuplicate = new JButton("Duplicate Check");
		btnDuplicate.setBounds(270,10,200,25);
		pane.add(btnDuplicate);
		
		contentPane.add(pane);
		setVisible(true);
		
        btnSignup.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				String id = userText.getText();
				char[] tmp = passText.getPassword();
				String pw = new String(tmp);
				boolean islogin = SignupService.signup(id, pw);

				if(islogin){
					JOptionPane.showMessageDialog(null,"SUCCESS");
					dispose();
//					로그인성공하면 보여줄창 생성 
					
				}
				else{
					JOptionPane.showMessageDialog(null,"FAIL!!");
				}
			}
		});
        
        btnDuplicate.addActionListener(new ActionListener(){
			
        	
			@Override
			public void actionPerformed(ActionEvent e){
				String id = userText.getText();
			
				boolean isDuplicate = DuplicateService.duplicateCheck(id);

				if(isDuplicate){
					JOptionPane.showMessageDialog(null,"Duplicated");
				}
				else{
					JOptionPane.showMessageDialog(null,"You can use it for ID");
				}
			}
		});
		
	}
	//boolean flag= SignupService.signup("����","ȫ��");

}
