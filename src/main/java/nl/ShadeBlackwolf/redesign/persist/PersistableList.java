package nl.ShadeBlackwolf.redesign.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersistableList {
	
	private List<Persistable> persistables = new ArrayList<>();

	public void register(Persistable testPersistable) {
		persistables.add(testPersistable);
	}

	public List<Persistable> getList() {
		return persistables;
	}
	
	public void publishData(Map<String, String> data){
		for (Persistable p : persistables){
			p.restorePersistedFields(data);
		}
	}
}
