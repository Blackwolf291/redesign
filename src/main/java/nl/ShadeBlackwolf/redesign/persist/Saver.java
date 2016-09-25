package nl.ShadeBlackwolf.redesign.persist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Saver {

	private SaveDataCollector saveDataCollector;
	
	public Saver(SaveDataCollector saveDataCollector) {
		this.saveDataCollector = saveDataCollector;
	}

	public void save() {
		save("save");
	}
	
	public void save(String filename) {
		File file = new File(filename + ".sav");
		try {
			if(!file.createNewFile()){
				throw new SaveExists();
			}
			try(FileWriter fw = new FileWriter(file)){
				fw.write(saveDataCollector.getSaveData());
				fw.flush();
			}
		} catch (IOException e) {
			throw new CouldNotSave(e);
		}
	}
	
	public void overwrite(String filename) {
		File oldSave = new File(filename + ".sav");
		if (!oldSave.exists()){throw new NoSuchFile();}
		try {
			Files.move(oldSave.toPath(), new File(filename + ".sav.old").toPath(), REPLACE_EXISTING);
		} catch (IOException e) {
			throw new OldFileBackupFailed(e);
		}
		try{
			save(filename);
		} catch (Exception e){
			try {
				Files.move(new File(filename + ".sav.old").toPath(), oldSave.toPath(), REPLACE_EXISTING);
			} catch (IOException e1) {
				throw new RestoringBackupFailed(e1);
			}
		}
		
	}
	class NoSuchFile extends RuntimeException{}
	class SaveExists extends RuntimeException{}
	class RestoringBackupFailed extends RuntimeException {
		public RestoringBackupFailed(IOException e) {
			super(e);
		}
	}
	class OldFileBackupFailed extends RuntimeException {
		public OldFileBackupFailed(IOException e) {
			super(e);
		}
	}
	class CouldNotSave extends RuntimeException{
		CouldNotSave(Exception e){
			super(e);
		}
	}
}
