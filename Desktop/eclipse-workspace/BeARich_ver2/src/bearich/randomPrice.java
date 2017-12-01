package bearich;
import java.util.Random;


public class randomPrice {

	Random random = new Random(1234);
	
	private double virtual_price=0;
	

	
	public double getVirtual_price(double new_price) {
		
		double weight = (double)(Math.random()*4+98);
		weight /=100;
		virtual_price = (new_price * weight);
		
		return this.virtual_price;
	}
}
