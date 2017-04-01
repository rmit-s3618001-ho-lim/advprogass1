import java.util.Random;

public class Swimmer extends Athletes {

	/* swimmers constructors */
	public Swimmer(String IDs, String names, int ages, String states) {
		super(IDs, names, ages, states);
	}

	public Swimmer() {
	};

	// load swimmers into an array //
	public void loadSwimmers(Swimmer[] swimList) {
		swimList[0] = new Swimmer("SW01", "Bob", 34, "Victoria");
		swimList[1] = new Swimmer("SW02", "Terry", 50, "Queensland");
		swimList[2] = new Swimmer("SW03", "James", 60, "NSW");
		swimList[3] = new Swimmer("SW04", "Lee", 29, "NSW");
		swimList[4] = new Swimmer("SW05", "Lim", 32, "Victoria");
	}

	// randomly generate swimmers' race time for current game //
	public void compete(Game gam, Athletes swim) {
		Random RNG = new Random();
		if (gam instanceof SwimmingGame) {
			int competeTime = RNG.nextInt(101) + 100;
			swim.setTime(competeTime);
		} else {
			System.out.println(swim.getName() + " has entered the wrong game.");
		}

	}

	// prints out the swimmers who are participating in current game information
	// //
	public void print() {
		String swimID = getID();
		String swimName = getName();
		int swimAge = getAge();
		String swimState = getState();
		int swimPoints = getPoints();
		System.out.print(String.format("ID: %-5s Name: %-15s Age: %-5s State: %-14s Points: %s", swimID, swimName,
				swimAge, swimState, swimPoints));
		System.out.println("");
	}

	// prints out all swimmers ID, name and their points //
	public void printPoints() {
		String swimID = getID();
		String swimName = getName();
		int swimPoints = getPoints();
		String type = "Swimmer";
		System.out.print(
				String.format("ID: %-5s Name: %-15s Type: %-14s Points: %s", swimID, swimName, type, swimPoints));
		System.out.println("");
	}

}
