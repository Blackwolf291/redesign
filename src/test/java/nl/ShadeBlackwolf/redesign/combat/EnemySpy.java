package nl.ShadeBlackwolf.redesign.combat;

public class EnemySpy extends Enemy {

	private boolean hasAttacked = false;

	public boolean hasAttacked() {
		return hasAttacked;
	}

	@Override
	public void attack() {
		super.attack();
		hasAttacked = true;
	}
}
