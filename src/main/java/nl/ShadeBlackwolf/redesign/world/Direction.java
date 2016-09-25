package nl.ShadeBlackwolf.redesign.world;

public enum Direction {
	north(0,-50),
	northeast(35, -35),
	east(50, 0),
	southeast(35, 35),
	south(0, 50),
	southwest(-35, 35),
	west(-50, 0),
	northwest(-35, -35);

	private final int xSpeed;
	private final int ySpeed;
	
	private Direction(int xSpeed, int ySpeed){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public int getYSpeed() {
		return ySpeed;
	}

	public int getXSpeed() {
		return xSpeed;
	}

	
}
