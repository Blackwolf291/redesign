package nl.ShadeBlackwolf.redesign.combat.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.ShadeBlackwolf.redesign.combat.CombatTest;
import static nl.ShadeBlackwolf.redesign.combat.PlayerActions.*;

public class WaitTest extends CombatTest{

	@Test
	public void actionIncreasesTurnCount() {
		combat.performAction(wait);
		assertEquals(1, combat.getTurnCount());
	}
	
	@Test
	public void repeatedActionIncreaseTurnCountByOneEach(){
		combat.performAction(wait);
		combat.performAction(wait);
		combat.performAction(wait);
		combat.performAction(wait);
		combat.performAction(wait);
		combat.performAction(wait);
		assertEquals(6, turnCounter.getTurnCount());
	}
	
	@Test
	public void waitActionDoesNotEndCombat(){
		combat.performAction(wait);
		assertTrue(combatStatus.isOngoing());
	}
}
