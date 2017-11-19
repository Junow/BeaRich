import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.jfree.data.time.Millisecond;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class getPrice {

	
	public String connect(String currency) {
//		String coinName = "BTC"; 
		String coinName = currency;
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
		
		return sb.toString();
	}
	
	
	public double get_price(String sb) {
		Random_price virtual = new Random_price();
		double new_price=0;
//		double virtual_price;
		try {
			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(sb);
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
			
//			virtual_price = virtual.getVirtual_price(new_price);
			
			
//			System.out.println("virtual : " + virtual_price);
//			
//			if(Math.abs(virtual_price - new_price) > new_price*0.98 && Math.abs(virtual_price - new_price) < new_price*1.02) {
//				System.out.println("virtual : "+virtual_price);
//				series.add(new Millisecond(), virtual_price);
//			}
//			else {
//				double half_new_vir = (new_price + virtual_price)/2;
//				System.out.println("half : "+half_new_vir);
//				series.add(new Millisecond(), half_new_vir);
//			}
			
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return new_price;
	}
}
