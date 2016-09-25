package nl.ShadeBlackwolf.redesign.world;

public class Obstruction {

	private int x;
	private int y;
	private int height;
	private int width;
	public Obstruction(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	public boolean isInObstruction(int x, int y, int height, int width){
		return coordinatesInObstruction(x, y, height, width) || obstructionInCoordinates(x, y, height, width);
	}
	private boolean obstructionInCoordinates(int x, int y, int height, int width) {
		return cornerInCoordinates(this.x, this.y, x, y, height, width)||
				cornerInCoordinates(this.x, this.y+this.height, x, y, height, width)||
				cornerInCoordinates(this.x+this.width, this.y+this.height, x, y, height, width)||
				cornerInCoordinates(this.x+this.width, this.y, x, y, height, width);
	}
	private boolean cornerInCoordinates(int x2, int y2, int x, int y, int height, int width) {
		return y2>y&&y2<y+height&&x2>x&&x2<x+width;
	}
	private boolean coordinatesInObstruction(int x, int y, int height, int width) {
		return cornerInObstruction(x, y)||
				cornerInObstruction(x, y+height)||
				cornerInObstruction(x+width, y+height)||
				cornerInObstruction(x+width, y);
	}
	private boolean cornerInObstruction(int x, int y) {
		return y>getTop()&&y<getBottom()&&x>getLeft()&&x<getRight();
	}
	
	private int getTop(){
		return y;
	}
	
	private int getBottom(){
		return y+height;
	}
	
	private int getLeft(){
		return x;
	}
	
	private int getRight(){
		return x+width;
	}
}
