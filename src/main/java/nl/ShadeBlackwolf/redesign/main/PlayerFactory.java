package nl.ShadeBlackwolf.redesign.main;

import java.util.Map;

import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.persist.Persistable;

public class PlayerFactory implements Persistable{
	private Player player;
	
	public Player getPlayer(){
		return player;
	}
	public void create(){
		player = new Player();
	}
	private void restore(Map<String, String> data){
		player = new Player(data);
	}
	@Override
	public Map<String, String> getPersistedFields() {
		return player.getPersistedFields();
	}
	@Override
	public void restorePersistedFields(Map<String, String> data) {
		restore(data);
	}
}
