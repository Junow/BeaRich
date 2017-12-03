package bearich;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import com.sun.javafx.charts.Legend;

public class ShowFrame extends ApplicationFrame{
	static JFrame frame = new JFrame("BeARich"); // Main Frame
	JTextField textField = new JTextField(22);
	JTextArea messageArea = new JTextArea(45, 22);

	JPanel buttonPanel = new JPanel(); // Left Panel
	JPanel chartJPanel = new JPanel(); // Center Panel
	JPanel chatPanel = new JPanel(); // Right Panel

	//////// about chart////////
	TimeSeries series;
	
	
	
	JFreeChart chart;
	ChartPanel chartPanel ;
//	* {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM (기본값: BTC), ALL(전체)
	JButton BTC;
	JButton ETH;
	JButton DASH;
	JButton LTC;
	JButton ETC;
	JButton XRP;
	JButton BCH;
	JButton XMR;
	JButton ZEC;
	JButton QTUM;
	
	public ShowFrame(String title) {
		super(title);
		/////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      /////////////      
		// Layout GUI
		frame.setPreferredSize(new Dimension(1400,800));
		
		buttonPanel.setPreferredSize(new Dimension(90,780)); // left panel size
		chartJPanel.setPreferredSize(new Dimension(1000,780)); // center panel size
		chatPanel.setPreferredSize(new Dimension(290,780)); // right panel size
		
		
		
		frame.add(buttonPanel,"West"); buttonPanel.setBackground(Color.pink);
		frame.add(chartJPanel, "Center"); chartJPanel.setBackground(Color.green);
		frame.add(chatPanel,"East"); chatPanel.setBackground(Color.MAGENTA);

		
		//////////    add button//////////////
		BTC = new JButton("BTC");
		ETH = new JButton("ETH");
		DASH = new JButton("DASH");
		LTC = new JButton("LTC");
		ETC = new JButton("ETC");
		XRP = new JButton("XRP");
		BCH = new JButton("BCH");
		XMR = new JButton("XMR");
		ZEC = new JButton("ZEC");
		QTUM = new JButton("QTUM");
		buttonPanel.add(BTC);
		buttonPanel.add(ETH);
		buttonPanel.add(DASH);
		buttonPanel.add(LTC);
		buttonPanel.add(ETC);
		buttonPanel.add(XRP);
		buttonPanel.add(BCH);
		buttonPanel.add(XMR);
		buttonPanel.add(ZEC);
		buttonPanel.add(QTUM);
		
		//////////    add chart /////////
		
		
		series = new TimeSeries("", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection(series);
		chart = createChart(dataset);
		chartPanel = new ChartPanel(chart); 
		chartJPanel.add(chartPanel);
		
		chartPanel.setPreferredSize(new Dimension(1000,765)); // set chart size
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////
		chartPanel.setMouseWheelEnabled(true);
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////	
		//////////    add text field, message Area ////////////
		
		chatPanel.add(new JScrollPane(messageArea), "Center");
		chatPanel.add(textField, "South");
		
		chatPanel.setBackground(new Color(90,94,96));
		chartJPanel.setBackground(new Color(90,94,96));
		buttonPanel.setBackground(new Color(90,94,96));
		chart.setBackgroundPaint(new Color(90,94,96));
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
		
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		plot.setBackgroundPaint(new Color(90,94,96));
		plot.setOutlinePaint(Color.white);
		ValueAxis xaxis = plot.getDomainAxis();
//		xaxis.setLabelAngle( 90 * (Math.PI / 180.0) ); // time rotate
		xaxis.setAutoRange(true);
		xaxis.setLabel("Time");
		//Domain axis would show data of 60 seconds for a time
//		xaxis.setFixedAutoRange(60000.0);  // size
		xaxis.setVerticalTickLabels(false); // vertical or horizon
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////
		xaxis.setAxisLinePaint(Color.white);
		xaxis.setLabelPaint(Color.white);
		xaxis.setTickLabelPaint(Color.white);
		xaxis.setTickMarkPaint(Color.white);
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////
		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setLabel("Price");
//		yaxis.setRange(8380000.0, 9010000.0); // y축 범위
		yaxis.setAutoRange(true);
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////
		yaxis.setAxisLinePaint(Color.white);
		yaxis.setLabelPaint(Color.white);
		yaxis.setTickLabelPaint(Color.white);
		yaxis.setTickMarkPaint(Color.white);
		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////		//////////
//		setSize(new Dimension(800,400));
		

		return result;
	}

	
	public TimeSeries getSeries() {
		return series;
	}

	
	
}