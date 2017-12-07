//import java.util.Random;

public class Perceptron {
	float[] weights;
	float c = (float) 0.01;
	
	//-1 ~ 1 사이의 값을 길이 n 짜리 가중치로 할당.
	Perceptron(int n){
		weights = new float[n];
		for(int i=0;i<weights.length;i++) {
			weights[i]=(float)Math.random()*3-1;
		}
	}
	
	//입력값과 가중치끼리 곱해서 양수인지 음수인지 판별.
	int feedforward(float[] inputs) {
		float sum=0;
		for(int i=0;i<inputs.length;i++) {
			sum += inputs[i]*weights[i];
		}
		return activate(sum);
	}
	//양수 1 음수 -1;
	int activate(float sum) {
		if(sum>0) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	// 답 - 예상값을 
	void train(float[] inputs, float desired) {
		int guess = feedforward(inputs);
		float error = desired - guess;
		for(int i=0;i<weights.length;i++) {
			weights[i]=c*error*inputs[i];
		}
	}


}
