package nl.ShadeBlackwolf.redesign.persist;

import java.util.Map;

public interface Persistable {

	public Map<String, String> getPersistedFields();

	public void restorePersistedFields(Map<String, String> data);
}
