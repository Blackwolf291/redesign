package nl.ShadeBlackwolf.redesign.combat.actions;

import nl.ShadeBlackwolf.redesign.combat.Action;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

public class Confused implements Action {

	private TurnCounter turnCounter;
	
	public Confused(TurnCounter turnCounter){
		this.turnCounter = turnCounter;
	}
	
	public void perform() {
		turnCounter.incrementTurn();
	}

}