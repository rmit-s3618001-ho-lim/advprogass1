// author: Zhipeng Li s3581721

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;


public class JUnitCyclistsCompete {
	Cyclists cycle1;
	Official off;
	Game cgame = new CyclingGame("C01", off);
	
	@Before
	public void setUp() throws Exception {
		cycle1 = new Cyclists("CY02", "Cyclists", "Woody", 50, "QLD");
	}

//Test whether compete method can create compete time for athlete.
	@Test
	public void test() {
		cycle1.compete(cgame , cycle1);
		assertTrue(cycle1.getTime()!=0);
	}
	
}
	