package bearich;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

//import com.sun.javafx.charts.Legend;

public class ShowFrame extends ApplicationFrame{



	static JFrame frame = new JFrame("BeARich"); // Main Frame
	JTextField textField = new JTextField(28);
	JTextArea messageArea = new JTextArea(45, 28);

	JPanel buttonPanel = new JPanel(); // Left Panel
	JPanel chartJPanel = new JPanel(); // Center Panel
	JPanel chatPanel = new JPanel(); // Right Panel

	JPanel walletPanel = new JPanel();

	//   chatClient cli;

	JPanel BTCPrice;
	JPanel ETHPrice;
	JPanel DASHPrice;
	JPanel LTCPrice;
	JPanel ETCPrice;
	JPanel XRPPrice;
	JPanel BCHPrice;
	JPanel XMRPrice;
	JPanel ZECPrice;
	JPanel QTUMPrice;

	//////// about chart////////
	TimeSeries BTCseries;
	TimeSeries ETHseries;
	TimeSeries DASHseries;
	TimeSeries LTCseries;
	TimeSeries ETCseries;
	TimeSeries XRPseries;
	TimeSeries BCHseries;
	TimeSeries XMRseries;
	TimeSeries ZECseries;
	TimeSeries QTUMseries;

	JTabbedPane JTP = new JTabbedPane();

	JFreeChart BTCchart;
	JFreeChart ETHchart;
	JFreeChart DASHchart;
	JFreeChart LTCchart;
	JFreeChart ETCchart;
	JFreeChart XRPchart;
	JFreeChart BCHchart;
	JFreeChart XMRchart;
	JFreeChart ZECchart;
	JFreeChart QTUMchart;

	ChartPanel BTCPanel;
	ChartPanel ETHPanel;
	ChartPanel DASHPanel;
	ChartPanel LTCPanel;
	ChartPanel ETCPanel;
	ChartPanel XRPPanel;
	ChartPanel BCHPanel;
	ChartPanel XMRPanel;
	ChartPanel ZECPanel;
	ChartPanel QTUMPanel;



	JPanel BTCwallet;
	JPanel ETHwallet;
	JPanel DASHwallet;
	JPanel LTCwallet;
	JPanel ETCwallet;
	JPanel XRPwallet;
	JPanel BCHwallet;
	JPanel XMRwallet;
	JPanel ZECwallet;
	JPanel QTUMwallet;

	JPanel BTCPricePanel;
	JPanel ETHPricePanel;
	JPanel DASHPricePanel;
	JPanel LTCPricePanel;
	JPanel ETCPricePanel;
	JPanel XRPPricePanel;
	JPanel BCHPricePanel;
	JPanel XMRPricePanel;
	JPanel ZECPricePanel;
	JPanel QTUMPricePanel;

	static JTextArea BTCPriceArea;
	static JTextArea ETHPriceArea;
	static JTextArea DASHPriceArea;
	static JTextArea LTCPriceArea;
	static JTextArea ETCPriceArea;
	static JTextArea XRPPriceArea;
	static JTextArea BCHPriceArea;
	static JTextArea XMRPriceArea;
	static JTextArea ZECPriceArea;
	static JTextArea QTUMPriceArea;

	JPanel BTCLabelPanel;
	JPanel ETHLabelPanel;
	JPanel DASHLabelPanel;
	JPanel LTCLabelPanel;
	JPanel ETCLabelPanel;
	JPanel XRPLabelPanel;
	JPanel BCHLabelPanel;
	JPanel XMRLabelPanel;
	JPanel ZECLabelPanel;
	JPanel QTUMLabelPanel;

	ImageIcon BTCIcon;
	ImageIcon ETHIcon;
	ImageIcon DASHIcon;
	ImageIcon LTCIcon;
	ImageIcon ETCIcon;
	ImageIcon XRPIcon;
	ImageIcon BCHIcon;
	ImageIcon XMRIcon;
	ImageIcon ZECIcon;
	ImageIcon QTUMIcon;

	static JTextArea krwArea;
	static JTextArea assetArea;
	static JTextArea buyArea;
	static JTextArea estArea;
	static JTextArea profArea;
	static JTextArea earnArea;

	//   * {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM (기본값: BTC), ALL(전체)


