package nl.ShadeBlackwolf.redesign.player;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import nl.ShadeBlackwolf.redesign.Player;
import nl.ShadeBlackwolf.redesign.main.PlayerFactory;

import static nl.ShadeBlackwolf.redesign.TestUtils.*;

@RunWith(HierarchicalContextRunner.class)
public class PlayerTest {
	
	private PlayerFactory factory;
	
	@Before
	public void createPlayerFactory(){
		factory = new PlayerFactory();
	}

	@Test
	public void createPlayerTest() {
		factory.create();
		assertNotNull(factory.getPlayer());
	}

	public class LoadedPlayerContentContext{
		private Player player;
		
		@Before
		public void buildPlayer(){
			factory.restorePersistedFields(getDataMap());
			player = factory.getPlayer();
		}
		
		@Test
		public void checkRestoredMapDataIsCorrect(){
			AssertPlayerCorrect(player);
		}

		@Test
		public void checkPersistMapDataIsCorrect(){
			assertMapEquals(getDataMap(), player.getPersistedFields());
		}
		
		private void AssertPlayerCorrect(Player player) {
			assertEquals("Shade",player.getName());
		}

		private Map<String, String> getDataMap() {
			Map<String, String> map = new HashMap<>();
			map.put("name", "Shade");
			return map;
		}
	}
	
}
