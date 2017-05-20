

import java.util.Random;

public class Cyclists extends Athletes{

	/* cyclists constructors */
	public Cyclists(String IDs, String types, String names, int ages, String states) {
		super(IDs, types, names, ages, states);
	}

	public Cyclists() {
	}
	
	// randomly generate cyclists' race time for current game //
	public void compete(Game gam, Athletes cycle) {
		Random RNG = new Random();
		if (gam instanceof CyclingGame) {
			int competeTime = RNG.nextInt(301) + 300;
			cycle.setTime(competeTime);
		}

	}


	// prints out all cyclists ID, name and their points //
	public String print() {
		String cycleID = getID();
		String cycleName = getName();
		String type = getType();
		int age = getAge();
		String state = getState();
		int cyclePoints = getPoints();
		
		String athleteData = String.format("ID: %-15s \t Type: %-35s \t Name: %-25s \t Age: %-15s \t "
				+ "State: %-14s \t Points: %s",
				cycleID, type, cycleName, age, state, cyclePoints);
		return athleteData;
	}



}
