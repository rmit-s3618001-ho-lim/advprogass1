//author: Cherng Ho Lim s3618001



import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	
	/* variables used for keeping track of number of games created
	 * also used for making new ID for games */
	 
	private int swimcount;
	private int runcount;
	private int cyclecount;
	private String winpredict;

	Official officialList[] = new Official[3];
	Swimmer swimList[] = new Swimmer[5];
	Sprinters sprinterList[] = new Sprinters[5];
	Cyclists cyclistList[] = new Cyclists[5];
	Superathletes superList[] = new Superathletes[3];

	Athletes competitors[] = new Athletes[8];

	ArrayList<Game> gameList = new ArrayList<Game>();

	/*
	 * load in the official, swimmer, sprinters, cyclists and superathletes
	 * before showing the main menu
	 */
	public void runDriver() {
		Official offload = new Official();
		offload.loadOfficials(officialList);

		Swimmer swimload = new Swimmer();
		swimload.loadSwimmers(swimList);

		Sprinters sprintload = new Sprinters();
		sprintload.loadSprinters(sprinterList);

		Cyclists cycleload = new Cyclists();
		cycleload.loadCyclists(cyclistList);

		Superathletes supload = new Superathletes();
		supload.loadSuperAthletes(superList);

		while (true) {
			menu();
		}

	}

	/*
	 * ==============================================================
	 * ==================== MAIN MENU =================================
	 * =========================== =====================================
	 */

	public void menu() {
		Scanner scan = new Scanner(System.in);

		do {
			System.out.println();
			System.out.println("====== Ozlympic Menu ======");
			System.out.println("<1> Create a game to run");
			System.out.println("<2> Predict the winner of the game");
			System.out.println("<3> Start the game");
			System.out.println("<4> Display the final results of all games");
			System.out.println("<5> Display the points of all athletes");
			System.out.println("<6> Exit");

			try {
				String s = scan.nextLine();
				int input = Integer.parseInt(s);

				if (input == 1) {
					newGame();
				} else if (input == 2) {
					predictMenu();
				} else if (input == 3) {
					/*
					 * Check whether the user has already chosen a game or not
					 * Only runs game if a game has been chosen and the game has
					 * 4 or more participants
					 */
					if (gameList.size() == 0) {
						System.out.println("There is no game to run.");
						System.out.println("Choose option 1 to create a new game");
					} else {
						int count = 0;
						for (int i = 0; i < competitors.length; i++) {
							if (competitors[i] == null) {
								count++;
							}
						}
						if (count >= 5) {
							System.out.println("There are not enough participants in this game");
							System.out.println("Choose option 1 to create a new game");
						} else {
							System.out.println("Starting game...");
							Game gam = gameList.get((gameList.size() - 1));
							startGame(gam);
						}
					}

				} else if (input == 4) {
					gameResults();
				} else if (input == 5) {
					athletesPoints();
				} else if (input == 6) {
					System.out.println("Exiting program...");
					System.exit(0);
					scan.close();
				} else {
					System.out.println("Please choose an option between 1-6");
				}
			} catch (Exception e) {
				System.out.println("Please input a number");
			}

		} while (true);

	}

	/************************************************************************/
	/* to create a new game when the user chooses option 1 */
	/************************************************************************/

	public void newGame() {
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("====== Game Menu ======");
			System.out.println("<1> Swimming");
			System.out.println("<2> Cycling");
			System.out.println("<3> Running");
			System.out.println("<4> Return to previous menu");
			System.out.println();
			System.out.println("Note: Selecting a new game will cancel/finish the previous game");

			try {

				String s = scan.nextLine();
				int input = Integer.parseInt(s);
				/*
				 * creating a new Swimming Game and its ID, and choosing its
				 * official and participants. Also resets user's prediction
				 */
				if (input == 1) {
					winpredict = null;
					competitors = new Athletes[competitors.length];
					SwimmingGame swim = new SwimmingGame();
					String swimID = swim.IDMaker(swimcount);
					Official officialchoice = officialList[chooseOfficial(officialList)];
					Game newGame = new SwimmingGame(swimID, officialchoice);
					showGame(newGame);
					gameList.add(newGame);
					competitorsNumber(newGame);
					displayCompetitors();
					swimcount++;
					menu();
					/*
					 * creating a new Cycling Game and its ID, and choosing its
					 * official and participants. Also resets user's prediction
					 */
				} else if (input == 2) {
					winpredict = null;
					competitors = new Athletes[competitors.length];
					CyclingGame cycle = new CyclingGame();
					String cycleID = cycle.IDMaker(cyclecount);
					Official officialchoice = officialList[chooseOfficial(officialList)];
					Game newGame = new CyclingGame(cycleID, officialchoice);
					showGame(newGame);
					gameList.add(newGame);
					competitorsNumber(newGame);
					displayCompetitors();
					cyclecount++;
					menu();
					/*
					 * creating a new Running Game and its ID, and choosing its
					 * official and participants. Also resets user's prediction
					 */
				} else if (input == 3) {
					winpredict = null;
					competitors = new Athletes[competitors.length];
					RunningGame run = new RunningGame();
					String runID = run.IDMaker(runcount);
					Official officialchoice = officialList[chooseOfficial(officialList)];
					Game newGame = new RunningGame(runID, officialchoice);
					showGame(newGame);
					gameList.add(newGame);
					competitorsNumber(newGame);
					displayCompetitors();
					runcount++;
					menu();
					/*
					 * return to the previous menu
					 */
				} else if (input == 4) {
					System.out.println("Returning to previous menu...");
					menu();
				} else {
					System.out.println("Please choose an option between 1-4");
				}
			} catch (Exception e) {
				System.out.println("Please choose an option between 1-4");
			}

		} while (true);

	}

	/* randomly choosing an official */
	public int chooseOfficial(Official[] List) {
		Random rng = new Random();
		int offChoice = rng.nextInt(List.length);
		return offChoice;
	}

	/* randomly choosing the number of participants in current game */
	public void competitorsNumber(Game theGame) {
		Random rng = new Random();
		int number = 2 + rng.nextInt(7);
		if (theGame instanceof SwimmingGame) {
			((SwimmingGame) theGame).chooseCompetitors(competitors, swimList, superList, number);
		} else if (theGame instanceof RunningGame) {
			((RunningGame) theGame).chooseCompetitors(competitors, sprinterList, superList, number);
		} else {
			((CyclingGame) theGame).chooseCompetitors(competitors, cyclistList, superList, number);
		}

	}

	/* choosing print method depending on type of current game */
	public void showGame(Game theGame) {
		if (theGame instanceof SwimmingGame) {
			((SwimmingGame) theGame).print();
		} else if (theGame instanceof RunningGame) {
			((RunningGame) theGame).print();
		} else {
			((CyclingGame) theGame).print();
		}

	}

	/*
	 * choosing print method depending on type of participants in current game
	 * prints out "No participant" if there is no participant in certain slots
	 */
	public void displayCompetitors() {
		System.out.println();
		System.out.println("--- GAME PARTICIPANTS ---");
		for (int i = 0; i < competitors.length; i++) {
			int count = i + 1;
			if (competitors[i] instanceof Swimmer) {
				System.out.printf("<" + count + "> ");
				((Swimmer) competitors[i]).print();
			} else if (competitors[i] instanceof Sprinters) {
				System.out.printf("<" + count + "> ");
				((Sprinters) competitors[i]).print();
			} else if (competitors[i] instanceof Superathletes) {
				System.out.printf("<" + count + "> ");
				((Superathletes) competitors[i]).print();
			} else if (competitors[i] instanceof Cyclists) {
				System.out.printf("<" + count + "> ");
				((Cyclists) competitors[i]).print();
			} else {
				System.out.printf("<" + count + "> ");
				System.out.println("No participant");
			}

		}
		System.out.println();
	}

	/*
	 * =========================================================================
	 * ================ /* prints out participant list for current game and
	 * prompts users to choose their winner
	 * =========================================================================
	 * =======
	 */
	public void predictMenu() {
		Scanner scan = new Scanner(System.in);

		do {
			displayCompetitors();
			System.out.println("Choose an option from 1-8 to predict your winner for" + " this game");
			System.out.println("Press <9> to return to previous menu");

			try {

				String s = scan.nextLine();
				int input = Integer.parseInt(s);

				if (input > 0 && input < 9) {
					if (competitors[input - 1] == null) {
						System.out.println("There is no participant for that option. Please choose another option");
					} else {
						winpredict = competitors[input - 1].getName();
						System.out.println("You have chosen " + competitors[input - 1].getName() + " as"
								+ " your winner. Good Luck!!");
						menu();
					}
				} else if (input == 9) {
					System.out.println("Returning to previous menu...");
					menu();
				} else {
					System.out.println("Please choose an option between 1-9");
				}
			} catch (Exception e) {
				System.out.println("Please input a number");
			}

		} while (true);

	}

	/*
	 * ================================================= ================== /*
	 * =====================starting the game =================
	 * ======================================
	 */
	public void startGame(Game theGame) {
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				competitors[i].compete(theGame, competitors[i]);
			}
		}
		Official referee = theGame.getOfficial();
		referee.summariseScore(competitors, theGame);
		congratulations(theGame, winpredict);

	}

	/*
	 * informs user whether they predicted the winner correctly or not
	 * display a different message if a winner was not predicted
	 */
	public void congratulations(Game theGame, String predict) {
		Athletes first = theGame.getWinner1();
		Athletes second = theGame.getWinner2();
		Athletes third = theGame.getWinner3();
		System.out.println();
		if (predict != null) {
			System.out.println("You have predicted " + predict + " to be the winner.");
			if (predict.equalsIgnoreCase(first.getName())) {
				System.out.println(first.getName() + " is the first winner. Congratulations!!!");
				System.out.println("You have predicted the first winner correctly!!");
				System.out.println("CONGRATULATIONS...!!!");
			} else if (predict.equalsIgnoreCase(second.getName())) {
				System.out.println(second.getName() + " is the second winner.");
				System.out.println("You have predicted the second winner correctly!!");
				System.out.println("CONGRATULATIONS..!!");
			} else if (predict.equalsIgnoreCase(third.getName())) {
				System.out.println(third.getName() + " is the third winner.");
				System.out.println("You have predicted the third winner correctly!!");
				System.out.println("CONGRATULATIONS!");
			} else {
				System.out.println("You did not predict any of the winners correctly");
				System.out.println("Better luck next time");
			}
		} else {
			System.out.println("You did not choose to predict a winner...");
		}

	}

	/*
	 * =========================================================================
	 * printing out all games details and their winners depending on what type
	 * =======================================================================
	 */
	public void gameResults() {
		Iterator reader = gameList.iterator();
		System.out.println();
		System.out.println("==== GAME LIST =====");
		while (reader.hasNext()) {
			Game theGame = (Game) reader.next();
			if (theGame instanceof SwimmingGame) {
				((SwimmingGame) theGame).printResults();
			} else if (theGame instanceof RunningGame) {
				((RunningGame) theGame).printResults();
			} else {
				((CyclingGame) theGame).printResults();
			}
		}
	}
	/*
	 * =========================================================================
	 * /* prints out all athletes details and their points depending on what
	 * type
	 * ========================================================================
	 */

	public void athletesPoints() {
		System.out.println();
		System.out.println("--- ATHLETES POINTS ---");
		for (int i = 0; i < swimList.length; i++) {
			swimList[i].printPoints();
		}
		for (int i = 0; i < sprinterList.length; i++) {
			sprinterList[i].printPoints();
		}
		for (int i = 0; i < cyclistList.length; i++) {
			cyclistList[i].printPoints();
		}
		for (int i = 0; i < superList.length; i++) {
			superList[i].printPoints();
		}
		System.out.println();
	}
}
