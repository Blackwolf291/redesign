package nl.ShadeBlackwolf.redesign.world;

public class Coordinates {
	private final int x;
	private final int y;
	private final int height;
	private final int width;
	
	public Coordinates(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}
	
	public Coordinates move(Direction direction, LocationBorders locationBorders){
		return move(direction.getXSpeed(), direction.getYSpeed(), locationBorders);
		
	}
	
	public Coordinates move(int dx, int dy, LocationBorders locationBorders){
		if(dx == 0&&dy == 0){
			return this;
		}
		if(locationBorders.isNotAvailable(new Coordinates(getNextValue(x, dx), getNextValue(y, dy), height, width))){
			return this;
		}
		if(dx == 0){
			return new Coordinates(x, getNextValue(y, dy), height, width)
					.move(0, getDelta(dy), locationBorders);
		}
		if(dy == 0){
			return new Coordinates(getNextValue(x, dx), y, height, width)
					.move(getDelta(dx), dy, locationBorders);
		}
		Coordinates coordinates = new Coordinates(getNextValue(x, dx), getNextValue(y, dy), height, width);
		return coordinates.move(getDelta(dx), getDelta(dy), locationBorders);
	}

	public int getNextValue(int value, int d){
		if (d>0){
			return value+1;
		} else if (d<0){
			return value-1;
		} else {
			return 0;
		}
	}
	
	public int getDelta(int dx){
		if (dx>0){
			return dx-1;
		} else if (dx<0){
			return dx+1;
		} else {
			return 0;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null||getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		return (x == other.getX() && y == other.getY());
	}
}
