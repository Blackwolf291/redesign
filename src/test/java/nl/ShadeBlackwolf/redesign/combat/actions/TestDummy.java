package nl.ShadeBlackwolf.redesign.combat.actions;

import nl.ShadeBlackwolf.redesign.combat.Enemy;
import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;

public class TestDummy implements Enemy {

	private CombatStatus combatStatus;
	
	public TestDummy(CombatStatus combatStatus){
		this.combatStatus = combatStatus;
	}
	
	private int hp;
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(int hp){
		this.hp = hp;
	}
	
	public void dealDamage(int damage) {
		hp -= damage;
		if (hp<=0){combatStatus.win();}
	}

}
