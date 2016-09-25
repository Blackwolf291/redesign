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
	
	public boolean isNotAvailable(int x, int y, int height, int width){
		return (x<getLeft()||x+width>getRight()||y<getTop()||y+height>getBottom()||isInObstruction(x, y, height, width));
	}
	
	public boolean isInObstruction(int x, int y, int height, int width){
		for(Obstruction obs : obstructions){
			if (obs.isInObstruction(x, y, height, width)){
				return true;
			}
		}
		return false;
	}
}
