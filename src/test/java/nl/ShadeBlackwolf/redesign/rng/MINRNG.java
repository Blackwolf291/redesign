package nl.ShadeBlackwolf.redesign.rng;

import java.util.Random;

public class MINRNG extends Random{
	
	@Override
	public boolean nextBoolean(){
		return false;
	}
	

	@Override
	public int nextInt(int under){
		return 0;
	}
}
