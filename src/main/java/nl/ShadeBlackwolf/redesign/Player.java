package nl.ShadeBlackwolf.redesign;

import nl.ShadeBlackwolf.redesign.combat.Attack;

public class Player {

	private Attack attack;
	
	public void setAttack(Attack attack){
		this.attack = attack;
	}
	
	public Attack getAttack() {
		return attack;
	}
}
