package nl.ShadeBlackwolf.redesign.rng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bechte.junit.runners.context.HierarchicalContextRunner;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class RandomNumberGeneratorTest {
	private RandomNumberGenerator r;
	
	@Before
	public void createRNG(){
		r = new RandomNumberGenerator();
	}
	
	public class LowestPossibleValuesContext{
		

		@Before
		public void setRandom(){
			r.setRandom(new MINRNG());;
		}
		
		
		@Test
		public void flipReturnsBoolean(){
			assertFalse(r.flip());
		}

		@Test
		public void fiveTimesTailsReturns0(){
			assertEquals(0, r.flip(5));
		}

		@Test
		public void flipTillTailscountingHeads_returns0(){
			assertEquals(0, r.flipTillTails());
		}
		
		@Test
		public void rollD6_returns1(){
			assertEquals(1, r.roll());
		}

		@Test
		public void rollD20_returns1(){
			assertEquals(1, r.roll(20));
		}
		

		@Test
		public void roll3d6_returns3(){
			assertEquals(3, r.roll(3,6));
		}
	}

	public class HighestPossibleValuesContext{
		

		@Before
		public void setRandom(){
			r.setRandom(new MAXRNG());;
		}
		
		
		@Test
		public void flipReturnsBoolean(){
			assertTrue(r.flip());
		}

		@Test
		public void fiveTimesTailsReturns0(){
			assertEquals(5, r.flip(5));
		}
		

		@Test
		public void rollD6_returns6(){
			assertEquals(6, r.roll());
		}

		@Test
		public void rollD20_returns1(){
			assertEquals(20, r.roll(20));
		}
	}
}
