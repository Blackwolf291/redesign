package nl.ShadeBlackwolf.redesign.combat.actions;

import nl.ShadeBlackwolf.redesign.combat.Action;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

import nl.ShadeBlackwolf.redesign.Player;

public class AttackAction implements Action {
	private Player player;
	private TurnCounter turnCounter;
	
	public AttackAction(Player player, TurnCounter turnCounter){
		this.player = player;
		this.turnCounter = turnCounter;
	}

	public void perform() {
		player.getAttack().executeAttack();
		turnCounter.incrementTurn();
	}

}
