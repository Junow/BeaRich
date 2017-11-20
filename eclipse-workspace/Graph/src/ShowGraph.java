import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.*;
import org.jfree.chart.axis.CategoryLabelPositions;

@SuppressWarnings("serial")
public class ShowGraph  extends ApplicationFrame{

	private static TimeSeries series;
	
	@SuppressWarnings("deprecation") 
	// 생성자 
	public ShowGraph(final String title) {
		super(title);

		series = new TimeSeries("", Millisecond.class);
		
		final TimeSeriesCollection dataset = new TimeSeriesCollection(series);
		final JFreeChart chart = createChart(dataset);

		chart.setBorderPaint(Color.BLUE);

		
		final JPanel content = new JPanel();
		//Created Chartpanel for chart area
		final ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setAutoscrolls(true);
		

		content.add(chartPanel,BorderLayout.EAST);	
		content.setPreferredSize(new Dimension(1400,700));      // size of screen
		chartPanel.setHorizontalAxisTrace(true);
		chartPanel.setVerticalAxisTrace(true);
		content.setBackground(Color.white);
		chartPanel.setPreferredSize(new Dimension(900, 700)); // size of graph
		chartPanel.setChart(chart);

		JScrollPane js = new JScrollPane(chartPanel);
		js.setPreferredSize(new Dimension(2000,1000));
		
		//Puts the whole content on a Frame
		setContentPane(content);
		

	}

	public TimeSeries getSeries() {
		return series;
	}

	public static void setSeries(TimeSeries series) {
		ShowGraph.series = series;
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
				"BeARich",
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

	private void setLayout(String east) {
		// TODO Auto-generated method stub
	}
	
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

