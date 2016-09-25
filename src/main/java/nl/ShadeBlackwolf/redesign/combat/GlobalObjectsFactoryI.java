package nl.ShadeBlackwolf.redesign.combat;

import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.combat.utils.TurnCounter;

public interface GlobalObjectsFactoryI {

	Player getPlayer();

	TurnCounter getTurnCounter();

	TurnCounter getNewTurnCounter();

}