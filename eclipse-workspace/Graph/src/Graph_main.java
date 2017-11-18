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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.json.*;

/**
 * An example to show how we can create a dynamic chart.
 */

@SuppressWarnings("serial")
public class Graph_main /*implements ActionListener*/ {

	private static TimeSeries series;
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

		Random_price virtual = new Random_price();
		double virtual_price = 0;
		
		final ShowGraph demo = new ShowGraph("Be A Rich");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		series = demo.getSeries();
		

		
		while(true) {
			time2 = System.currentTimeMillis ();
			//5초에 한번씩만 업데이트할려고 함 time1 time2 차이
			if(Math.abs((time2-time1)/1000.0) >= 5) {
				time1 = System.currentTimeMillis (); 
//				* {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM (기본값: BTC), ALL(전체)
				String coinName = "BTC"; 
				StringBuilder sb = null;
				try {
					URL url = new URL("https://api.bithumb.com/public/ticker/"+coinName);
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



					//					String status = jsonObject.get("status").toString();
					//					double opening_price = Double.parseDouble(dataObject.get("opening_price").toString());
					//                double closing_price = Double.parseDouble(dataObject.get("closing_price").toString());
					//                double min_price = Double.parseDouble(dataObject.get("min_price").toString());
					//                double max_price = Double.parseDouble(dataObject.get("max_price").toString());
					//                double average_price = Double.parseDouble(dataObject.get("average_price").toString());
					//                double units_traded = Double.parseDouble(dataObject.get("units_traded").toString());
					//                double volume_1day = Double.parseDouble(dataObject.get("volume_1day").toString());
					//                double volume_7day = Double.parseDouble(dataObject.get("volume_7day").toString());
					double buy_price = Double.parseDouble(dataObject.get("buy_price").toString());
					//                double sell_price = Double.parseDouble(dataObject.get("sell_price").toString());
					//                String date = (dataObject.get("date").toString());

					new_price = buy_price;
					
					virtual_price = virtual.getVirtual_price(new_price);
					
					System.out.println("virtual : " + virtual_price);
					
					if(Math.abs(virtual_price - new_price) > new_price*0.98 && Math.abs(virtual_price - new_price) < new_price*1.02) {
						System.out.println("virtual : "+virtual_price);
						series.add(new Millisecond(), virtual_price);
					}
					else {
						double half_new_vir = (new_price + virtual_price)/2;
						System.out.println("half : "+half_new_vir);
						series.add(new Millisecond(), half_new_vir);
					}
					
					
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
			
			}

			

		}
	}
	
	public void start(Stage stage) throws Exception {
	       Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
	    
	        Scene scene = new Scene(root, 300, 275);
	    
	        stage.setTitle("FXML Welcome");
	        stage.setScene(scene);
	        stage.show();
	    }
}


	/** The time series data. */


	/** The most recent value added. */
	//	private double lastValue = 100.0;
	/** Timer to refresh graph after every 1/4th of a second */
	//    private Timer timer = new Timer(1000, this);

	/**
	 * Constructs a new dynamic chart application.
	 *
	 * @param title  the frame title.
	 */
