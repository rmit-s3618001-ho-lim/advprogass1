//author : Zhipeng Li s3581721


public class Official extends Participant {

	/* official constructors */
	public Official(String IDs, String names, int ages, String states) {
		super.ID = IDs;
		super.name = names;
		super.age = ages;
		super.state = states;
	}

	public Official() {
	}

	/* method to load all officials into an array */
	public void loadOfficials(Official[] officialList) {
		officialList[0] = new Official("OF01", "Jimmy Firecracker", 34, "Victoria");
		officialList[1] = new Official("OF02", "John Smith", 50, "W. Australia");
		officialList[2] = new Official("OF03", "Chicken McTasty", 23, "Tasmania");
	}

	/* print out the official in current game details */
	public void print() {
		String offID = getID();
		String offName = getName();
		int offAge = getAge();
		String offState = getState();
		System.out.println();
		System.out.println("--- OFFICIAL DETAILS ---");
		System.out.print(String.format("ID: %-5s Name: %-15s Age: %-5s State: %s", offID, offName, offAge, offState));
		System.out.println();
	}

	/*
	 * method to choose three winners based on the participants' race time for
	 * that game. the three winners are then given points based on their
	 * performance
	 */
	public void summariseScore(Athletes[] competitors, Game gam) {
		int min = Integer.MAX_VALUE;
		Athletes winner1 = null;
		Athletes winner2 = null;
		Athletes winner3 = null;
		/* choosing the first winner and giving him his points */
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					min = competitors[i].getTime();
					winner1 = competitors[i];

				}
			}
		}
		gam.setWinner1(winner1);
		System.out.println(
				"The first winner of this game is " + winner1.getName() + " with a race time of " + min + " seconds");
		winner1.setPoints(5);

		/* choosing the second winner and giving him his points */
		min = Integer.MAX_VALUE;
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					if (competitors[i] != winner1) {
						min = competitors[i].getTime();
						winner2 = competitors[i];
					}
				}
			}
		}
		gam.setWinner2(winner2);
		System.out.println(
				"The second winner of this game is " + winner2.getName() + " with a race time of " + min + " seconds");
		winner2.setPoints(3);

		/* choosing the last winner and giving him his points */
		min = Integer.MAX_VALUE;
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				if (competitors[i].getTime() < min) {
					if (competitors[i] != winner1 && competitors[i] != winner2) {
						min = competitors[i].getTime();
						winner3 = competitors[i];
					}
				}
			}
		}
		gam.setWinner3(winner3);
		System.out.println(
				"The third winner of this game is " + winner3.getName() + " with a race time of " + min + " seconds");
		winner3.setPoints(1);

	}

}
