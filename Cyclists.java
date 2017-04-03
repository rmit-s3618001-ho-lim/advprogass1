// author: Cherng Ho Lim s3618001


import java.util.Random;

public class Cyclists extends Athletes {

	/* cyclists constructors */
	public Cyclists(String IDs, String names, int ages, String states) {
		super(IDs, names, ages, states);
	}

	public Cyclists() {
	}

	// load cyclists into an array //
	public void loadCyclists(Cyclists[] cyclistList) {
		cyclistList[0] = new Cyclists("CY01", "Wayne", 34, "Victoria");
		cyclistList[1] = new Cyclists("CY02", "Woody", 50, "Queensland");
		cyclistList[2] = new Cyclists("CY03", "Bruce", 60, "Tasmania");
		cyclistList[3] = new Cyclists("CY04", "Lang", 19, "Victoria");
		cyclistList[4] = new Cyclists("CY05", "Chris", 22, "Victoria");
	}

	// randomly generate cyclists' race time for current game //
	public void compete(Game gam, Athletes cycle) {
		Random RNG = new Random();
		if (gam instanceof CyclingGame) {
			int competeTime = RNG.nextInt(301) + 300;
			cycle.setTime(competeTime);
		} else {
			System.out.println(cycle.getName() + " has entered the wrong game.");
		}

	}

	// prints out the cyclists who are participating in current game information
	// //
	public void print() {
		String cycleID = getID();
		String cycleName = getName();
		int cycleAge = getAge();
		String cycleState = getState();
		int cyclePoints = getPoints();
		System.out.print(String.format("ID: %-5s Name: %-15s Age: %-5s State: %-14s Points: %s", cycleID, cycleName,
				cycleAge, cycleState, cyclePoints));
		System.out.println("");
	}

	// prints out all cyclists ID, name and their points //
	public void printPoints() {
		String cycleID = getID();
		String cycleName = getName();
		int cyclePoints = getPoints();
		String type = "Cyclist";
		System.out.print(
				String.format("ID: %-5s Name: %-15s Type: %-14s Points: %s", cycleID, cycleName, type, cyclePoints));
		System.out.println("");
	}

}
