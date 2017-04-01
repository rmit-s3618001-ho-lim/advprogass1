
import java.util.Random;

public class SwimmingGame extends Game {

	// constructors for swimming game//
	public SwimmingGame(String ID, Official off) {
		super(ID, off);
	}

	public SwimmingGame() {
	}

	// generate a unique ID for each swimming game created//
	public String IDMaker(int count) {
		StringBuilder build = new StringBuilder();
		build.append("S");
		if (count <= 9) {
			build.append(0);
			build.append(String.valueOf(count));
		} else {
			build.append(String.valueOf(count));
		}
		return build.toString();

	}

	// randomly choosing swimmers or superathletes from respective array to
	// participate in current game
	// also ensure that the same athlete is not added to current game
	public void chooseCompetitors(Athletes[] competitors, Swimmer[] swimmers, Superathletes[] supers, int number) {
		Random rng = new Random();
		Swimmer swim = null;
		Superathletes supere = null;
		int count = 0;
		do {
			boolean sameguy = false;
			int athleteschoice = rng.nextInt(2);
			// randomly choosing a swimmer
			if (athleteschoice == 0) {
				swim = swimmers[rng.nextInt(swimmers.length)];
				if (competitors[0] == null) {
					competitors[0] = swim;
					count++;
				} else {
					for (int j = 0; j < count; j++) {
						if (swim.getID().equalsIgnoreCase(competitors[j].getID())) {
							sameguy = true;
						}
					}
					if (sameguy == true) {
						continue;
					} else {
						competitors[count] = swim;
						count++;
					}

				}
				// randomly choosing a superathlete
			} else {
				supere = supers[rng.nextInt(supers.length)];
				if (competitors[0] == null) {
					competitors[0] = supere;
					count++;
				} else {
					for (int j = 0; j < count; j++) {
						if (supere.getID().equalsIgnoreCase(competitors[j].getID())) {
							sameguy = true;
						}
					}
					if (sameguy == true) {
						continue;
					} else {
						competitors[count] = supere;
						count++;
					}
				}
			}
		} while (count < number);

	}

	// print out current game details
	public void print() {
		String gameID = getGameID();
		Official off = getOfficial();
		System.out.println("--- GAME DETAILS ---");
		System.out.printf("ID: " + gameID);
		System.out.printf("   Type:  Swimming Game");
		System.out.println();
		off.print();
	}

	// prints out swimming game details including their winners
	// prints out different message if the game was either cancelled or never
	// started
	public void printResults() {
		String gameID = getGameID();
		Official off = getOfficial();
		String type = "Swimming";
		Athletes win1 = getWinner1();
		Athletes win2 = getWinner2();
		Athletes win3 = getWinner3();
		if (win1 != null && win2 != null && win3 != null) {
			System.out.print(String.format(
					"Game ID: %-2s Type: %-9s Official: %-18s Winner 1: %-12s Winner 2: %-12s" + "Winner 3: %s", gameID,
					type, off.getName(), win1.getName(), win2.getName(), win3.getName()));
			System.out.println();
		} else {
			System.out.print(
					String.format("Game ID: %-2s Type: %-9s Official: %-20s (This game was cancelled or not started)",
							gameID, type, off.getName()));
			System.out.println();
		}
	}

}
