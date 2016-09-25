package nl.ShadeBlackwolf.redesign.world;

public abstract class Location {
	private Coordinates coordinates = getDefaultCoordinates();
	private LocationBorders locationBorders;
	public Location(){
		locationBorders = getLocationBorders();
	}
	
	public void move(Direction direction) {
		coordinates = coordinates.move(direction, locationBorders);
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
	
}
