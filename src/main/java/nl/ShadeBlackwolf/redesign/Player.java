package nl.ShadeBlackwolf.redesign;

import java.util.HashMap;
import java.util.Map;

import nl.ShadeBlackwolf.redesign.combat.Attack;

public class Player{

	private Attack attack;
	private String name;
	
	public Player(Map<String, String> data) {
		name = data.get("name");
	}

	public Player() {
	}

	public void setAttack(Attack attack){
		this.attack = attack;
	}
	
	public Attack getAttack() {
		return attack;
	}

	public Map<String, String> getPersistedFields() {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		return map;
	}

	public String getName() {
		return name;
	}
}
