// author: Cherng Ho Lim s3618001


import java.util.Random;

public class Superathletes extends Athletes {

	// superathlete constructors
	public Superathletes(String IDs, String names, int ages, String states) {
		super(IDs, names, ages, states);
	
	}

	public Superathletes() {
		
	}

	// load superathletes into an array //
	public void loadSuperAthletes(Superathletes[] superList) {
		superList[0] = new Superathletes("SU01", "SuperJohn", 34, "Victoria");
		superList[1] = new Superathletes("SU02", "SuperBob", 50, "NSW");
		superList[2] = new Superathletes("SU03", "SuperJim", 60, "Victoria");
	}

	// randomly generate superathletes' race time for current game //
	public void compete(Game gam, Athletes superer) {
		Random RNG = new Random();
		if (gam instanceof SwimmingGame) {
			int competeTime = RNG.nextInt(101) + 100;
			superer.setTime(competeTime);
		} else if (gam instanceof RunningGame) {
			int competeTime = RNG.nextInt(11) + 10;
			superer.setTime(competeTime);
		} else if (gam instanceof CyclingGame) {
			int competeTime = RNG.nextInt(301) + 500;
			superer.setTime(competeTime);
		} else {
			System.out.println(superer.getName() + " has entered the wrong game.");
		}

	}

	// prints out the superathletes who are participating in current game information
		// //
	public void print() {
		String superID = getID();
		String superName = getName();
		int superAge = getAge();
		String superState = getState();
		int superPoints = getPoints();
		System.out.print(String.format("ID: %-5s Name: %-15s Age: %-5s State: %-14s Points: %s", superID, superName,
				superAge, superState, superPoints));
		System.out.println("");
	}
	
	// prints out all superathletes ID, name and their points //
	public void printPoints() {
		String superID = getID();
		String superName = getName();
		int superPoints = getPoints();
		String type = "Superathlete";
		System.out.print(
				String.format("ID: %-5s Name: %-15s Type: %-14s Points: %s", superID, superName, type, superPoints));
		System.out.println("");
	}
}
