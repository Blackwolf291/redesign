package nl.ShadeBlackwolf.redesign.world;

import java.util.HashMap;
import java.util.Map;

public abstract class Location {
	private Coordinates coordinates = getDefaultCoordinates();
	private LocationBorders locationBorders;
	private Map<Coordinates, Connector> connectors = new HashMap<>();
	
	public Location(){
		locationBorders = getLocationBorders();
	}
	
	public void setCoordinates(Coordinates coordinates){
		this.coordinates = coordinates;
	}
	
	public void move(Direction direction) {
		coordinates = coordinates.move(direction, locationBorders, connectors);
	}
	private Coordinates getDefaultCoordinates() {
		return new Coordinates(0,0, 20, 15);
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	protected abstract LocationBorders getLocationBorders();

	public void addObstruction(Obstruction obstruction) {
		locationBorders.addObstruction(obstruction);
	}
	
	public void addConnector(Connector connector, Coordinates coordinates) {
		connectors.put(coordinates,connector);
	}
	
}
