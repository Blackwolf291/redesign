package nl.ShadeBlackwolf.redesign.world;

import java.util.Map;
import java.util.Map.Entry;

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
	
	public Coordinates move(Direction direction, LocationBorders locationBorders, Map<Coordinates, Connector> connectors){
		return move(direction.getXSpeed(), direction.getYSpeed(), locationBorders, connectors);
	}
	
	public Coordinates move(int dx, int dy, LocationBorders locationBorders, Map<Coordinates, Connector> connectors){
		Connector connector = getOverlappingConnector(connectors);
		if(connector != null){
			connector.changeLocation();
			return null;
		}
		if(dx == 0&&dy == 0){
			return this;
		}
		if(locationBorders.isNotAvailable(new Coordinates(getNextValue(x, dx), getNextValue(y, dy), height, width))){
			return this;
		}
		if(dx == 0){
			return new Coordinates(x, getNextValue(y, dy), height, width)
					.move(0, getDelta(dy), locationBorders, connectors);
		}
		if(dy == 0){
			return new Coordinates(getNextValue(x, dx), y, height, width)
					.move(getDelta(dx), dy, locationBorders, connectors);
		}
		Coordinates coordinates = new Coordinates(getNextValue(x, dx), getNextValue(y, dy), height, width);
		return coordinates.move(getDelta(dx), getDelta(dy), locationBorders, connectors);
	}

	private Connector getOverlappingConnector(Map<Coordinates, Connector> connectors) {
		for (Entry<Coordinates, Connector> connector:connectors.entrySet()){
			if(inCoordinates(connector.getKey())){
				return connector.getValue();
			}
		}
		return null;
	}

	private boolean inCoordinates(Coordinates con) {
		return cornerInCoordinates(con, x, y)||
				cornerInCoordinates(con, x+width, y)||
				cornerInCoordinates(con, x, y+height)||
				cornerInCoordinates(con, x+width, y+height)||
				con.cornerInCoordinates(this, con.x, con.y)||
				con.cornerInCoordinates(this, con.x+con.width, con.y)||
				con.cornerInCoordinates(this, con.x, con.y+con.height)||
				con.cornerInCoordinates(this, con.x+con.width, con.y+con.height);
	}

	private boolean cornerInCoordinates(Coordinates con, int x, int y) {
		return x>=con.x && x<=con.x+con.width && y>=con.y && y<=con.y+con.height;
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
