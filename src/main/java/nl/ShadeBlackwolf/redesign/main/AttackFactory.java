package nl.ShadeBlackwolf.redesign.main;

import nl.ShadeBlackwolf.redesign.combat.Attack;
import nl.ShadeBlackwolf.redesign.combat.AttackFactoryI;
import nl.ShadeBlackwolf.redesign.combat.EnemyProvider;
import nl.ShadeBlackwolf.redesign.combat.attacks.DummyAttack;

public class AttackFactory implements AttackFactoryI {
	private	EnemyProvider provider;
		
	public AttackFactory(EnemyProvider enemyProvider){
		this.provider = enemyProvider;
	}
		
	private Attack dummyAttack = new DummyAttack();
	public Attack getDummyAttack(){
		dummyAttack.setEnemy(provider.getEnemy());
		return dummyAttack;
	}
}
