package nl.ShadeBlackwolf.redesign.combat.actions;

import nl.ShadeBlackwolf.redesign.combat.Action;
import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;

public class Surrender implements Action {

	private CombatStatus combatStatus;
	
	public Surrender(CombatStatus combatStatus){
		this.combatStatus = combatStatus;
	}
	
	public void perform() {
		combatStatus.lose();
	}

}
