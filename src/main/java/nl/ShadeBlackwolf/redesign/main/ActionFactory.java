package nl.ShadeBlackwolf.redesign.main;

import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.combat.Action;
import nl.ShadeBlackwolf.redesign.combat.ActionFactoryI;
import nl.ShadeBlackwolf.redesign.combat.PlayerActions;
import nl.ShadeBlackwolf.redesign.combat.actions.AttackAction;
import nl.ShadeBlackwolf.redesign.combat.actions.Confused;
import nl.ShadeBlackwolf.redesign.combat.actions.Surrender;
import nl.ShadeBlackwolf.redesign.combat.actions.Wait;
import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

public class ActionFactory implements ActionFactoryI{

	Action wait;
	Action surrender;
	Action confused;
	Action attack;
	
	public ActionFactory(Player player, TurnCounter turnCounter, CombatStatus combatStatus){
		attack = new AttackAction(player, turnCounter);
		confused = new Confused(turnCounter);
		wait = new Wait(turnCounter);
		surrender = new Surrender(combatStatus);
	}
	
	public void performAction(PlayerActions act){
		switch(act){
		case attack:
			attack.perform();
			break;
		case surrender:
			surrender.perform();
			break;
		case wait:
			wait.perform();
			break;
		default:
			confused.perform();
			break;
		
		}
	}
}
