package nl.ShadeBlackwolf.redesign.combat;

import nl.ShadeBlackwolf.redesign.Player;

public class PlayerSpy extends Player {
	private boolean hasAttacked = false;
	
	@Override
	public void attack(){
		super.attack();
		hasAttacked = true;
	}

	public boolean hasAttacked() {
		return hasAttacked;
	}

}
