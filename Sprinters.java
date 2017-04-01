import java.util.Random;

public class Sprinters extends Athletes {

	/* sprinters constructors */
	public Sprinters(String IDs, String names, int ages, String states) {
		super(IDs, names, ages, states);

	}

	public Sprinters() {
	}

	// load sprinters into an array //
	public void loadSprinters(Sprinters[] sprinterList) {
		sprinterList[0] = new Sprinters("SP01", "Michael", 34, "Victoria");
		sprinterList[1] = new Sprinters("SP02", "Justin", 50, "S. Australia");
		sprinterList[2] = new Sprinters("SP03", "Tom", 40, "Victoria");
		sprinterList[3] = new Sprinters("SP04", "Jerry", 19, "NSW");
		sprinterList[4] = new Sprinters("SP05", "Bolt", 12, "Queensland");
	}

	// randomly generate sprinters' race time for current game //
	public void compete(Game gam, Athletes run) {
		Random RNG = new Random();
		if (gam instanceof RunningGame) {
			int competeTime = RNG.nextInt(11) + 10;
			run.setTime(competeTime);
		} else {
			System.out.println(run.getName() + " has entered the wrong game.");
		}

	}

	// prints out the sprinters who are participating in current game
	// information //
	public void print() {
		String sprintID = getID();
		String sprintName = getName();
		int sprintAge = getAge();
		String sprintState = getState();
		int sprintPoints = getPoints();
		System.out.print(String.format("ID: %-5s Name: %-15s Age: %-5s State: %-14s Points: %s", sprintID, sprintName,
				sprintAge, sprintState, sprintPoints));
		System.out.println("");
	}

	// prints out all sprinters ID, name and their points //
	public void printPoints() {
		String sprintID = getID();
		String sprintName = getName();
		int sprintPoints = getPoints();
		String type = "Sprinter";
		System.out.print(
				String.format("ID: %-5s Name: %-15s Type: %-14s Points: %s", sprintID, sprintName, type, sprintPoints));
		System.out.println("");
	}
}
