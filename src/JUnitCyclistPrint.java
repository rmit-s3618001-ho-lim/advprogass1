// author: Zhipeng Li s3581721

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Before;


public class JUnitCyclistPrint {
	Cyclists cyclist1;

	@Before
	public void setUp() throws Exception {
		cyclist1 = new Cyclists ("CY01", "Cyclists", "Wayne", 34, "VIC");
	}

	@Test
	public void test() {
		assertEquals("ID:CY01  Type:Cycling  Name:Wayne  Age: 34 State: VIC Points: 0", cyclist1.print());
	}

}
