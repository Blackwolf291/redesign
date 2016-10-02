package nl.ShadeBlackwolf.redesign.world;

public class CurrentLocationProvider {
	private Location currentLocation;
	public void setCurrentLocation(Location location){
		currentLocation = location;
	}
	public Location getCurrentLocation(){
		return currentLocation;
	}
}
