package nl.ShadeBlackwolf.redesign.rng;

import java.util.Random;

public class MAXRNG extends Random {
	public boolean nextBoolean(){
		return true;
	}
	

	@Override
	public int nextInt(int under){
		return under-1;
	}
}
