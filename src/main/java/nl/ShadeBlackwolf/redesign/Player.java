package nl.ShadeBlackwolf.redesign;

import java.util.HashMap;
import java.util.Map;

import nl.ShadeBlackwolf.redesign.combat.Combattant;

public class Player extends Combattant{

	private String name;
	
	public Player(Map<String, String> data) {
		name = data.get("name");
	}

	public Player() {
	}

	public Map<String, String> getPersistedFields() {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		return map;
	}

	public String getName() {
		return name;
	}

	public void attack() {
	}
}
