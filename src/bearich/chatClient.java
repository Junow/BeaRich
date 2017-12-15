package bearich;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.jfree.data.time.Millisecond;
/////////////////
public class chatClient extends ShowFrame implements ActionListener, UI{
public chatClient(String title) {
      super(title);
      // TODO Auto-generated constructor stub
   }

   //   super();
   BufferedReader in;
   PrintWriter out;
   
   private String myName="";
   
   private double newBTCPrice;
   private double newETHPrice;
   private double newDASHPrice;
   private double newLTCPrice;
   private double newETCPrice;
   private double newXRPPrice;
   private double newBCHPrice;
   private double newXMRPrice;
   private double newZECPrice;
   private double newQTUMPrice;
   
//   public chatClient() {
///////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      
//      // Layout GUI
//      textField.setEditable(false);
//      messageArea.setEditable(false);
//      frame.getContentPane().add(textField, "North");
//      frame.getContentPane().add(new JScrollPane(messageArea), "Center");
//      frame.pack();
//
//      // Add Listeners
//      
///////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      
//   }
   
   public void chatMain() throws IOException {
      // Make connection and initialize streams
//      String serverAddress = getServerAddress();
//	   myName = name;
	   String serverAddress = "localhost";
      //create socket.
      Socket socket = new Socket(serverAddress, 9001);
      //incoming
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      //outgoing
      out = new PrintWriter(socket.getOutputStream(), true);//
      // Process all messages from server, according to the protocol.
      


      textField.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            out.println(textField.getText());
            textField.setText("");
         }
      });
      
      /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////
      //여기서 wallet 초기화
      // 스레드 하나 만들어서 계속 값 쏴줘야함 (argument = myName)

