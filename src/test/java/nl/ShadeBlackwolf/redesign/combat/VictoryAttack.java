package nl.ShadeBlackwolf.redesign.combat;

import static nl.ShadeBlackwolf.redesign.combat.CombatStats.getCombatStats;

public class VictoryAttack implements Attack {

	@Override
	public void performAttack() {
		getCombatStats().playerLoss();
	}

}
