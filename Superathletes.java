

import java.util.Random;

public class Superathletes extends Athletes {

	// superathlete constructors
	public Superathletes(String IDs, String types, String names, int ages, String states) {
		super(IDs, types, names, ages, states);
	
	}

	public Superathletes() {
		
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
		} 

	}


	// prints out all superathletes ID, name and their points //
	public String print() {
		String superID = getID();
		String superName = getName();
		String type = "Superathlete";
		int age = getAge();
		String state = getState();
		int superPoints = getPoints();
		
		String athleteData = String.format("ID: %-15s \t Type: %-35s \t Name: %-25s \t Age: %-15s \t "
				+ "State: %-14s \t Points: %s",
				superID, type, superName, age, state, superPoints);
		return athleteData;
	}
}
