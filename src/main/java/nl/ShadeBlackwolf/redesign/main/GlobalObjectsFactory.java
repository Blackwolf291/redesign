package nl.ShadeBlackwolf.redesign.main;

import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.combat.EnemyProvider;
import nl.ShadeBlackwolf.redesign.combat.GlobalObjectsFactoryI;
import nl.ShadeBlackwolf.redesign.combat.utils.CombatStatus;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

public class GlobalObjectsFactory implements GlobalObjectsFactoryI {
	private Player player = new Player();
	private TurnCounter turnCounter = new TurnCounter();
	private CombatStatus combatStatus = new CombatStatus();
	private EnemyProvider enemyProvider = new EnemyProvider();
	
	public Player getPlayer(){
		return player;
	}
	
	public TurnCounter getTurnCounter(){
		return turnCounter;
	}

	public TurnCounter getNewTurnCounter(){
		turnCounter = new TurnCounter();
		return turnCounter;
	}
	
	public CombatStatus getCombatStatus(){
		return combatStatus;
	}

	public CombatStatus getNewCombatStatus(){
		combatStatus = new CombatStatus();
		return combatStatus;
	}
	public EnemyProvider getEnemyProvider(){
		return enemyProvider ;
	}
}
