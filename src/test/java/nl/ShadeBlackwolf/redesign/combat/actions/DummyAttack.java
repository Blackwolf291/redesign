package nl.ShadeBlackwolf.redesign.combat.actions;

import nl.ShadeBlackwolf.redesign.combat.Attack;
import nl.ShadeBlackwolf.redesign.combat.Enemy;

public class DummyAttack implements Attack{

	private Enemy enemy;
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public void executeAttack() {
		enemy.dealDamage(1);
	}

}
