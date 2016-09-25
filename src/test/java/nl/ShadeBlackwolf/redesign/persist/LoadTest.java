package nl.ShadeBlackwolf.redesign.persist;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class LoadTest {
	
	private Loader loader;
	private PersistableList persistableList;
	
	@Before
	public void setup(){
		persistableList = new PersistableList();
		loader = new Loader(persistableList);
	}

	public class resultCollectorContext{
		private ResultCollector resultCollector;
		
		@Before
		public void setupResultCollector(){
			resultCollector = new ResultCollector();
			persistableList.register(resultCollector);
		}
		
		@Test
		public void loaderCollectsAndDistributesData(){
			loader.Load(getFile("test-Saves/testfile.sav"));
			assertMapEquals(getExpectedMap(), resultCollector.restoredData);
		}
		
		private void assertMapEquals(Map<String, String> expectedMap, Map<String, String> actualMap) {
			if (expectedMap.size()!=actualMap.size()){
				fail();
			}
			for (Entry<String, String> entry : expectedMap.entrySet()){
				if (actualMap.containsKey(entry.getKey())){
					assertEquals(entry.getValue(),actualMap.get(entry.getKey()));
				} else {
					fail();
				}
			}
		}

		private Map<String, String> getExpectedMap() {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "Shade");
			map.put("species", "wolf");
			return map;
		}

		private class ResultCollector implements Persistable{
			Map<String, String> restoredData;
			
			@Override
			public Map<String, String> getPersistedFields() {
				return null;
			}

			@Override
			public void restorePersistedFields(Map<String, String> data) {
				this.restoredData = data;
			}
		}
	}
	
	public class ExceptionContext{
		
		@Rule
		public ExpectedException exception = ExpectedException.none();
		
		@Test
		public void loaderFailsOnDoubleKey(){
			exception.expect(Loader.DuplicateKey.class);
			loader.Load(getFile("test-Saves/testfileWithDoubleKey.sav"));
		}

		@Test
		public void loaderFailsOnThreePartEntry(){
			exception.expect(Loader.MalformedEntry.class);
			loader.Load(getFile("test-Saves/testfileWithThreePartEntry.sav"));
		}

		@Test
		public void loaderFailsOnSinglePartEntry(){
			exception.expect(Loader.MalformedEntry.class);
			loader.Load(getFile("test-Saves/testfileWithSinglePartEntry.sav"));
		}
	}

	private File getFile(String fileName) {
		return new File(getClass().getClassLoader().getResource(fileName).getFile());
	}
	
}