	public ShowFrame(String title) {
		super(title);
		/////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      
		// Layout GUI
		frame.setPreferredSize(new Dimension(1400,800));

		chartJPanel.setPreferredSize(new Dimension(1000,780)); // center panel size
		chatPanel.setPreferredSize(new Dimension(380,780)); // right panel size

		messageArea.setSelectionColor(new Color(35,98,200));

		//      frame.add(chartJPanel, "West"); 
		frame.add(JTP,"West");
		frame.add(chatPanel,"East"); 

		///////				///////				///////				///////				///////				///////				///////				///////				///////				///////				///////
		walletPanel.setPreferredSize(new Dimension(1000,765)); walletPanel.setBackground(Color.white);
		JLabel myassets = new JLabel("My Assets"); myassets.setForeground(Color.white);
		JLabel krw = new JLabel("KRW"); krw.setForeground(Color.white);
		JLabel totalmyinfo = new JLabel("Total Assests"); totalmyinfo.setForeground(Color.white);
		JLabel totalBuy = new JLabel("Total Buy"); totalBuy.setForeground(Color.white);
		JLabel totalEst = new JLabel("Total Estimates"); totalEst.setForeground(Color.white);
		JLabel totalprof = new JLabel("Total Profit"); totalprof.setForeground(Color.white);
		JLabel earning = new JLabel("Total Earning Rate");earning.setForeground(Color.white);

		krwArea = new JTextArea("100,000"); krwArea.setEditable(false); krwArea.setBackground(new Color(82,86,88));krwArea.setForeground(Color.white);
		assetArea = new JTextArea("200,000"); assetArea.setEditable(false); assetArea.setBackground(new Color(82,86,88));assetArea.setForeground(Color.white);
		buyArea = new JTextArea("0.0"); buyArea.setEditable(false); buyArea.setBackground(new Color(70,70,70));buyArea.setForeground(Color.white);
		estArea = new JTextArea("1.0"); estArea.setEditable(false); estArea.setBackground(new Color(70,70,70));estArea.setForeground(Color.white);
		profArea = new JTextArea("10.0"); profArea.setEditable(false); profArea.setBackground(new Color(70,70,70));profArea.setForeground(Color.white);
		earnArea = new JTextArea("10.1"); earnArea.setEditable(false); earnArea.setBackground(new Color(70,70,70));earnArea.setForeground(Color.white);


		JPanel asset = new JPanel();asset.setBackground(new Color(70,70,70));
		JPanel coins = new JPanel();coins.setLayout(new GridLayout(5,2));coins.setBackground(new Color(82,86,88));

		JPanel above = new JPanel(); above.setLayout(new GridLayout(1,0)); /*above.setBackground(new Color(227,227,227));*/
		JPanel left = new JPanel(); left.setLayout(new GridLayout(2,0)); left.setBackground(new Color(82,86,88));
		JPanel right = new JPanel(); right.setLayout(new GridLayout(2,0)); right.setBackground(new Color(82,86,88));
		JPanel below = new JPanel();  below.setLayout(new GridLayout(2,4)); /*below.setBackground(new Color(227,227,227));*/
		JPanel center = new JPanel(); center.setLayout(new GridLayout(1,2)); /*center.setBackground(new Color(227,227,227));*/



		above.add(myassets);

		left.add(krw); 
		left.add(krwArea);
		right.add(totalmyinfo);
		right.add(assetArea);

		below.add(totalBuy);
		below.add(buyArea);
		below.add(totalprof);
		below.add(profArea);
		below.add(totalEst);
		below.add(estArea);
		below.add(earning);
		below.add(earnArea);

		above.setPreferredSize(new Dimension(996,40));
		left.setPreferredSize(new Dimension(498,70));
		right.setPreferredSize(new Dimension(498, 70));
		below.setPreferredSize(new Dimension(996,45));
		center.setPreferredSize(new Dimension(996,140));

		asset.setPreferredSize(new Dimension(998, 260));
		coins.setPreferredSize(new Dimension(998,480));

		//set coins size and componets in coins.

		BTCwallet = new JPanel(); BTCwallet.setPreferredSize(new Dimension(495,92));
		ETHwallet = new JPanel();  ETHwallet.setPreferredSize(new Dimension(495,92));
		DASHwallet = new JPanel(); DASHwallet.setPreferredSize(new Dimension(495,92));
		LTCwallet = new JPanel(); LTCwallet.setPreferredSize(new Dimension(495,92));
		ETCwallet = new JPanel(); ETCwallet.setPreferredSize(new Dimension(495,92));

		XRPwallet = new JPanel();  XRPwallet.setPreferredSize(new Dimension(495,92));
		BCHwallet = new JPanel(); BCHwallet.setPreferredSize(new Dimension(495,92));
		XMRwallet = new JPanel(); XMRwallet.setPreferredSize(new Dimension(495,92));
		ZECwallet = new JPanel(); ZECwallet.setPreferredSize(new Dimension(495,92));
		QTUMwallet = new JPanel(); QTUMwallet.setPreferredSize(new Dimension(495,92));

		JLabel BTCImage = new JLabel(new ImageIcon(new ImageIcon("0.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH)));
		JLabel ETHImage = new JLabel(new ImageIcon( new ImageIcon("1.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel DASHImage = new JLabel(new ImageIcon(new ImageIcon("2.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel LTCImage = new JLabel(new ImageIcon(new ImageIcon("3.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel ETCImage = new JLabel(new ImageIcon(new ImageIcon("4.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel XRPImage = new JLabel(new ImageIcon(new ImageIcon("5.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel BCHImage = new JLabel(new ImageIcon(new ImageIcon("6.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel XMRImage = new JLabel(new ImageIcon(new ImageIcon("7.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel ZECImage = new JLabel(new ImageIcon(new ImageIcon("8.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 
		JLabel QTUMImage = new JLabel(new ImageIcon(new ImageIcon("9.jpeg").getImage().getScaledInstance(100, 92, Image.SCALE_SMOOTH))); 

		//add information of number of coins.
		coins.add(BTCwallet);  BTCwallet.setLayout(new BorderLayout());BTCwallet.add(BTCImage,"West"); BTCwallet.setBackground(new Color(82,86,88));
		coins.add(XRPwallet); XRPwallet.setLayout(new BorderLayout());XRPwallet.add(XRPImage,"West");XRPwallet.setBackground(new Color(82,86,88));
		coins.add(ETHwallet); ETHwallet.setLayout(new BorderLayout());ETHwallet.add(ETHImage,"West");ETHwallet.setBackground(new Color(82,86,88));
		coins.add(BCHwallet); BCHwallet.setLayout(new BorderLayout());BCHwallet.add(BCHImage,"West"); BCHwallet.setBackground(new Color(82,86,88));
		coins.add(DASHwallet); DASHwallet.setLayout(new BorderLayout());DASHwallet.add(DASHImage,"West");DASHwallet.setBackground(new Color(82,86,88));
		coins.add(XMRwallet); XMRwallet.setLayout(new BorderLayout());XMRwallet.add(XMRImage,"West");XMRwallet.setBackground(new Color(82,86,88));
		coins.add(LTCwallet); LTCwallet.setLayout(new BorderLayout());LTCwallet.add(LTCImage,"West");LTCwallet.setBackground(new Color(82,86,88));
		coins.add(ZECwallet); ZECwallet.setLayout(new BorderLayout());ZECwallet.add(ZECImage,"West");ZECwallet.setBackground(new Color(82,86,88));
		coins.add(ETCwallet); ETCwallet.setLayout(new BorderLayout());ETCwallet.add(ETCImage,"West"); ETCwallet.setBackground(new Color(82,86,88));
		coins.add(QTUMwallet); QTUMwallet.setLayout(new BorderLayout());QTUMwallet.add(QTUMImage,"West"); QTUMwallet.setBackground(new Color(82,86,88));

		BTCPricePanel = new JPanel();  BTCPricePanel.setPreferredSize(new Dimension(395,92));  
		ETHPricePanel = new JPanel();  ETHPricePanel.setPreferredSize(new Dimension(395,92)); 
		DASHPricePanel = new JPanel(); DASHPricePanel.setPreferredSize(new Dimension(395,92)); 
		LTCPricePanel = new JPanel(); LTCPricePanel.setPreferredSize(new Dimension(395,92)); 
		ETCPricePanel = new JPanel(); ETCPricePanel.setPreferredSize(new Dimension(395,92)); 
		XRPPricePanel = new JPanel(); XRPPricePanel.setPreferredSize(new Dimension(395,92)); 
		BCHPricePanel = new JPanel(); BCHPricePanel.setPreferredSize(new Dimension(395,92)); 
		XMRPricePanel = new JPanel(); XMRPricePanel.setPreferredSize(new Dimension(395,92));
		ZECPricePanel = new JPanel(); ZECPricePanel.setPreferredSize(new Dimension(395,92));
		QTUMPricePanel = new JPanel(); QTUMPricePanel.setPreferredSize(new Dimension(395,92)); 

		BTCPriceArea = new JTextArea("0,0");BTCPriceArea.setPreferredSize(new Dimension(395,46));BTCPricePanel.setLayout(new BorderLayout());BTCPricePanel.add(BTCPriceArea,"South");
		ETHPriceArea = new JTextArea("0,0"); ETHPriceArea.setPreferredSize(new Dimension(395,46));ETHPricePanel.setLayout(new BorderLayout()); ETHPricePanel.add(ETHPriceArea,"South");
		DASHPriceArea = new JTextArea("0,0"); DASHPriceArea.setPreferredSize(new Dimension(395,46)); DASHPricePanel.setLayout(new BorderLayout()); DASHPricePanel.add(DASHPriceArea,"South");
		LTCPriceArea = new JTextArea("0,0"); LTCPriceArea.setPreferredSize(new Dimension(395,46)); LTCPricePanel.setLayout(new BorderLayout()); LTCPricePanel.add(LTCPriceArea,"South");
		ETCPriceArea = new JTextArea("0,0"); ETCPriceArea.setPreferredSize(new Dimension(395,46)); ETCPricePanel.setLayout(new BorderLayout()); ETCPricePanel.add(ETCPriceArea,"South");
		XRPPriceArea = new JTextArea("0,0"); XRPPriceArea.setPreferredSize(new Dimension(395,46)); XRPPricePanel.setLayout(new BorderLayout()); XRPPricePanel.add(XRPPriceArea,"South");
		BCHPriceArea = new JTextArea("0,0"); BCHPriceArea.setPreferredSize(new Dimension(395,46)); BCHPricePanel.setLayout(new BorderLayout()); BCHPricePanel.add(BCHPriceArea,"South");
		XMRPriceArea = new JTextArea("0,0"); XMRPriceArea.setPreferredSize(new Dimension(395,46)); XMRPricePanel.setLayout(new BorderLayout()); XMRPricePanel.add(XMRPriceArea, "South");
		ZECPriceArea = new JTextArea("0,0"); ZECPriceArea.setPreferredSize(new Dimension(395,46)); ZECPricePanel.setLayout(new BorderLayout()); ZECPricePanel.add(ZECPriceArea, "South");
		QTUMPriceArea = new JTextArea("0,0"); QTUMPriceArea.setPreferredSize(new Dimension(395,46)); QTUMPricePanel.setLayout(new BorderLayout()); QTUMPricePanel.add(QTUMPriceArea, "South");

		BTCLabelPanel = new JPanel(); BTCLabelPanel.setLayout(new GridLayout(1,2));BTCLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel btc = new JLabel("BTC"); BTCLabelPanel.add(btc); BTCPricePanel.add(BTCLabelPanel); 
		ETHLabelPanel = new JPanel(); ETHLabelPanel.setLayout(new GridLayout(1,2));ETHLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel eth = new JLabel("ETH"); ETHLabelPanel.add(eth); ETHPricePanel.add(ETHLabelPanel);
		DASHLabelPanel = new JPanel(); DASHLabelPanel.setLayout(new GridLayout(1,2));DASHLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel dash = new JLabel("DASH"); DASHLabelPanel.add(dash); DASHPricePanel.add(DASHLabelPanel);
		LTCLabelPanel = new JPanel(); LTCLabelPanel.setLayout(new GridLayout(1,2));LTCLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel ltc = new JLabel("LTC"); LTCLabelPanel.add(ltc); LTCPricePanel.add(LTCLabelPanel);
		ETCLabelPanel = new JPanel(); ETCLabelPanel.setLayout(new GridLayout(1,2));ETCLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel etc = new JLabel("ETC"); ETCLabelPanel.add(etc); ETCPricePanel.add(ETCLabelPanel);
		XRPLabelPanel = new JPanel(); XRPLabelPanel.setLayout(new GridLayout(1,2));XRPLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel xrp = new JLabel("XRP"); XRPLabelPanel.add(xrp); XRPPricePanel.add(XRPLabelPanel);
		BCHLabelPanel = new JPanel(); BCHLabelPanel.setLayout(new GridLayout(1,2));BCHLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel bch = new JLabel("BCH"); BCHLabelPanel.add(bch); BCHPricePanel.add(BCHLabelPanel);
		XMRLabelPanel = new JPanel(); XMRLabelPanel.setLayout(new GridLayout(1,2));XMRLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel xmr = new JLabel("XMR"); XMRLabelPanel.add(xmr); XMRPricePanel.add(XMRLabelPanel);
		ZECLabelPanel = new JPanel(); ZECLabelPanel.setLayout(new GridLayout(1,2));ZECLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel zec = new JLabel("ZEC"); ZECLabelPanel.add(zec); ZECPricePanel.add(ZECLabelPanel);
		QTUMLabelPanel = new JPanel(); QTUMLabelPanel.setLayout(new GridLayout(1,2));QTUMLabelPanel.setPreferredSize(new Dimension(395,46)); JLabel qtum = new JLabel("QTUM"); QTUMLabelPanel.add(qtum); QTUMPricePanel.add(QTUMLabelPanel);

		BTCwallet.add(BTCPricePanel,"Center");
		ETHwallet.add(ETHPricePanel,"Center");
		DASHwallet.add(DASHPricePanel,"Center");
		LTCwallet.add(LTCPricePanel,"Center");
		ETCwallet.add(ETCPricePanel,"Center");
		XRPwallet.add(XRPPricePanel, "Center");
		BCHwallet.add(BCHPricePanel,"Center");
		XMRwallet.add(XMRPricePanel,"Center");
		ZECwallet.add(ZECPricePanel,"Center");
		QTUMwallet.add(QTUMPricePanel,"Center");

		walletPanel.add(asset,"North");
		walletPanel.add(coins,"South");

		coins.setBackground(new Color(82,86,88));
		center.add(left);left.setBackground(new Color(82,86,88));
		center.add(right);right.setBackground(new Color(82,86,88));


		asset.add(above,"North");above.setBackground(new Color(70,70,70));
		asset.add(center,"Center"); center.setBackground(new Color(82,86,88));
		asset.add(below,"South");below.setBackground(new Color(70,70,70));

		///////				///////				///////				///////				///////				///////				///////				///////				///////				///////				///////

		//////////    add chart /////////

		BTCseries = new TimeSeries("", Millisecond.class);
		ETHseries = new TimeSeries("", Millisecond.class);
		DASHseries = new TimeSeries("", Millisecond.class);
		LTCseries = new TimeSeries("", Millisecond.class);
		ETCseries = new TimeSeries("", Millisecond.class);
		XRPseries = new TimeSeries("", Millisecond.class);
		BCHseries = new TimeSeries("", Millisecond.class);
		XMRseries = new TimeSeries("", Millisecond.class);
		ZECseries = new TimeSeries("", Millisecond.class);
		QTUMseries = new TimeSeries("", Millisecond.class);

		final TimeSeriesCollection BTCdataset = new TimeSeriesCollection(BTCseries);
		final TimeSeriesCollection ETHdataset = new TimeSeriesCollection(ETHseries);
		final TimeSeriesCollection DASHdataset = new TimeSeriesCollection(DASHseries);
		final TimeSeriesCollection LTCdataset = new TimeSeriesCollection(LTCseries);
		final TimeSeriesCollection ETCdataset = new TimeSeriesCollection(ETCseries);
		final TimeSeriesCollection XRPdataset = new TimeSeriesCollection(XRPseries);
		final TimeSeriesCollection BCHdataset = new TimeSeriesCollection(BCHseries);
		final TimeSeriesCollection XMRdataset = new TimeSeriesCollection(XMRseries);
		final TimeSeriesCollection ZECdataset = new TimeSeriesCollection(ZECseries);
		final TimeSeriesCollection QTUMdataset = new TimeSeriesCollection(QTUMseries);

		BTCchart = createChart(BTCdataset);
		ETHchart = createChart(ETHdataset);
		DASHchart = createChart(DASHdataset);
		LTCchart = createChart(LTCdataset);
		ETCchart = createChart(ETCdataset);
		XRPchart = createChart(XRPdataset);
		BCHchart = createChart(BCHdataset);
		XMRchart = createChart(XMRdataset);
		ZECchart = createChart(ZECdataset);
		QTUMchart = createChart(QTUMdataset);

		BTCPanel = new ChartPanel(BTCchart); 
		ETHPanel = new ChartPanel(ETHchart);
		DASHPanel =new ChartPanel(DASHchart);
		LTCPanel = new ChartPanel(LTCchart);
		ETCPanel = new ChartPanel(ETCchart);
		XRPPanel = new ChartPanel(XRPchart);
		BCHPanel = new ChartPanel(BCHchart);
		XMRPanel = new ChartPanel(XMRchart);
		ZECPanel = new ChartPanel(ZECchart);
		QTUMPanel = new ChartPanel(QTUMchart);

		//      chartJPanel.add(BTCPanel);
		JTP.add("BTC",  BTCPanel);
		JTP.add("ETH",  ETHPanel);
		JTP.add("DASH",  DASHPanel);
		JTP.add("LTC",  LTCPanel);
		JTP.add("ETC",  ETCPanel);
		JTP.add("XRP",  XRPPanel);
		JTP.add("XMR",  XMRPanel);
		JTP.add("BCH",  BCHPanel);
		JTP.add("ZEC",  ZECPanel);
		JTP.add("QTUM",  QTUMPanel);
		JTP.add("My Account", walletPanel);

		BTCPanel.setPreferredSize(new Dimension(1000,765));
		ETHPanel.setPreferredSize(new Dimension(1000,765));
		DASHPanel.setPreferredSize(new Dimension(1000,765));
		LTCPanel.setPreferredSize(new Dimension(1000,765));
		ETCPanel.setPreferredSize(new Dimension(1000,765));
		XRPPanel.setPreferredSize(new Dimension(1000,765));
		XMRPanel.setPreferredSize(new Dimension(1000,765));
		BCHPanel.setPreferredSize(new Dimension(1000,765));
		ZECPanel.setPreferredSize(new Dimension(1000,765));
		QTUMPanel.setPreferredSize(new Dimension(1000,765));

		// set chart size
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
		BTCPanel.setMouseWheelEnabled(true);
		ETHPanel.setMouseWheelEnabled(true);
		DASHPanel.setMouseWheelEnabled(true);
		LTCPanel.setMouseWheelEnabled(true);
		ETCPanel.setMouseWheelEnabled(true);
		XRPPanel.setMouseWheelEnabled(true);
		XMRPanel.setMouseWheelEnabled(true);
		BCHPanel.setMouseWheelEnabled(true);
		ZECPanel.setMouseWheelEnabled(true);
		QTUMPanel.setMouseWheelEnabled(true);
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////   
		//////////    add text field, message Area ////////////

		chatPanel.add(new JScrollPane(messageArea), "Center");
		chatPanel.add(textField, "South");

		chatPanel.setBackground(new Color(41,41,41));
		//      chartJPanel.setBackground(new Color(41,41,41));
		buttonPanel.setBackground(new Color(41,41,41));
		BTCchart.setBackgroundPaint(new Color(41,41,41));
		ETHchart.setBackgroundPaint(new Color(41,41,41));
		DASHchart.setBackgroundPaint(new Color(41,41,41));
		LTCchart.setBackgroundPaint(new Color(41,41,41));
		ETCchart.setBackgroundPaint(new Color(41,41,41));
		XRPchart.setBackgroundPaint(new Color(41,41,41));
		XMRchart.setBackgroundPaint(new Color(41,41,41));
		BCHchart.setBackgroundPaint(new Color(41,41,41));
		ZECchart.setBackgroundPaint(new Color(41,41,41));
		QTUMchart.setBackgroundPaint(new Color(41,41,41));


		textField.setBackground(new Color(240,240,240));
		messageArea.setBackground(new Color(240,240,240));


		textField.setEditable(false);
		messageArea.setEditable(false);

		frame.pack();
	}   
	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart result = ChartFactory.createTimeSeriesChart(
				"",
				"",
				"",
				dataset,
				true,
				true,
				true
				);

		final XYPlot plot = result.getXYPlot();

		plot.setBackgroundPaint(Color.white);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		plot.setBackgroundPaint(new Color(41,41,41));
		plot.getRenderer().setSeriesPaint(0, Color.white);
		//      plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		//      plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
		ValueAxis xaxis = plot.getDomainAxis();
		//      xaxis.setLabelAngle( 90 * (Math.PI / 180.0) ); // time rotate
		xaxis.setAutoRange(true);
		xaxis.setLabel("Time");
		//Domain axis would show data of 60 seconds for a time
		xaxis.setFixedAutoRange(30000.0);  // size
		xaxis.setVerticalTickLabels(false); // vertical or horizon
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
		xaxis.setAxisLinePaint(Color.white);
		xaxis.setLabelPaint(Color.white);
		xaxis.setTickLabelPaint(Color.white);
		xaxis.setTickMarkPaint(Color.white);
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setLabel("Price");
		//      yaxis.setRange(8380000.0, 9010000.0); // y축 범위
		yaxis.setAutoRange(true);
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
		yaxis.setAxisLinePaint(Color.white);
		yaxis.setLabelPaint(Color.white);
		yaxis.setTickLabelPaint(Color.white);
		yaxis.setTickMarkPaint(Color.white);
		//////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////      //////////
		//      setSize(new Dimension(800,400));


		return result;
	}


	public TimeSeries getBTCSeries() {
		return BTCseries;
	}

	public TimeSeries getETHSeries() {
		return ETHseries;
	}
	public TimeSeries getDASHSeries() {
		return DASHseries;
	}
	public TimeSeries getLTCSeries() {
		return LTCseries;
	}
	public TimeSeries getETCSeries() {
		return ETCseries;
	}
	public TimeSeries getXRPSeries() {
		return XRPseries;
	}
	public TimeSeries getBCHSeries() {
		return BCHseries;
	}
	public TimeSeries getXMRSeries() {
		return XMRseries;
	}
	public TimeSeries getZECSeries() {
		return ZECseries;
	}
	public TimeSeries getQTUMSeries() {
		return QTUMseries;
	}

	//to set wallet newly.
	public void setWallet(String id) {
		//	   WalletMain wall = new WalletMain(id);
	}


//	public void setBTCPriceArea(String bTCPriceArea) {
//		//	BTCPriceArea = bTCPriceArea;
//		BTCPriceArea.setText(bTCPriceArea);
//	}
	public static void setBTCPriceArea(String bTCPriceArea) {
		BTCPriceArea.setText("");
		BTCPriceArea.setText(bTCPriceArea);
	}
	public static void setETHPriceArea(String eTHPriceArea) {
		ETHPriceArea.setText("");
		ETHPriceArea.setText(eTHPriceArea);
	}
	public static void setDASHPriceArea(String dASHPriceArea) {
			DASHPriceArea.setText("");
		DASHPriceArea.setText(dASHPriceArea);
	}
	public static void setLTCPriceArea(String lTCPriceArea) {
			LTCPriceArea.setText("");
		LTCPriceArea.setText(lTCPriceArea);
	}
	public static void setETCPriceArea(String  eTCPriceArea) {
			ETCPriceArea.setText("");
		ETCPriceArea.setText(eTCPriceArea);
	}
	public static void setXRPPriceArea(String  xRPPriceArea) {
			XRPPriceArea.setText("");
		XRPPriceArea.setText(xRPPriceArea);
	}
	public static void setBCHPriceArea(String bCHPriceArea) {
			BCHPriceArea.setText("");
		BCHPriceArea.setText(bCHPriceArea);
	}
	public static void setXMRPriceArea(String xMRPriceArea) {
			XMRPriceArea.setText("");
		XMRPriceArea.setText(xMRPriceArea);
	}

	public static void setZECPriceArea(String zECPriceArea) {
			ZECPriceArea.setText("");
		ZECPriceArea.setText(zECPriceArea);
	}

	public static void setQTUMPriceArea(String  qTUMPriceArea) {
			QTUMPriceArea.setText("");
		QTUMPriceArea.setText(qTUMPriceArea);
	}

	public static void setkrwArea(String krw) {
		krwArea.setText("");
		krwArea.setText(krw);
	}
	public static void setassetArea(String asset) {
		assetArea.setText(asset);
	}
	public static void setbuyArea(String area) {
		buyArea.setText(area);
	}
	public static void setestArea(String est) {
		estArea.setText(est);
	}
	public static void setprofArea(String prof) {
		profArea.setText(prof);
	}
	public static void setearnArea(String earn) {
		earnArea.setText(earn);
	}
}
