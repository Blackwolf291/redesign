package nl.ShadeBlackwolf.redesign.main;

import nl.ShadeBlackwolf.redesign.persist.Loader;
import nl.ShadeBlackwolf.redesign.persist.Persistable;
import nl.ShadeBlackwolf.redesign.persist.PersistableList;
import nl.ShadeBlackwolf.redesign.persist.SaveDataCollector;
import nl.ShadeBlackwolf.redesign.persist.Saver;

public class PersistanceFactory {
	private PersistableList persistableList = new PersistableList();
	private Loader loader = new Loader(persistableList);
	private Saver saver = new Saver(new SaveDataCollector(persistableList));
	
	public void registerPersistable(Persistable persistable){
		persistableList.register(persistable);
		
	}
	
	public Loader getLoader(){
		return loader;
	}
	
	public Saver getSaver(){
		return saver;
	}
}
