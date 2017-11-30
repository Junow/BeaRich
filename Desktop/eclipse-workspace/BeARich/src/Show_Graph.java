import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
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

@SuppressWarnings("serial")
public class Show_Graph  extends ApplicationFrame{

	final TimeSeriesCollection dataset = new TimeSeriesCollection(series);
	final JFreeChart chart = createChart(dataset);
	//Created Chartpanel for chart area
	final ChartPanel chartPanel = new ChartPanel(chart);
	
	private static TimeSeries series;
	
	@SuppressWarnings("deprecation") 
	// 생성자 
	public Show_Graph(final String title) {
		super(title);

		
		series = new TimeSeries("", Millisecond.class);
		
//		JFrame frame = new JFrame("prac");
		
		final JPanel content = new JPanel(new BorderLayout());
		chart.setBorderPaint(Color.BLACK);
//		JTextArea messageArea = new JTextArea(20, 20); // just for test must be deleted.
		
//		JFrame frame = new JFrame(title);
		
		chartPanel.setAutoscrolls(true);
	
//		frame.setPreferredSize(new Dimension(1200,600)); // size of frame.
//		frame.setBackground(Color.white);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when close the frame, the program exit
//		
//		frame.pack();
		
		content.add(chartPanel);	
		content.setBackground(Color.white);
		content.setBounds(20,30,1,5);
		content.setPreferredSize(new Dimension(1200,600));      // size of screen
		chartPanel.setHorizontalAxisTrace(true);
		chartPanel.setVerticalAxisTrace(true);
		chartPanel.setPreferredSize(new Dimension(800, 600)); // size of graph
		chartPanel.setBounds(45,30,910,570); // must be needed to show graph............. controls location of graph
		chartPanel.setChart(chart);

		
		//Puts the whole content on a Frame
//		setContentPane(content);
		
		
//		frame.add(new JScrollPane(messageArea), "East");  // just for test must be deleted.
//		frame.add(chartPanel, "West");
//		frame.add(content,BorderLayout.WEST); // jpanel into frame
		
//		frame.setVisible(true);
		

	}
	
	public JFreeChart get_chart() {
		return this. chart;
	}

	public TimeSeries getSeries() {
		return series;
	}

	public static void setSeries(TimeSeries series) {
		Show_Graph.series = series;
	}

	/**
	 * Creates a sample chart.
	 *
	 * @param dataset  the dataset.
	 *
	 * @return A sample chart.
	 */
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
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);
		plot.setRangeCrosshairPaint(Color.white);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinePaint(Color.pink);
		plot.setRangeGridlinesVisible(false);

		ValueAxis xaxis = plot.getDomainAxis();
//		xaxis.setLabelAngle( 90 * (Math.PI / 180.0) ); // time rotate
		xaxis.setAutoRange(true);
		xaxis.setLabel("time");
		//Domain axis would show data of 60 seconds for a time
//		xaxis.setFixedAutoRange(60000.0);  // size
		xaxis.setVerticalTickLabels(false); // vertical or horizon
		
		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setLabel("price");
//		yaxis.setRange(8380000.0, 9010000.0); // y축 범위
		yaxis.setAutoRange(true);
		yaxis.setAxisLinePaint(Color.black);
		
		this.setSize(new Dimension(800,400));
		

		return result;
	}

	
	public void setdata(double price) {
		Show_Graph.series.add(new Millisecond(), price);
	}
//	private void setLayout(String east) {
//		// TODO Auto-generated method stub
//	}
//	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
}
	/**
	 * Generates an random entry for a particular call
     made by time for every 1/4th of a second.
	 *
	 * @param e  the action event.
	 */
	//    public void actionPerformed(final ActionEvent e) {
	//
	////        final double factor = 0.9 + 0.2*Math.random();
	////        this.lastValue = this.lastValue * factor;
	//
	//        final Millisecond now = new Millisecond();
	//
	//        this.series.add(new Millisecond(), new_price);
	//
	////        System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+new_price);
	//    }

