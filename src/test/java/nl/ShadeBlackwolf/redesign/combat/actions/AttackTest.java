package nl.ShadeBlackwolf.redesign.combat.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nl.ShadeBlackwolf.redesign.combat.Attack;
import nl.ShadeBlackwolf.redesign.combat.CombatTest;
import nl.ShadeBlackwolf.redesign.combat.EnemyProvider;

import static nl.ShadeBlackwolf.redesign.combat.PlayerActions.attack;

public class AttackTest extends CombatTest{

	TestDummy enemy;
	EnemyProvider provider;
	
	@Before
	public void setup(){
		super.setup();
		enemy = new TestDummy(combatStatus);
		provider = new EnemyProvider();
		provider.setEnemy(enemy);
		Attack attack = new DummyAttack();
		attack.setEnemy(enemy);
		player.setAttack(attack);
	}
	
	@Test
	public void attackActionIncreasesTurn() {
		combat.performAction(attack);
		assertEquals(1, combat.getTurnCount());
	}

	@Test
	public void attackActionReducesEnemyHP() {
		enemy.setHP(1);
		combat.performAction(attack);
		assertTrue("Hitpoints didn't drop.", 1>enemy.getHP());
		assertFalse("combat not over", combat.isOngoing());
		assertTrue("not won", combat.hasWon());
	}

	@Test
	public void attackActionDoesntAlwaysInstakill() {
		enemy.setHP(2);
		combat.performAction(attack);
		assertTrue("Hitpoints didn't drop.", 2>enemy.getHP());
		assertTrue("combat over", combat.isOngoing());
		assertFalse("won", combat.hasWon());
	}
	
}
