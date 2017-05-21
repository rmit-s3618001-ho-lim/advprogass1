// author: Cherng Ho Lim s3618001

import java.util.Random;

public class Swimmer extends Athletes {

	/* swimmers constructors */
	public Swimmer(String IDs, String types, String names, int ages, String states) {
		super(IDs, types, names, ages, states);
	}

	public Swimmer() {
	};


	// randomly generate swimmers' race time for current game //
	public void compete(Game gam, Athletes swim) {
		Random RNG = new Random();
		if (gam instanceof SwimmingGame) {
			int competeTime = RNG.nextInt(101) + 100;
			swim.setTime(competeTime);
		}
	}

	// prints out the swimmers who are participating in current game information
	// //
	public String print() {
		String swimID = getID();
		String swimName = getName();
		String type = getType();
		int age = getAge();
		String state = getState();
		int swimPoints = getPoints();
		
		String athleteData = String.format("ID: %-15s \t Type: %-35s \t Name: %-25s \t Age: %-15s \t "
				+ "State: %-14s \t Points: %s",
				swimID, type, swimName, age, state, swimPoints);
		return athleteData;
	}

}
