package nl.ShadeBlackwolf.redesign.combat;

public class EnemyMock extends Enemy {

	private boolean hasAttacked = false;
	private int victoryTurn;
	private int attackCount = 0;

	public boolean hasAttacked() {
		return hasAttacked;
	}

	@Override
	public void attack() {
		turnCount++;
		if(turnCount==victoryTurn){
			new VictoryAttack().performAttack();
		}
		hasAttacked = true;
		attackCount++;
		
	}
	

	public void addVictoryAttack(int victoryTurn) {
		this.victoryTurn = victoryTurn;
	}
	
	public int getNumberOfAttacks(){
		return attackCount;
	}

}
