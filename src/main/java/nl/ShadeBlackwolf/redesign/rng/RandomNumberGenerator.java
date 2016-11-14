package nl.ShadeBlackwolf.redesign.rng;

import java.util.Random;

public class RandomNumberGenerator {
	private Random r = new Random();
	
	void setRandom(Random r){
		this.r = r;
	}

	public boolean flip() {
		return r.nextBoolean();
	}

	public int flip(int coins) {
		int heads = 0;
		for (int i=0; i<coins;i++){
			if (flip()){
				heads++;
			}
		}
		return heads;
	}

	public int flipTillTails() {
		int heads = 0;
		while(flip()){
			heads++;
		}
		return heads;
	}

	public int roll(int size) {
		return roll(1, size);
	}

	public int roll() {
		return roll(6);
	}

	public int roll(int number, int size) {
		int result = 0;
		for (int count = 0; count<number; count++){
			result += r.nextInt(size)+1;
		}
		return result;
	}

}
