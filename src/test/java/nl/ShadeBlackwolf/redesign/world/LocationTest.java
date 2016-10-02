package nl.ShadeBlackwolf.redesign.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static nl.ShadeBlackwolf.redesign.world.Direction.*;

@RunWith(HierarchicalContextRunner.class)
public class LocationTest {
	
	private Location location;
	
	@Before
	public void setup(){
		location = new TestLocation();
	}
	
	@Test
	public void canMoveNorthInLocation(){
		location.move(north);
		assertXY(0, -50);
	}

	@Test
	public void canMoveNorthEastInLocation(){
		location.move(northeast);
		assertXY(35, -35);
	}

	@Test
	public void canMoveEastInLocation(){
		location.move(east);
		assertXY(35, 0);
	}

	@Test
	public void canMoveSouthEastInLocation(){
		location.move(southeast);
		assertXY(30, 30);
	}

	@Test
	public void canMoveSouthInLocation(){
		location.move(south);
		assertXY(0, 30);
	}

	@Test
	public void canMoveSouthWestInLocation(){
		location.move(southwest);
		assertXY(-30, 30);
	}

	@Test
	public void canMoveWestInLocation(){
		location.move(west);
		assertXY(-50, 0);
	}

	@Test
	public void canMoveNorthWestInLocation(){
		location.move(northwest);
		assertXY(-35, -35);
	}

	public class RestrictedAccessContext{
		
		@Before
		public void addObstructionToLocation(){
			location.addObstruction(new Obstruction(new Coordinates(-40, -40, 20, 80)));
		}
		
		@Test
		public void testEnteringObstruction(){
			location.move(north);
			assertXY(0, -19);
		}

		@Test
		public void testEnteringObstructionDiagonally(){
			location.move(northeast);
			assertXY(19, -19);
		}
		
		@Test
		public void cannotSlideBetweenObstaclesTooTightToFit(){
			location.move(west);
			location.move(north);
			assertXY(-50, -19);
		}
	}
	
	public class LocationConnectorContext{
		private CurrentLocationProvider locationProvider;
		
		@Before
		public void setUpConnector(){
			locationProvider = new CurrentLocationProvider();
			locationProvider.setCurrentLocation(location);
			Connector connector = new Connector(
					new TestLocation(), new Coordinates(30, 0, 20, 15), locationProvider);
			location.addConnector(connector, new Coordinates(-50, 0, 10, 10));
		}
		
		@Test
		public void locationConnectorTest(){
			location.move(west);
			assertNotSame(location, locationProvider.getCurrentLocation());
		}
	}
	
	private void assertXY(int x, int y){
		assertEquals("x coordinate:", x, location.getCoordinates().getX());
		assertEquals("y coordinate:", y, location.getCoordinates().getY());
	}
	
	private class TestLocation extends Location {
		public LocationBorders getLocationBorders(){
			return new LocationBorders(-50, -50, 100, 100);
		}
	}
}
