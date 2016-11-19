package nl.ShadeBlackwolf.redesign.combat;
import static nl.ShadeBlackwolf.redesign.combat.CombatStats.*; 

public class CombatAlgorithm {
	private Combattant[] combattants;
	
	public CombatAlgorithm(Combattant[] combattants) {
		this.combattants = combattants;
	}

	public static void start(Combattant... combattants){
		CombatAlgorithm combat = new CombatAlgorithm(combattants);
		getCombatStats().reset();
		combat.run();
	}

	private void run() {
		for (Combattant c : combattants){
			if(getCombatStats().notOver()){
				c.attack();
			}
		}
	}
}
