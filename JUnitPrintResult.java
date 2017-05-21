

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class JUnitPrintResult {
	CyclingGame cycling1 = new CyclingGame(); ;
	Official off1 = new Official("OF01", "Jimmy Firecracker", null, 34, "Victoria");
	Cyclists c1 = new Cyclists("CY01","Cyclist", "Wayne", 34, "Victoria");
	Cyclists c2 = new Cyclists("CY02","Cyclist", "Woody", 50, "Queensland");
	Cyclists c3 = new Cyclists("CY03","Cyclist", "Bruce", 60, "Tasmania");
	
	
	@Before
	public void setUp() throws Exception {
		cycling1 = new CyclingGame("C01", off1);
		cycling1.setWinner1(c1);
		cycling1.setWinner2(c2);
		cycling1.setWinner3(c3);
	}

	@Test
	
	public void test1() {
		assertEquals("Game ID: C01 Type: Cycling Official:Jimmy Firecracker Winner 1: Wayne Winner 2: Woody Winner 3: Burce", cycling1.printResults());
	}
	@Test 
	
	public void test2(){
		cycling1.setWinner1(null);
		assertNull(cycling1.printResults());
	}
}
