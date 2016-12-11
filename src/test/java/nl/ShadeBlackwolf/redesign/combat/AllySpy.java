package nl.ShadeBlackwolf.redesign.combat;

public class AllySpy extends Ally{

	private boolean hasAttacked = false;
	
	
	public boolean hasAttacked() {
		return hasAttacked;
	}

	@Override
	public void attack() {
		hasAttacked = true;
	}

}
