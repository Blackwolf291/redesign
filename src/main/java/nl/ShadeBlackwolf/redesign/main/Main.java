package nl.ShadeBlackwolf.redesign.main;

public class Main {
	public static void main(String... args){
		Main main = new Main();
		try{
		main.createGame();
		main.run();
		} catch (Throwable t) {
			main.gracefulShutdown();
		}
	}

	private void gracefulShutdown() {
		// TODO Auto-generated method stub
		
	}

	private void run() {
		// TODO Auto-generated method stub
		
	}

	private void createGame() {
		PersistanceFactory factory = new PersistanceFactory();
	}
}
