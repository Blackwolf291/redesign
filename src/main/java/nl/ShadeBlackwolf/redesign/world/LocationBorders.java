package nl.ShadeBlackwolf.redesign.world;

import java.util.ArrayList;
import java.util.List;

public class LocationBorders {
	private int x;
	private int y;
	private int height;
	private int width;
	
	private List<Obstruction> obstructions = new ArrayList<>(); 
	public LocationBorders(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public int getTop() {
		return y;
	}
	
	public int getBottom(){
		return y+height;
	}
	
	public int getLeft(){
		return x;
	}
	
	public int getRight(){
		return x+width;
	}
	
	public void addObstruction(Obstruction obstruction) {
		obstructions.add(obstruction);
	}
	
	public boolean isNotAvailable(Coordinates coordinates) {
		return (coordinates.getX()<getLeft()||coordinates.getX()+coordinates.getWidth()>getRight()||coordinates.getY()<getTop()||coordinates.getY()+coordinates.getHeight()>getBottom()||isInObstruction(coordinates));
	}

	private boolean isInObstruction(Coordinates coordinates) {
		for(Obstruction obs : obstructions){
			if (obs.isInObstruction(coordinates)){
				return true;
			}
		}
		return false;
	}
}