//      String[] walletinfo = LoadService.LoadWallet(myName);
//      System.out.println("----------------------------");
//      for(int i=0;i<11;i++) {
//    	  	System.out.print(walletinfo[i]+" ");
//      }
//      System.out.println("----------------------------");
      
      /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////


      Login log=null;
      while (true) {



         String line = in.readLine();
         System.out.println(line+"?????????");
         
         if (line.startsWith("SUBMITNAME")) {
                log = new Login(out);
                log.setVisible(true);log.pack();
//                log.isOk(in, out);



//        	 	this.pack();
//        	 	this.frame.setVisible(true);
//        	 	UIStart();
         }
         else if(line.startsWith("WELCOME")){
            System.out.println("welcome in client");
            log.dispose();	
            this.frame.setVisible(true);
         }
         else if(line.startsWith("RELOGIN")) {
        	 	JOptionPane.showMessageDialog(null, "FAIL");
        	 	log.dispose();
//        	 	log.kill();
//        	 	new Login(out);
         }
         else if (line.startsWith("NAMEACCEPTED")) {
//            chat_client.frame.setVisible(true);
            NameAccepted(line);
         } 
         else if (line.startsWith("MESSAGE")) {
            Message(line);
         } 
         //when whisper message is received.
         else if(line.startsWith("Whisper")) {
            Whisper(line);
         }
         //when the person who user are talking to is not exist.
         else if(line.startsWith("NO")) {
            No(line);
         }
         //when the user attempts to whisper to itself.
         else if(line.startsWith("You")) {
            You(line);
         }
         //when a new user logged in.
         else if(line.startsWith("ENTRANCE")) {
            Entrance(line);
         }
         else if(line.startsWith("NULL")){
            Null(line);
         }
         //when the user name is already used. server request a new name that is not exist.
//         else if(line.startsWith("ALREADY")) {
//            Already(line);
//         }
         //when a user logged out.
         else if(line.startsWith("OUT")) {
            Out(line);
         }
         else if(line.startsWith("price")){
        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////
        	 // thread setting user's wallet information
        	 
        	 
        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 /////        	 ////
            ////////// 쓰레드 하나 만들기
            System.out.println("hi thread");

            
           
            
            
            newBTCPrice = Double.parseDouble(line.substring(8)); 
            newETHPrice = Double.parseDouble(line.substring(8));
            newDASHPrice = Double.parseDouble(line.substring(8));
            newLTCPrice = Double.parseDouble(line.substring(8));
            newETCPrice = Double.parseDouble(line.substring(8));
            newXRPPrice = Double.parseDouble(line.substring(8));
            newBCHPrice = Double.parseDouble(line.substring(8));
            newXMRPrice = Double.parseDouble(line.substring(8));
            newZECPrice = Double.parseDouble(line.substring(8));
            newQTUMPrice = Double.parseDouble(line.substring(8));
            System.out.println("this is "+line);
            if(line.substring(5,8).compareTo("BTC")==0) {
               priceThread thr = new priceThread(socket,super.getBTCSeries(), newBTCPrice);
               thr.start();
            }
               else if(line.substring(5,8).compareTo("ETH")==0) {
                  priceThread thr = new priceThread(socket,super.getETHSeries(), newETHPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("DAS")==0) {
               priceThread thr= new priceThread(socket,super.getDASHSeries(), newDASHPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("LTC")==0) {
               priceThread thr = new priceThread(socket,super.getLTCSeries(), newLTCPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("ETC")==0) {
               priceThread thr = new priceThread(socket,super.getETCSeries(), newETCPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("XRP")==0) {
               priceThread thr = new priceThread(socket,super.getXRPSeries(), newXRPPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("BCH")==0) {
               priceThread thr = new priceThread(socket,super.getBCHSeries(), newBCHPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("XMR")==0) {
               priceThread thr = new priceThread(socket,super.getXMRSeries(), newXMRPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("ZEC")==0) {
               priceThread thr = new priceThread(socket,super.getZECSeries(), newZECPrice);
               thr.start();
            }
            else if(line.substring(5,8).compareTo("QTU")==0) {
               priceThread thr= new priceThread(socket,super.getQTUMSeries(), newQTUMPrice);
               thr.start();
            }
            
            WalletThread wallet1 = new WalletThread(myName); wallet1.start();

            String rate[] = new String[10];

            DecimalFormat realBTCPrice = new DecimalFormat("#.#");
            rate[0]= realBTCPrice.format(newBTCPrice*super.getBTCPriceArea());
            DecimalFormat realETHPrice = new DecimalFormat("#.#");
            rate[1] = realETHPrice.format(newETCPrice*super.getETHPriceArea());
            DecimalFormat realDASHPrice = new DecimalFormat("#.#");
            rate[2] = realDASHPrice.format(newDASHPrice*super.getDASHPriceArea());
            DecimalFormat realLTCPrice = new DecimalFormat("#.#");
            rate[3] = realDASHPrice.format(newLTCPrice*super.getLTCPriceArea());
            DecimalFormat realETCPrice = new DecimalFormat("#.#");
            rate[4] = realDASHPrice.format(newETCPrice*super.getETCPriceArea());
            DecimalFormat realXRPPrice = new DecimalFormat("#.#");
            rate[5] = realDASHPrice.format(newXRPPrice*super.getXRPPriceArea());
            DecimalFormat realBCHPrice = new DecimalFormat("#.#");
            rate[6] = realDASHPrice.format(newBCHPrice*super.getBCHPriceArea());
            DecimalFormat realXMRPrice = new DecimalFormat("#.#");
            rate[7] = realDASHPrice.format(newXMRPrice*super.getXMRPriceArea());
            DecimalFormat realZECPrice = new DecimalFormat("#.#");
            rate[8] = realDASHPrice.format(newZECPrice*super.getZECPriceArea());
            DecimalFormat realQTUMPrice = new DecimalFormat("#.#");
            rate[9] = realDASHPrice.format(newQTUMPrice*super.getQTUMPriceArea());
            
            super.setRate(rate);
            /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////
            
            /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////     /////
         }

      }
   }

   /**
    * Runs the client as an application with a closeable frame.
    */


   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
   }
   
   @Override
   public String getServerAddress() {
      return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
   }

   @Override
   public String getName() {
      return JOptionPane.showInputDialog(
      frame,
      "Choose a screen name:",
      "Screen name selection",
      JOptionPane.PLAIN_MESSAGE);
   }

   @Override
   public String NULL() {
      return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name(re-select the name):",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
   }

   @Override
   public String ALREADY() {
      return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name(already in use):",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
   }

   @Override
   public void UIStart() {
      textField.setEditable(false);
      messageArea.setEditable(false);
      frame.getContentPane().add(textField, "North");
      frame.getContentPane().add(new JScrollPane(messageArea), "Center");
      frame.pack();
      
   }

   @Override
   public void NameAccepted(String line) {
      // TODO Auto-generated method stub
      myName = line.substring(12);
      textField.setEditable(true);
   }

   @Override
   public void Message(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line.substring(8) + "\n");
      //this code makes automatic scrolling.
      messageArea.setCaretPosition(messageArea.getDocument().getLength());
   }

   @Override
   public void Whisper(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line.substring(7) +"\n");
      messageArea.setCaretPosition(messageArea.getDocument().getLength());
      messageArea.setDisabledTextColor(Color.red);
   }

   @Override
   public void No(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line.substring(2) + "\n");
      messageArea.setCaretPosition(messageArea.getDocument().getLength());
   }

   @Override
   public void You(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line + "\n");
      messageArea.setCaretPosition(messageArea.getDocument().getLength());
   }

   @Override
   public void Entrance(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line.substring(8) + " logged in! \n");
   }

   @Override
   public void Null(String line) {
      // TODO Auto-generated method stub
      while(true) {
         try {
            line = in.readLine();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         out.println(NULL());
         if(!(line.startsWith("NULL"))) {
            break;
         }
      }
   }

   @Override
   public void Already(String line) {
      // TODO Auto-generated method stub
      while(true) {
         try {
            line = in.readLine();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         out.println(ALREADY());
         if(!(line.startsWith("ALREADY"))) {
            break;
         }
      }
   }

   @Override
   public void Out(String line) {
      // TODO Auto-generated method stub
      messageArea.append(line.substring(3) + " logged out" + "\n");
      messageArea.setCaretPosition(messageArea.getDocument().getLength());
   }
   
   public String getMyName() {
	   return this.myName;
   }
   
   static double simplify(double d, double l, int n) {
	      return Math.round(d * l * Math.pow(10, n)) / Math.pow(10, n);
	   }

}