package nl.ShadeBlackwolf.redesign.combat.utils;

public class TurnCounter {
	
	private int turnCount = 0;

	public int getTurnCount() {
		return turnCount;
	}

	public void incrementTurn(){
		turnCount++;
	}
}