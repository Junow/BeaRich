import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.URL;
//import java.net.URLConnection;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import org.json.*;

@SuppressWarnings("serial")
public class ShowGraph  extends ApplicationFrame{

	private static TimeSeries series;
	
	@SuppressWarnings("deprecation") 
	// 생성자 
	public ShowGraph(final String title) {
		super(title);

		series = new TimeSeries("Random Data", Millisecond.class);

		final TimeSeriesCollection dataset = new TimeSeriesCollection(series);
		final JFreeChart chart = createChart(dataset);

		//        timer.setInitialDelay(10000);

		//Sets background color of chart 아예뒤쪾
		chart.setBackgroundPaint(Color.white);
		chart.setBorderPaint(Color.black);

		//Created JPanel to show graph on screen
		final JPanel content = new JPanel(new BorderLayout());

		//Created Chartpanel for chart area
		final ChartPanel chartPanel = new ChartPanel(chart);

		//Added chartpanel to main panel
		content.add(chartPanel);

		//Sets the size of whole window (JPanel)
		chartPanel.setPreferredSize(new java.awt.Dimension(1200, 700));

		//Puts the whole content on a Frame
		setContentPane(content);

		//        timer.start();

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
				"Time",
				"Price",
				dataset,
				true,
				true,
				false
				);

		final XYPlot plot = result.getXYPlot();
		
//		plot.setBackgroundPaint(new Color(0xffffe0));
		plot.setBackgroundPaint(new Color(128,128,128)); // background color
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.black);
		plot.setRangeCrosshairPaint(Color.white);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinePaint(Color.pink);
		plot.setRangeGridlinesVisible(false);
		//        plot.isDomainZoomable();

		//        plot.setDomainTickBandPaint(Color.pink);
		//        plot.setDomainZeroBaselinePaint(Color.pink);

		ValueAxis xaxis = plot.getDomainAxis();
		xaxis.setAutoRange(true);

		//Domain axis would show data of 60 seconds for a time
		xaxis.setFixedAutoRange(60000.0);  // 60 seconds //갱신주기?
		xaxis.setVerticalTickLabels(true);
//		xaxis.setAxisLinePaint(Color.pink);
		
		
		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setRange(7580000.0, 7610000.0); // y축 범위
		yaxis.setAutoRange(true);
		return result;
	}
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

