package nl.ShadeBlackwolf.redesign.persist;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.hamcrest.Matchers.*;

@RunWith(HierarchicalContextRunner.class)
public class SaveDataToFileTest {
	private File file;
	private Saver saver;
	private PersistableList persistableList;
	
	
	@Before
	public void setup(){
		file = new File("save.sav");
		persistableList = new PersistableList();
		saver = new Saver(new SaveDataCollector(persistableList));
	}
	
	@After
	public void teardown(){
		file.delete();
	}
	
	@Test
	public void saverWithoutRegisteredObjectsCreatesEmptyFile() throws IOException{
		saver.save();
		assertTrue(file.exists());
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			assertEquals(null, br.readLine());
		}
	}

	public class TestPersistableContext{
		private TestPersistable testPersistable;
		
		@Before
		public void setupTestPersistable(){
			testPersistable = new TestPersistable();
			persistableList.register(testPersistable);
		}
		
		@Test
		public void saverWithEmptyRegisteredObjectCreatesEmptyFile() throws IOException{
			saver.save();
			assertTrue(file.exists());
			try (BufferedReader br = new BufferedReader(new FileReader(file))){
				assertNull(br.readLine());
			}
		}
		
		public class PersistableContentContext{
			@Before
			public void fillTestPersistable(){
				testPersistable.put("key", "value");
			}
			
			public class FileTeadingContext{
				@Test
				public void saverWithRegisteredObjectWritesContentToFile() throws IOException{
					saver.save();
					assertTrue(file.exists());
					assertWrittenLineIn("key:value");
				}
			
				@Test
				public void saverWithRegisteredObjectWritesJoinedVaraiblesToFile() throws IOException{
					testPersistable.put("a", "b");
					saver.save();
					assertTrue(file.exists());
					assertWrittenLineIn("key:value;a:b","a:b;key:value");
				}
			
				@Test
				public void multipleObjectsJoinsMaps() throws IOException{
					TestPersistable p = new TestPersistable();
					p.put("a", "b");
					persistableList.register(p);
					saver.save();
					assertTrue(file.exists());
					assertWrittenLineIn("key:value;a:b","a:b;key:value");
				}
				
				private void assertWrittenLineIn(String... results) throws IOException{
					try (BufferedReader br = new BufferedReader(new FileReader(file))){
						assertThat(br.readLine(),isIn(Arrays.asList(results)));
					}
				}
			}

			public class ExceptionContext{
				@Rule
				public ExpectedException exception = ExpectedException.none();
				
				@Test
				public void doubleKey_throws() throws IOException{
					exception.expect(SaveDataCollector.DuplicateKey.class);
					TestPersistable p = new TestPersistable();
					p.put("key", "value2");
					persistableList.register(p);
					saver.save();
				}
			}
		}

		private class TestPersistable implements Persistable {

			private Map<String, String> testMap = new HashMap<>();
			
			public void put(String key, String value) {
				testMap.put(key, value);
			}

			@Override
			public Map<String, String> getPersistedFields() {
				return testMap;
			}

			@Override
			public void restorePersistedFields(Map<String, String> data) {
			}

		}
	}
}
