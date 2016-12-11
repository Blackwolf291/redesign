package nl.ShadeBlackwolf.redesign.combat;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Combattant{

	List<Attack> attacks = new ArrayList<>();
	
	@Override
	public void attack(){
		turnCount++;
		if (!attacks.isEmpty()){
			attacks.get(0).performAttack();
		}
	}
}
