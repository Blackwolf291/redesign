package nl.ShadeBlackwolf.redesign.combat;

public abstract class Combattant {
	protected int turnCount = 0;
	public abstract void attack();
	public int getSpeed() {
		return 0;
	}
}
