package nl.ShadeBlackwolf.redesign.combat;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy implements Combattant{

	List<Attack> attacks = new ArrayList<>();
	
	@Override
	public void attack(){
		if (!attacks.isEmpty()){
			attacks.get(0).performAttack();
		}
	}
	
	public void addAttack(VictoryAttack victoryAttack) {
		attacks.add(victoryAttack);
	}

}
