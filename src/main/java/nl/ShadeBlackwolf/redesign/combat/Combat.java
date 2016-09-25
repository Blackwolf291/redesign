package nl.ShadeBlackwolf.redesign.combat;

import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

public class Combat {
	private ActionFactoryI actionFactory;
	private TurnCounter turnCounter;
	private CombatStatus combatStatus;
	
	public Combat(ActionFactoryI actionFactory, TurnCounter turnCounter, CombatStatus combatStatus) {
		this.actionFactory=actionFactory;
		this.turnCounter = turnCounter;
		this.combatStatus = combatStatus;
		
	}

	public void performAction(PlayerActions action) {
		actionFactory.performAction(action);
	}

	public int getTurnCount() {
		return turnCounter.getTurnCount();
	}

	public boolean isOngoing() {
		return combatStatus.isOngoing();
	}
	
	public boolean hasWon() {
		return combatStatus.hasWon();
	}
}
