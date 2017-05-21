// author: Cherng Ho Lim s3618001

public class RunningGame extends Game {
	
	WrongSprintGame wrong = new WrongSprintGame() {
	};

	SameAth same = new SameAth() {
	};

	// constructors for running game//
	public RunningGame(String ID, Official off) {
		super(ID, off);
	}

	public RunningGame() {
	}

	// generate a unique ID for each running game created//
	public String IDMaker(int count) {
		StringBuilder build = new StringBuilder();
		build.append("R");
		if (count <= 9) {
			build.append(0);
			build.append(String.valueOf(count));
		} else {
			build.append(String.valueOf(count));
		}
		return build.toString();

	}

	// checks if athlete chosen can be added to game
	public int chooseCompetitors(Athletes[] competitors, Athletes[] theList, String ID) {
		try {
			int count = competitors.length;

			for (int i = 0; i < competitors.length; i++) {
				if (competitors[i] == null) {
					count--;
				}
			}
			
			//count the current number of competitors in game
			same.validate(competitors, ID, count);

			int index = -1;
			/* check if id inputted is in list of athletes */
			for (int i = 0; i < theList.length; i++) {
				if (theList[i].getID().equalsIgnoreCase(ID)) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				return -1;
			} else {

				Athletes ath = theList[index];
				wrong.validate(ath);
				for (int i = 0; i < competitors.length; i++) {
					if (competitors[i] == null) {
						competitors[count] = ath;
						break;
					}
				}
			}

			return 1;
		} catch (WrongTypeException e) {
			return 0;
		} catch (SameAthleteException e) {
			return -2;
		}
	}


	// print out current game details
		public String print() {
			String gameID = getGameID();
			String data = "ID: " + gameID+ "      Type:  Running Game";
			return data;
		}

	// prints out running game details including their winners
		public String printResults() {
			String gameID = getGameID();
			Official off = getOfficial();
			String type = "Running";
			Athletes win1 = getWinner1();
			Athletes win2 = getWinner2();
			Athletes win3 = getWinner3();
			if (win1 != null && win2 != null && win3 != null) {
				String data = String.format(
						"Game ID: %-5s \t Type: %-10s \t Official: %-35s \t Winner 1: %-30s \t"
								+ " Winner 2: %-30s \t Winner 3: %s", gameID,
						type, off.getName(), win1.getName(), win2.getName(), win3.getName());
				return data;
			}
			return null;
		}

}
