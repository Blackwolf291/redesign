package nl.ShadeBlackwolf.redesign.persist;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import nl.ShadeBlackwolf.redesign.main.PersistanceFactory;

import static nl.ShadeBlackwolf.redesign.TestUtils.*;

@RunWith(HierarchicalContextRunner.class)
public class LoadTest {
	
	private PersistanceFactory factory;
	
	@Before
	public void setup(){
		factory = new PersistanceFactory();
	}

	public class resultCollectorContext{
		private ResultCollector resultCollector;
		
		@Before
		public void setupResultCollector(){
			resultCollector = new ResultCollector();
			factory.registerPersistable(resultCollector);
		}
		
		@Test
		public void loaderCollectsAndDistributesData(){
			factory.getLoader().Load(getFile("test-Saves/testfile.sav"));
			assertMapEquals(getExpectedMap(), resultCollector.restoredData);
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
			factory.getLoader().Load(getFile("test-Saves/testfileWithDoubleKey.sav"));
		}

		@Test
		public void loaderFailsOnThreePartEntry(){
			exception.expect(Loader.MalformedEntry.class);
			factory.getLoader().Load(getFile("test-Saves/testfileWithThreePartEntry.sav"));
		}

		@Test
		public void loaderFailsOnSinglePartEntry(){
			exception.expect(Loader.MalformedEntry.class);
			factory.getLoader().Load(getFile("test-Saves/testfileWithSinglePartEntry.sav"));
		}
	}

	private File getFile(String fileName) {
		return new File(getClass().getClassLoader().getResource(fileName).getFile());
	}
	
}
