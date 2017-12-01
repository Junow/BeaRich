package bearich;

import java.net.ServerSocket;
import java.net.Socket;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class priceThread extends Thread{

	private TimeSeries series;

	ServerSocket serverSocket;
	Socket socket;

	long time1 = System.currentTimeMillis (); 
	long time2 = System.currentTimeMillis ();

	double newPrice=0;
	
	public priceThread(Socket socket, TimeSeries series,double price) {
		this.socket=socket;
		this.series=series;
		this.newPrice=price;
	}

	// 가격 받았을떄만 받아서 series.add 하고 죽일 예정.
	public void run() {
		series.add(new Millisecond(), newPrice);
		System.out.println(newPrice);
	}

}
