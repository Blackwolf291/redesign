package nl.ShadeBlackwolf.redesign.combat.utils;

public class CombatStatus {
	
	private boolean ongoing = true;
	private boolean won = false;
	
	public boolean isOngoing(){
		return ongoing;
	}

	public void lose() {
		ongoing = false;
	}

	public boolean hasWon() {
		return won;
	}

	public void win() {
		ongoing = false;
		won = true;
	}
}
