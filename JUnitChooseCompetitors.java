

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class JUnitChooseCompetitors {
	CyclingGame cycling1 = new CyclingGame();
	Athletes[] competitors = new Athletes[5];
	Athletes[] theList = new Athletes[6];
	Athletes c1;
	Athletes c2;
	Athletes c3;
	Athletes c4;
	Athletes c5;
	Athletes c6;
	Athletes c7;
	Athletes c8;
	
	@Before
	public void setUp() throws Exception {
		c1 = new Cyclists("CY01", "Cyclists", "Wayne", 34, "VIC");
		c2 = new Cyclists("CY02", "Cyclists", "Woody", 50, "QLD");
		c3 = new Cyclists("CY03", "Cyclists", "Bruce", 60, "TAS");
		c4 = new Cyclists("CY04", "Cyclists", "Lang", 19, "VIC");
		c5 = new Cyclists("CY05", "Cyclists", "Chris", 22, "NSW");
		c6 = new Swimmer("SW01", "Swimmer", "SuperJohn", 34, "VIC");
		c7 = new Superathletes("SU02", "Super", "SuperBob", 50," NSW");
		competitors[0] = c1;
		competitors[1] = c2;
		competitors[2] = c3;
		competitors[3] = c4;
		competitors[4] = null;
		
		
		theList[0] = c1;
		theList[1] = c2;
		theList[2] = c3;
		theList[3] = c4;
		theList[4] = c5;
		theList[5] = c6;
		
		
		
	}
	
	@Test
	public void test0() {
		assertEquals(0, cycling1.chooseCompetitors(competitors, theList, "CY05"));
		
	}

	@Test(expected = WrongTypeException.class)
	public void test1() {
		assertEquals(0, cycling1.chooseCompetitors(competitors, theList, "SW01"));
		
	}
	
	@Test(expected = SameAthleteException.class)
	public void test2() {
		assertEquals(-2, cycling1.chooseCompetitors(competitors, theList, "CY01"));
	}



}
