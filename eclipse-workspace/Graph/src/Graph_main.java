

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




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.json.*;

/**
 * An example to show how we can create a dynamic chart.
 */

@SuppressWarnings("serial")
public class Graph_main extends ApplicationFrame /*implements ActionListener*/ {

	static double new_price=0;
	//    static double test=0;
	/**
	 * Starting point for the dynamic graph application.
	 *
	 * @param args  ignored.
	 */
	public static void main(final String[] args) {
		long time1 = System.currentTimeMillis (); 
		long time2 = System.currentTimeMillis ();


		final Graph_main demo = new Graph_main("Dynamic Line And TimeSeries Chart");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		while(true) {
			time2 = System.currentTimeMillis ();
			//5초에 한번씩만 업데이트할려고 함 time1 time2 차이
			if((time2-time1)/1000.0 >= 5) {
				time1 = System.currentTimeMillis (); 
				String coinName = "BTC";
				StringBuilder sb = null;
				try {
					URL url = new URL("https://api.bithumb.com/public/ticker/");
					String postSql = "&offset=0&count=11";
					URLConnection conn;
					conn = url.openConnection();

					conn.setDoOutput(true);
					OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
					wr.write(postSql);
					wr.flush();
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					sb = new StringBuilder();
					String line = null;

					while ((line = reader.readLine()) != null) {

						sb.append(line + "\n");
					}
					//                System.out.println(sb);
				} catch (Exception e) {
					e.printStackTrace();
				}

				///////////////////////////////////////////////////////////////////

				String newSb = sb.toString();



				try {
					JSONParser jsonParser = new JSONParser();

					JSONObject jsonObject = (JSONObject) jsonParser.parse(newSb);
					JSONObject dataObject = (JSONObject) jsonObject.get("data");



					String status = jsonObject.get("status").toString();
					double opening_price = Double.parseDouble(dataObject.get("opening_price").toString());
					//                double closing_price = Double.parseDouble(dataObject.get("closing_price").toString());
					//                double min_price = Double.parseDouble(dataObject.get("min_price").toString());
					//                double max_price = Double.parseDouble(dataObject.get("max_price").toString());
					//                double average_price = Double.parseDouble(dataObject.get("average_price").toString());
					//                double units_traded = Double.parseDouble(dataObject.get("units_traded").toString());
					//                double volume_1day = Double.parseDouble(dataObject.get("volume_1day").toString());
					//                double volume_7day = Double.parseDouble(dataObject.get("volume_7day").toString());
					//                double buy_price = Double.parseDouble(dataObject.get("buy_price").toString());
					//                double sell_price = Double.parseDouble(dataObject.get("sell_price").toString());
					//                String date = (dataObject.get("date").toString());

					//                Thread.sleep(5000);
					new_price = opening_price;
					final Millisecond now = new Millisecond();
					//                final Second now = new Second();
					series.add(new Millisecond(), new_price);
					System.out.println("new price : " + new_price);




				}
				catch (ParseException e) {
					e.printStackTrace();
				}
				//            catch(InterruptedException e){
				//                e.printStackTrace();
				//            }
			}

			//        final Graph demo = new Graph("Dynamic Line And TimeSeries Chart");
			//                demo.pack();
			//                RefineryUtilities.centerFrameOnScreen(demo);
			//                demo.setVisible(true);
			//                demo.setBackground(Color.pink);

		}
	}


	/** The time series data. */
	private static TimeSeries series;

	/** The most recent value added. */
	private double lastValue = 100.0;
	public double testNum=0;
	/** Timer to refresh graph after every 1/4th of a second */
	//    private Timer timer = new Timer(1000, this);

	/**
	 * Constructs a new dynamic chart application.
	 *
	 * @param title  the frame title.
	 */
	@SuppressWarnings("deprecation") 
	// 생성자 
	public Graph_main(final String title) {
		super(title);

		//        testNum=num;
		this.series = new TimeSeries("Random Data", Millisecond.class);

		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
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

		plot.setBackgroundPaint(new Color(0xffffe0));
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

		ValueAxis yaxis = plot.getRangeAxis();
		yaxis.setRange(7580000.0, 7610000.0); // y축 범위

		return result;
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


}


