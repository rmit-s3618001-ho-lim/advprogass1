


import java.util.Random;

public class Sprinters extends Athletes {

	/* sprinters constructors */
	public Sprinters(String IDs, String types, String names, int ages, String states) {
		super(IDs, types, names, ages, states);

	}

	public Sprinters() {
	}


	// randomly generate sprinters' race time for current game //
	public void compete(Game gam, Athletes run) {
		Random RNG = new Random();
		if (gam instanceof RunningGame) {
			int competeTime = RNG.nextInt(11) + 10;
			run.setTime(competeTime);
		} 

	}

	// prints out the sprinters who are participating in current game
	// information //
	public String print() {
		String sprintID = getID();
		String sprintName = getName();
		String type = getType();
		int age = getAge();
		String state = getState();
		int sprintPoints = getPoints();
		
		String athleteData = String.format("ID: %-15s \t Type: %-35s \t Name: %-25s \t Age: %-15s \t "
				+ "State: %-14s \t Points: %s",
				sprintID, type, sprintName, age, state, sprintPoints);
		return athleteData;
	}
}
