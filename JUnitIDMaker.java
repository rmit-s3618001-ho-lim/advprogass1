

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class JUnitIDMaker {
	CyclingGame cycling1 = new CyclingGame();
	@Before
	public void setUp() throws Exception {
		 CyclingGame cycling1 = new CyclingGame("C01",null);
		assertEquals("C01", cycling1.getGameID());
	}

	
	@Test
	public void test1() {
		int swimcount = 1;
		cycling1.IDMaker(swimcount);
		assertEquals("C01", cycling1.getGameID());
	}
	@Test
	public void test2() {
		int swimcount = 2;
		cycling1.IDMaker(swimcount);
		assertEquals("C02", cycling1.getGameID());
	}
	@Test
	public void test3() {
		int swimcount = 3;
		cycling1.IDMaker(swimcount);
		assertEquals("C03",cycling1.getGameID());
	}
}