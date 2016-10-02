package nl.ShadeBlackwolf.redesign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Map;
import java.util.Map.Entry;

public class TestUtils {

	public static void assertMapEquals(Map<String, String> expectedMap, Map<String, String> actualMap) {
		if (expectedMap.size()!=actualMap.size()){
			fail();
		}
		for (Entry<String, String> entry : expectedMap.entrySet()){
			if (actualMap.containsKey(entry.getKey())){
				assertEquals(entry.getValue(),actualMap.get(entry.getKey()));
			} else {
				fail();
			}
		}
	}

}
