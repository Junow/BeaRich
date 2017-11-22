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

public class Graph /*implements ActionListener*/ {
	private static TimeSeries series;
	static double new_price=0;
	
	
	public static void main(final String[] args){
//		launch(args);
		long time1 = System.currentTimeMillis (); //초제한 클라 에서걸어
		long time2 = System.currentTimeMillis (); 
		
		Random_Price RP = new Random_Price(); // 서버에서 만들어서 소켓으로 넘겨줘야함.
		Get_Price get_price = new Get_Price(); // 서버에서 만들어서 소켓으로 넘겨줘야함.
		double virtual_price = 0; // 서
		
		final Show_Graph demo = new Show_Graph("Be A Rich"); // 클라에서 실행.
//		demo.pack();
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);
		
		series = demo.getSeries();
		
		//와일문 초제한빼고 클라에서 실행
		//초제한은 서버에서 
		while(true) {
			time2 = System.currentTimeMillis ();
			//5초에 한번씩만 업데이트할려고 함 time1 time2 차이
			if(Math.abs((time2-time1)/1000.0) >= 3) {
				time1 = System.currentTimeMillis (); 
//				* {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM (기본값: BTC), ALL(전체)
					new_price = get_price.get_price(get_price.connect("BTC"));
					
					virtual_price = ( RP).getVirtual_price(new_price);
//					System.out.println("virtual : " + virtual_price);
					
					
					
					//조건문 다 서버에서;
					if(Math.abs(virtual_price - new_price) > new_price*0.995 && Math.abs(virtual_price - new_price) < new_price*1.005) {
//						System.out.println("virtual : "+virtual_price);
						series.add(new Millisecond(), virtual_price); // 이부분 서버에서 가격받음.
					}
					else {
						double half_new_vir = (new_price + virtual_price)/2;
//						System.out.println("half : "+half_new_vir);
						series.add(new Millisecond(), half_new_vir); // 이부분 서버에서 가격받음.
					}
			}
		}
	}
	
}


