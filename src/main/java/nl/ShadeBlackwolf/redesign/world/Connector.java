package nl.ShadeBlackwolf.redesign.world;

public class Connector {

	private Location targetLocation;
	private Coordinates targetCoordinates;
	private CurrentLocationProvider locationProvider;
	
	public Connector(Location targetLocation, Coordinates targetCoordinates, CurrentLocationProvider locationProvider) {
		this.targetLocation = targetLocation;
		this.targetCoordinates = targetCoordinates;
		this.locationProvider = locationProvider;
	}

	public void changeLocation(){
		targetLocation.setCoordinates(targetCoordinates);
		locationProvider.setCurrentLocation(targetLocation);
	}
}
