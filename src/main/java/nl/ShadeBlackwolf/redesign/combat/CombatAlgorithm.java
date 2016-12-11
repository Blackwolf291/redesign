package nl.ShadeBlackwolf.redesign.combat;
import static nl.ShadeBlackwolf.redesign.combat.CombatStats.*;

import java.util.HashMap;
import java.util.Map; 

public class CombatAlgorithm {
	private Combattant[] combattants;
	private Map <Integer,Combattant> turnTracker = new HashMap<>();
	public CombatAlgorithm(Combattant[] combattants) {
		this.combattants = combattants;
		for (Combattant c: combattants){
			int firstTurn = 100-c.getSpeed();
			setTurn(c, firstTurn);
		}
	}

	private void setTurn(Combattant c, int turn) {
		if(!turnTracker.containsKey(turn)){
			turnTracker.put(turn, c);
		} else {
			turn++;
			setTurn(c, turn);
		}
	}

	public static void start(Combattant... combattants){
		CombatAlgorithm combat = new CombatAlgorithm(combattants);
		getCombatStats().reset();
		combat.run();
	}

	private void run() {
		int i = 0;
		while(getCombatStats().notOver()){
			if(turnTracker.containsKey(i)){
				Combattant combattant = turnTracker.get(i);
				combattant.attack();
				setTurn(combattant, i+combattant.getSpeed());
			}
			i++;
		}
	}
}
