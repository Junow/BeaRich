
import java.util.Random;


public class Random_Price {

	Random random = new Random(1234);
	
	private double virtual_price=0;
	

	
	public double getVirtual_price(double new_price) {
		
//		double weight = random.nextInt(105)+95;
		double weight = (double)(Math.random()*4+98);
		System.out.println("weight : " + weight);
//		weight %= 100;
		weight /=100;
//		weight+=1;
//		weight *= 0.01;
		
//		if(weight>0) {
//			weight+=1;
//		}
//		else {
//			weight-=1;
//		}
//		System.out.println("weight : " + weight);
		virtual_price = (new_price * weight);
		
		return this.virtual_price;
	}
}
