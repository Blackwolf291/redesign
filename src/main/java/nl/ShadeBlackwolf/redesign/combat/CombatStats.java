package nl.ShadeBlackwolf.redesign.combat;

public class CombatStats {
	private static CombatStats combatStats = new CombatStats();
	public static CombatStats getCombatStats(){
		return combatStats;
	}
	private boolean combatOver = false;
	private boolean won = false;

	public void playerLoss() {
		combatOver = true;
	}

	public boolean notOver() {
		return !combatOver;
	}

	public void reset(){
		combatOver = false;
		won = false;
	}
}
