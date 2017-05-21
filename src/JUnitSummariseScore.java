// author: Zhipeng Li s3581721

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class JUnitSummariseScore {
	Official referee;
	Athletes athlete1;
	Athletes athlete2;
	Athletes athlete3;
	Athletes athlete4;
	Athletes[] competitors = new Athletes[8];
	Game game = new SwimmingGame("S01",referee);
	
	@Before
	public void setUp() throws Exception {
		referee = new Official("OF01", "Official", "Jimmy Firecracker", 34, "VIC");
		
		athlete1 = new Swimmer("SW01", "Swimmer", "Bob", 34, "VIC");
		athlete2 = new Swimmer("SW02", "Swimmer", "Terry", 50, "ACT");
		athlete3 = new Swimmer("SW03", "Swimmer", "Lim", 32, "NT");
		athlete4 = new Swimmer("SW04", "Swimmer", "Tim", 31, "NT");
		
		competitors[0]= athlete1;
		competitors[1]= athlete2;
		competitors[2]= athlete3;
		competitors[3]= athlete4;
		
		competitors[0].setTime(200);
		competitors[1].setTime(300);
		competitors[2].setTime(400);
		competitors[3].setTime(500);
		
				
	}

	@Test
	public void test1() {
		referee.summariseScore(competitors, game);
		assertEquals(athlete1, game.getWinner1());
	}
	
	@Test
	public void test2() {
		referee.summariseScore(competitors, game);
		assertEquals(athlete2, game.getWinner2());
	}
	@Test
	public void test3() {
		referee.summariseScore(competitors, game);
		assertEquals(athlete3, game.getWinner3());
	}
	
	@Test
	public void test4(){
		referee.summariseScore(competitors, game);
		assertEquals(5, game.getWinner1().getPoints());
	}

}
