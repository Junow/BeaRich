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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.json.*;

/**
 * An example to show how we can create a dynamic chart.
 */

public class Graph_main /*implements ActionListener*/ {
	private static TimeSeries series;
	static double new_price=0;
	
	
	public static void main(final String[] args){
//		launch(args);
		long time1 = System.currentTimeMillis (); 
		long time2 = System.currentTimeMillis ();
		
		Random_price virtual = new Random_price();
		getPrice get_price = new getPrice();
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
					new_price = get_price.get_price(get_price.connect("BTC"));
					
					virtual_price = virtual.getVirtual_price(new_price);
					
//					System.out.println("virtual : " + virtual_price);
					
					if(Math.abs(virtual_price - new_price) > new_price*0.98 && Math.abs(virtual_price - new_price) < new_price*1.02) {
//						System.out.println("virtual : "+virtual_price);
						series.add(new Millisecond(), virtual_price);
					}
					else {
						double half_new_vir = (new_price + virtual_price)/2;
//						System.out.println("half : "+half_new_vir);
						series.add(new Millisecond(), half_new_vir);
					}
			}
		}
	}
	
}


