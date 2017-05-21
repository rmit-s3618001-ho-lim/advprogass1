

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class JUnitGamePrint {
	SwimmingGame sgame;
	Official sgameoff;
	@Before
	public void setUp() throws Exception {
		sgameoff = new Official("OF01", "Official", "Jimmy Firecracker", 34, "VIC");
		sgame = new SwimmingGame("S01",sgameoff);
	}

	@Test
	public void test() {
		assertEquals("ID: S01 Type:  Swimming Game", sgame.print());
	}

}