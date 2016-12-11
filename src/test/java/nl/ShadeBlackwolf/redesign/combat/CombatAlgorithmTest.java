package nl.ShadeBlackwolf.redesign.combat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static nl.ShadeBlackwolf.redesign.combat.CombatAlgorithm.start;

@RunWith(HierarchicalContextRunner.class)
public class CombatAlgorithmTest {

	private PlayerSpy player;
	private EnemyMock enemy1;

	@Before
	public void setup() {
		player = new PlayerSpy();
		enemy1 = new EnemyMock();
	}

	@Test
	public void canAddMultipleEnemies() {
		enemy1.addVictoryAttack(1);
		start(player, enemy1, new EnemyMock());
	}

	public class fullPartyContext {
		AllySpy ally1;
		AllySpy ally2;
		AllySpy ally3;
		EnemyMock enemy2;
		EnemyMock enemy3;
		EnemyMock enemy4;
		EnemyMock enemy5;
		
		@Before
		public void createAllCombattants(){
			ally1 = new AllySpy();
			ally2 = new AllySpy();
			ally3 = new AllySpy();
			enemy2 = new EnemyMock();
			enemy3 = new EnemyMock();
			enemy4 = new EnemyMock();
			enemy5 = new EnemyMock();
		}
		
		@Test
		public void givenNoVictory_everyoneAttacks() {
			enemy5.addVictoryAttack(1);
			start(player, ally1, ally2, ally3, enemy1, enemy2, enemy3, enemy4, enemy5);
			assertTrue("player did not attack", player.hasAttacked());
			assertTrue("ally1 did not attack", ally1.hasAttacked());
			assertTrue("ally2 did not attack", ally2.hasAttacked());
			assertTrue("ally3 did not attack", ally3.hasAttacked());
			assertTrue("enemy1 did not attack", enemy1.hasAttacked());
			assertTrue("enemy2 did not attack", enemy2.hasAttacked());
			assertTrue("enemy3 did not attack", enemy3.hasAttacked());
			assertTrue("enemy4 did not attack", enemy4.hasAttacked());
			assertTrue("enemy5 did not attack", enemy5.hasAttacked());
		}

		@Test
		public void givenVictory_combatEnds() {
			enemy1.addVictoryAttack(1);
			start(player, ally1, ally2, ally3, enemy1, enemy2, enemy3, enemy4, enemy5);
			assertTrue("player did not attack", player.hasAttacked());
			assertTrue("ally1 did not attack", ally1.hasAttacked());
			assertTrue("ally2 did not attack", ally2.hasAttacked());
			assertTrue("ally3 did not attack", ally3.hasAttacked());
			assertTrue("enemy1 did not attack", enemy1.hasAttacked());
			assertFalse("enemy2 attacked", enemy2.hasAttacked());
			assertFalse("enemy3 attacked", enemy3.hasAttacked());
			assertFalse("enemy4 attacked", enemy4.hasAttacked());
			assertFalse("enemy5 attacked", enemy5.hasAttacked());
		}

		@Test
		public void givenNoVictory_canHaveMultipleRounds() {
			enemy1.addVictoryAttack(2);
			start(player, ally1, ally2, ally3, enemy1, enemy2, enemy3, enemy4, enemy5);
			assertTrue("player did not attack", player.hasAttacked());
			assertTrue("ally1 did not attack", ally1.hasAttacked());
			assertTrue("ally2 did not attack", ally2.hasAttacked());
			assertTrue("ally3 did not attack", ally3.hasAttacked());
			assertTrue("enemy1 did not attack", enemy1.hasAttacked());
			assertEquals("enemy1 did not attack twice", 2, enemy1.getNumberOfAttacks());
			assertTrue("enemy2 did not attack", enemy2.hasAttacked());
			assertTrue("enemy3 did not attack", enemy3.hasAttacked());
			assertTrue("enemy4 did not attack", enemy4.hasAttacked());
			assertTrue("enemy5 did not attack", enemy5.hasAttacked());
		}
	}
}
