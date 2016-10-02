package nl.ShadeBlackwolf.redesign.world;

public class Obstruction {

	private Coordinates coordinates;
	
	public Obstruction(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public boolean isInObstruction(Coordinates coordinates) {
		return coordinatesInObstruction(coordinates) || 
				obstructionInCoordinates(coordinates);
	}
	
	private boolean obstructionInCoordinates(Coordinates coordinates) {
		return cornerInCoordinates(getLeft(), getTop(), coordinates)||
		cornerInCoordinates(getLeft(), getBottom(), coordinates)||
		cornerInCoordinates(getRight(), getBottom(), coordinates)||
		cornerInCoordinates(getRight(), getTop(), coordinates);
	}
	private boolean cornerInCoordinates(int x, int y, Coordinates coordinates) {
		return y>coordinates.getY()&&y<=coordinates.getY()+coordinates.getHeight()&&
				x>coordinates.getX()&&x<=coordinates.getX()+coordinates.getWidth();
	}
	private boolean coordinatesInObstruction(Coordinates coordinates) {
		return cornerInObstruction(coordinates.getX(), coordinates.getY())||
		cornerInObstruction(coordinates.getX(), coordinates.getY()+coordinates.getHeight())||
		cornerInObstruction(coordinates.getX()+coordinates.getWidth(), coordinates.getY()+coordinates.getHeight())||
		cornerInObstruction(coordinates.getX()+coordinates.getWidth(), coordinates.getY());
	}
	private boolean cornerInObstruction(int x, int y) {
		return y>=getTop()&&y<=getBottom()&&x>=getLeft()&&x<=getRight();
	}
	
	private int getTop(){
		return coordinates.getY();
	}
	
	private int getBottom(){
		return coordinates.getY()+coordinates.getHeight();
	}
	
	private int getLeft(){
		return coordinates.getX();
	}
	
	private int getRight(){
		return coordinates.getX()+coordinates.getWidth();
	}
}
