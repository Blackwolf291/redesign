package nl.ShadeBlackwolf.redesign.persist;

import java.util.*;
import java.util.Map.Entry;
public class SaveDataCollector {
	
	private PersistableList persistableList;
	
	public SaveDataCollector(PersistableList persistableList){
		this.persistableList = persistableList;
	}
	
	public String getSaveData() {
		Map<String, String> map = createSaveDataMap();
		String[] saveData = createSaveDataArray(map);
		return joinSaveDataToSingleString(saveData);
	}

	private String joinSaveDataToSingleString(String[] saveData) {
		StringJoiner joiner = new StringJoiner(";");
		for(String record : saveData){
			joiner.add(record);
		}
		return joiner.toString();
	}

	private String[] createSaveDataArray(Map<String, String> map) {
		String[] saveData = new String[map.size()];
		int index = 0;
		for(Entry<String, String> entry:map.entrySet()){
			saveData[index] = entry.getKey() + ":" + entry.getValue();
			index++;
		}
		return saveData;
	}

	private Map<String, String> createSaveDataMap() {
		Map<String, String> map = new HashMap<>();
		for(Persistable p : persistableList.getList()){
			for (Entry<String, String> entry:p.getPersistedFields().entrySet()){
				if(map.containsKey(entry.getKey())){
					throw new DuplicateKey();
				}
				map.put(entry.getKey(), entry.getValue());
			}
			
		}
		return map;
	}
	class DuplicateKey extends RuntimeException{}

}
