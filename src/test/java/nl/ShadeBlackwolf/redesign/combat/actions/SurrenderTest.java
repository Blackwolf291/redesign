package nl.ShadeBlackwolf.redesign.combat.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.ShadeBlackwolf.redesign.combat.CombatTest;
import static nl.ShadeBlackwolf.redesign.combat.PlayerActions.*;

public class SurrenderTest extends CombatTest{

	@Test
	public void actionEndsCombat() {
		combat.performAction(surrender);
		assertFalse(combatStatus.isOngoing());
	}
	
	@Test
	public void actionLosesCombat() {
		combat.performAction(surrender);
		assertFalse(combat.hasWon());
	}
}
