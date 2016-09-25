package nl.ShadeBlackwolf.redesign.combat.actions;

import static nl.ShadeBlackwolf.redesign.combat.PlayerActions.confused;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.ShadeBlackwolf.redesign.combat.CombatTest;

public class ConfusedTest extends CombatTest{

	@Test
	public void actionIncreasesTurnCount() {
		combat.performAction(confused);
		assertEquals(1, combat.getTurnCount());
	}

	@Test
	public void repeatedActionIncreaseTurnCountByOneEach(){
		combat.performAction(confused);
		combat.performAction(confused);
		combat.performAction(confused);
		combat.performAction(confused);
		combat.performAction(confused);
		combat.performAction(confused);
		assertEquals(6, turnCounter.getTurnCount());
	}
	
	@Test
	public void waitActionDoesNotEndCombat(){
		combat.performAction(confused);
		assertTrue(combatStatus.isOngoing());
	}
}
