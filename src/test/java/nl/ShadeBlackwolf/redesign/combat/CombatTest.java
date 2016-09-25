package nl.ShadeBlackwolf.redesign.combat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.combat.Combat;
import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;
import nl.ShadeBlackwolf.redesign.main.ActionFactory;

public abstract class CombatTest {

	protected Combat combat;
	protected Player player;
	protected TurnCounter turnCounter;
	protected CombatStatus combatStatus;
	
	@Before
	public void setup(){
		player = new Player();
		turnCounter = new TurnCounter();
		combatStatus = new CombatStatus();
		combat = new Combat(new ActionFactory(player, turnCounter, combatStatus), turnCounter, combatStatus);
	}
	
	@Test
	public void assertDefaultCombatConfiguration(){
		assertEquals(0, turnCounter.getTurnCount());
		assertTrue(combatStatus.isOngoing());
	}
}
