package nl.ShadeBlackwolf.redesign.persist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Loader {

	private PersistableList persistableList;
	
	public Loader(PersistableList persistableList) {
		this.persistableList = persistableList;
	}

	public void Load(File file) {
		persistableList.publishData(parseFile(file));
	}

	private Map<String, String> parseFile(File file) {
		String rawData = readRawLineFromFile(file);
		if (rawData == null){throw new EmptyFile();}
		String[] rawEntries = rawData.split(";");
		return parseEntriesForMap(rawEntries);
		
	}

	private Map<String, String> parseEntriesForMap(String[] rawEntries) {
		Map<String, String> data = new HashMap<>();
		for (String entry:rawEntries){
			String[] dataPair = entry.split(":");
			if(dataPair.length != 2){throw new MalformedEntry();}
			if(data.containsKey(dataPair[0])){throw new DuplicateKey();}
			data.put(dataPair[0], dataPair[1]);
		}
		return data;
	}

	private String readRawLineFromFile(File file) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			return br.readLine();
		} catch (IOException e) {
			throw new LoadFailed(e);
		}
	}
	class DuplicateKey extends RuntimeException{}
	class MalformedEntry extends RuntimeException {}
	class LoadFailed extends RuntimeException{

		public LoadFailed(IOException e) {
			super(e);
		}}

	class EmptyFile extends RuntimeException {

	}
}
