package nl.ShadeBlackwolf.redesign.combat;

public class AllySpy implements Ally{

	private boolean hasAttacked = false;
	
	
	public boolean hasAttacked() {
		return hasAttacked;
	}

	@Override
	public void attack() {
		hasAttacked = true;
	}

}
