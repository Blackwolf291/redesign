package nl.ShadeBlackwolf.redesign.persist;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class SaveTest {
	
	private File file;
	private Saver saver;
	
	@Before
	public void setup(){
		file = new File("save.sav");
		saver = new Saver(new SaveDataCollector(new PersistableList()));
	}
	
	@After
	public void teardown(){
		file.delete();
	}
	
	@Test
	public void saverCreatesFile(){
		saver.save();
		assertTrue(file.exists());
	}
	
	public class ExceptionContext{
		@Rule
		public ExpectedException exception = ExpectedException.none();
		
		@Test
		public void saveDoesNotOverRide() throws IOException{
			exception.expect(Saver.SaveExists.class);
			file.createNewFile();
			saver.save();
		}
	
		@Test
		public void overwriteFailsIfNoOldFile() throws IOException{
			exception.expect(Saver.NoSuchFile.class);
			saver.overwrite("filename");
		}
	}

	public class NewFileContext{
		
		String filename = "filename";
		
		@Before
		public void fileSetup(){
			file = new File(filename + ".sav");
		}
		
		@Test
		public void saveFileNameConfigurable(){
			saver.save(filename);
			assertTrue(file.exists());
		}
		
		public class FileCreateContext{
		
			@Before
			public void createFileSetup() throws IOException{
				file.createNewFile();
				try(FileWriter fw = new FileWriter(file)){
					fw.write("a");
					fw.flush();
				}
			}
			
			@Test
			public void overwriteMovesOldFile() throws IOException{
				saver.overwrite("filename");
				assertFileWithContentMoved(filename);
			}

			@Test
			public void overwriteCreatesNewSave() throws IOException{
				saver.overwrite("filename");
				assertNewFileMade();
			}
			
			private void assertNewFileMade() throws IOException {
				assertTrue(file.exists());
				try (BufferedReader br = new BufferedReader(new FileReader(file))){
					assertEquals(null, br.readLine());
				} 
			}

			private void assertFileWithContentMoved(String filename) throws IOException {
				File oldFile = new File(filename + ".sav.old"); 
				assertTrue(oldFile.exists());
				try (BufferedReader br = new BufferedReader(new FileReader(oldFile))){
					assertEquals("a", br.readLine());
				}
			}
		}
	}
}
