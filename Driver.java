// author: Cherng Ho Lim s3618001

import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Driver extends Application {

	public Driver (){};
	/*
	 * variables used for keeping track of number of games created also used for
	 * making new ID for games
	 */

	Database db = new Database();

	private boolean dbConnect = true;
	private boolean txtConnect = true;

	/* used for creating game purposes */
	private int swimcount;
	private int runcount;
	private int cyclecount;

	Official officialchoice;
	Game newGame = new Game();

	/* exceptions object */
	NotEnoughAthletes check = new NotEnoughAthletes();
	NoReferee refcheck = new NoReferee();
	gameExist gamecheck = new gameExist();
	gameRunOnce runcheck = new gameRunOnce();
	gameFull fullcheck = new gameFull();

	ArrayList<Swimmer> fullSwim = new ArrayList<Swimmer>();
	ArrayList<Sprinters> fullSprint = new ArrayList<Sprinters>();
	ArrayList<Cyclists> fullCycle = new ArrayList<Cyclists>();
	ArrayList<Superathletes> fullSuper = new ArrayList<Superathletes>();
	ArrayList<Official> fullOff = new ArrayList<Official>();

	Official officialList[] = null;
	Swimmer swimList[] = null;
	Sprinters sprinterList[] = null;
	Cyclists cyclistList[] = null;
	Superathletes superList[] = null;

	Athletes[] fullList = null;

	Athletes[] competitors = new Athletes[8];

	ArrayList<Game> gameList = new ArrayList<Game>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*
		 * attempts to connect to database. if fails, attempts to take data from
		 * text file if both fails, exit program
		 */
		dbConnect = db.connection();
		if (dbConnect == true) {
			db.insertParticipants();
			db.getAllParticipants(fullSwim, fullSprint, fullCycle, fullSuper, fullOff);
		} else {
			System.out.println("Connection to database has failed!");
			System.out.println("Attempting to use text file instead...");
			txtConnect = db.readParticipants(fullSwim, fullSprint, fullCycle, fullSuper, fullOff);
			if (txtConnect == false) {
				System.out.println("Connection to the database and text file has failed!!");
				System.out.println("Exiting program...");
				System.exit(0);
			}
		}

		/*
		 * convert arraylist to arrays and create an array filled with all
		 * athletes
		 */
		officialList = fullOff.toArray(new Official[fullOff.size()]);

		swimList = fullSwim.toArray(new Swimmer[fullSwim.size()]);

		cyclistList = fullCycle.toArray(new Cyclists[fullCycle.size()]);

		sprinterList = fullSprint.toArray(new Sprinters[fullSprint.size()]);

		superList = fullSuper.toArray(new Superathletes[fullSuper.size()]);

		fullList = new Athletes[swimList.length + sprinterList.length + cyclistList.length + superList.length];

		System.arraycopy(swimList, 0, fullList, 0, swimList.length);

		System.arraycopy(sprinterList, 0, fullList, swimList.length, sprinterList.length);

		System.arraycopy(cyclistList, 0, fullList, sprinterList.length + swimList.length, cyclistList.length);
		System.arraycopy(superList, 0, fullList, sprinterList.length + swimList.length + cyclistList.length,
				superList.length);

		/* create window for main menu */
		Scene scene = new Scene(MainMenu(), 1280, 720);
		primaryStage.setTitle("Ozlympic");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * ----------------------------------------------------------
	 * --------------------------------------------------------- -------------
	 * MAIN MENU -----------------------------------
	 * ----------------------------------------------------------
	 * -----------------------------------------------------
	 **/

	public GridPane MainMenu() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 10.5));
		pane.setHgap(30);
		pane.setVgap(40);

		Text text1 = new Text(30, 20, "MAIN MENU");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 30));
		text1.setUnderline(true);

		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		/** Button to create a new game **/
		Button create = new Button("Create a game to run");
		create.setTextFill(Color.BLUE);
		create.setFont(Font.font(20)); 
		create.setOnAction(a -> create.getScene().setRoot(createGameMenu()));

		/** Button to start the game **/
		Button gameStart = new Button("Start the game");
		gameStart.setTextFill(Color.BLUE);
		gameStart.setFont(Font.font(20));
		gameStart.setOnAction(b -> {

			try {/*
					 * before starting game, check whether game exists,
					 * participants >3, has not been run yet and has referee.
					 * throws exception if fails any of these
					 */
				gamecheck.validate(gameList);
				check.validate(competitors);
				Game gam = gameList.get((gameList.size() - 1));
				runcheck.validate(gam);
				refcheck.validate(gam);
				System.out.println("Starting game...");
				create.getScene().setRoot(startGame(gam));

			} catch (TooFewAthleteException e) {
				lab.setText("There are not enough participants in this game. Choose option 1 to create a new game");
			} catch (NoRefereeException e) {
				lab.setText("There is no referee in this game");
			} catch (GameNotExistException e) {
				lab.setText("There is no game to run. Choose option 1 to create a new game");
			} catch (GameHadRunException e) {
				lab.setText("The game has already been run");
			}
		});

		/** Button to see details of all finished games **/
		Button gameResults = new Button("Display results of finished games");
		gameResults.setTextFill(Color.BLUE);
		gameResults.setFont(Font.font(20));
		gameResults.setOnAction(c -> gameResults.getScene().setRoot(gameResults()));

		/** Button to see details of all athletes **/
		Button athPoints = new Button("Display all athletes points");
		athPoints.setTextFill(Color.BLUE);
		athPoints.setFont(Font.font(20));
		athPoints.setOnAction(d -> athPoints.getScene().setRoot(athleteList()));

		/** Button to exit program **/
		Button exit = new Button("Exit");
		exit.setTextFill(Color.RED);
		exit.setFont(Font.font(20));
		exit.setOnAction(e -> {
			System.out.println("Exiting program");
			System.exit(0);
		});

		pane.add(text1, 3, 1);
		pane.add(create, 3, 3);
		pane.add(gameStart, 3, 4);
		pane.add(gameResults, 3, 5);
		pane.add(athPoints, 3, 6);
		pane.add(exit, 3, 7);
		pane.add(lab, 3, 8);

		return pane;
	}

	/**************************** CREATING NEW GAME *****************************************************/
	
	/*----------------------------------------------
	 * ----------------------------------------------
	 * ---------- CREATE GAME MENU------------------
	 * ---------------------------------------------
	 * ----------------------------------------------
	 */
	public GridPane createGameMenu() {

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setVgap(40);

		Text text1 = new Text(20, 20, "OZLYMPIC GAME MENU");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 30));
		text1.setUnderline(true);

		Text text2 = new Text(20, 20, "Note: Selecting a new game will cancel/finish the previous game");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 14));

		/*Button to create swimming game */
		Button swimGame = new Button("Create swimming game");
		swimGame.setTextFill(Color.BLUE);
		swimGame.setFont(Font.font(20));

		//resets competitors, creates new ID and reset official choice 
		swimGame.setOnAction(a -> {
			competitors = new Athletes[competitors.length];
			SwimmingGame swim = new SwimmingGame();
			String swimID = swim.IDMaker(swimcount);
			newGame = new SwimmingGame(swimID, null);
			officialchoice = null;
			swimcount++;
			swimGame.getScene().setRoot(swimGameMenu());
		});

		/*Button to create running game */
		Button runGame = new Button("Create running game");
		runGame.setTextFill(Color.BLUE);
		runGame.setFont(Font.font(20));
		runGame.setOnAction(a -> {
			competitors = new Athletes[competitors.length];
			RunningGame run = new RunningGame();
			String runID = run.IDMaker(runcount);
			newGame = new RunningGame(runID, null);
			officialchoice = null;
			runcount++;
			runGame.getScene().setRoot(runGameMenu());
		});

		/*Button to create cycling game */
		Button cycGame = new Button("Create cycling game");
		cycGame.setTextFill(Color.BLUE);
		cycGame.setFont(Font.font(20));
		cycGame.setOnAction(a -> {
			competitors = new Athletes[competitors.length];
			CyclingGame cyc = new CyclingGame();
			String cycID = cyc.IDMaker(cyclecount);
			newGame = new CyclingGame(cycID, null);
			officialchoice = null;
			cyclecount++;
			cycGame.getScene().setRoot(cycleGameMenu());
		});

		/*Button to return to main menu */
		Button ret = new Button("Return to previous menu");
		ret.setTextFill(Color.RED);
		ret.setFont(Font.font(20));
		ret.setOnAction(e -> ret.getScene().setRoot(MainMenu()));

		pane.add(text1, 3, 0);
		pane.add(swimGame, 3, 2);
		pane.add(runGame, 3, 3);
		pane.add(cycGame, 3, 4);
		pane.add(ret, 3, 5);
		pane.add(text2, 3, 6);

		return pane;

	}

	/*----------------------------------------------
	 * ---------------------------------------------
	 * ------------ CREATE SWIMMING GAME MENU-------
	 * ----------------------------------------------
	 */

	public GridPane swimGameMenu() {

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(40);

		Text text1 = new Text(20, 20, "SWIMMING GAME MENU");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		text1.setUnderline(true);

		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		Text text2 = new Text(20, 20,
				"Note: Upon returning to main menu," + " you cannot add athletes or officials to the game anymore");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 14));

		/*Button to add athletes*/
		Button addAth = new Button("Add Athletes");
		addAth.setTextFill(Color.BLUE);
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			try {
				/*check whether there are already 8 athletes in game */
				fullcheck.validate(competitors);
				addAth.getScene().setRoot(chooseSwimmer());
			} catch (GameFullException a) {
				lab.setText("You cannot add more than 8 athletes in a game");
			}
			
		});

		/* Button to choice an official */
		Button addOff = new Button("Add Official");
		addOff.setTextFill(Color.BLUE);/* add button to stage(window) */
		addOff.setFont(Font.font(20));
		addOff.setOnAction(e -> addOff.getScene().setRoot(chooseOfficial()));

		/* Button to return to main menu. Cannot add any more athletes or official after this */
		Button ret = new Button("Return to main menu");
		ret.setTextFill(Color.RED);/* add button to stage(window) */
		ret.setFont(Font.font(20));
		ret.setOnAction(e -> {
			gameList.add(newGame);
			System.out.println("Game successfully created");
			ret.getScene().setRoot(newGameDetails());
		});

		pane.add(text1, 3, 0);
		pane.add(addAth, 3, 1);
		pane.add(addOff, 3, 2);
		pane.add(ret, 3, 3);
		pane.add(text2, 3, 4);
		pane.add(lab, 3, 6);

		return pane;
	}

	/*-----------------------------------
	 * ------------------------------------
	 * ----- CHOOSE SWIMMER DISPLAY---------
	 * -----------------------------
	 * ------------------------------------
	 */
	public GridPane chooseSwimmer() {

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(5);
		Text text1 = new Text(20, 20, "ATHLETE LIST");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 25));
		text1.setUnderline(true);
		Text text2 = new Text(20, 20, "To add a swimmer/superathlete, enter his ID:");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
		Label lab = new Label();
		lab.setTextFill(Color.RED);/* add button to stage(window) */
		lab.setFont(Font.font(20));

		TextField addAth = new TextField();
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			String ID = addAth.getText();

			/*
			 * to check whether the athlete can be added to game and why returns
			 * to create game menu when finished
			 */
			int swimError = ((SwimmingGame) newGame).chooseCompetitors(competitors, fullList, ID);
			if (swimError == -2) {
				lab.setText("You cannot add the same athlete into a game");
			} else if (swimError == -1) {
				lab.setText("The inputted ID does not exist");
			} else if (swimError == 0) {
				lab.setText("You must choose swimmers or superathletes");
			} else {
				System.out.println("Athlete successfully added");
				addAth.getScene().setRoot(swimGameMenu());
			}
		});

		/* listing out all athletes available */
		pane.add(text1, 2, 0);
		for (int i = 0; i < fullList.length; i++) {
			Text atxt = new Text(athletesPoints(fullList[i]));
			atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 14));
			pane.add(atxt, 2, i + 1);
		}
		pane.add(text2, 2, fullList.length + 4);
		pane.add(addAth, 3, fullList.length + 4);
		pane.add(lab, 2, fullList.length + 6);

		return pane;
	}

	
	/*------------------------------------------------------------
	 * -----------------------------------------------------------
	 * ---------------- CREATE RUNNING GAME MENU -----------------
	 * -----------------------------------------------------------
	 */
	public GridPane runGameMenu() {

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(40);

		Text text1 = new Text(20, 20, "RUNNING GAME MENU");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		text1.setUnderline(true);

		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		Text text2 = new Text(20, 20,
				"Note: Upon returning to main menu," + " you cannot add athletes or officials to the game anymore");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 14));

		/*Button to add athletes*/
		Button addAth = new Button("Add Athletes");
		addAth.setTextFill(Color.BLUE);
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			try {
				fullcheck.validate(competitors);
				addAth.getScene().setRoot(chooseSprinter());
			} catch (GameFullException a) {
				lab.setText("You cannot add more than 8 athletes in a game");
			}
		});

		/*Button to choose official*/
		Button addOff = new Button("Add Official");
		addOff.setTextFill(Color.BLUE);
		addOff.setFont(Font.font(20));
		addOff.setOnAction(e -> addOff.getScene().setRoot(chooseOfficial()));

		/* Button to return to main menu. Cannot add any more athletes or official after this */
		Button ret = new Button("Return to main menu");
		ret.setTextFill(Color.RED);
		ret.setFont(Font.font(20));
		ret.setOnAction(e -> {
			gameList.add(newGame);
			System.out.println("Game successfully created");
			ret.getScene().setRoot(newGameDetails());
		});

		pane.add(text1, 3, 0);
		pane.add(addAth, 3, 1);
		pane.add(addOff, 3, 2);
		pane.add(ret, 3, 3);
		pane.add(text2, 3, 4);
		pane.add(lab, 3, 6);

		return pane;
	}

	/*---------------------------------------
	 * --------------------------------------
	 * ----- CHOOSE SPRINTER DISPLAY---------
	 * --------------------------------------
	 * --------------------------------------
	 */
	public GridPane chooseSprinter() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(5);
		Text text1 = new Text(20, 20, "ATHLETE LIST");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 25));
		text1.setUnderline(true);
		Text text2 = new Text(20, 20, "To add a sprinter/superathlete, enter his ID:");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		TextField addAth = new TextField();
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			String ID = addAth.getText();

			/*
			 * to check whether the athlete can be added to game and why.
			 * returns to create game menu when finished
			 */
			int sprintError = ((RunningGame) newGame).chooseCompetitors(competitors, fullList, ID);
			if (sprintError == -2) {
				lab.setText("You cannot add the same athlete into a game");
			} else if (sprintError == -1) {
				lab.setText("The inputted ID does not exist");
			} else if (sprintError == 0) {
				lab.setText("You must choose sprinters or superathletes");
			} else {
				System.out.println("Athlete successfully added");
				addAth.getScene().setRoot(runGameMenu());
			}
		});

		/* listing out all athletes available */
		pane.add(text1, 2, 0);
		for (int i = 0; i < fullList.length; i++) {
			Text atxt = new Text(athletesPoints(fullList[i]));
			atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 14));
			pane.add(atxt, 2, i + 1);
		}
		pane.add(text2, 2, fullList.length + 4);
		pane.add(addAth, 3, fullList.length + 4);
		pane.add(lab, 2, fullList.length + 6);

		return pane;
	}

	/*----------------------------------------------
	 * ---------------------------------------------
	 * ------------ CREATE CYCLING GAME MENU-------
	 * ----------------------------------------------
	 */

	public GridPane cycleGameMenu() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(40);

		Text text1 = new Text(20, 20, "CYCLING GAME MENU");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		text1.setUnderline(true);

		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		Text text2 = new Text(20, 20,
				"Note: Upon returning to main menu," + " you cannot add athletes or officials to the game anymore");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 14));

		/* Button to add athletes */
		Button addAth = new Button("Add Athletes");
		addAth.setTextFill(Color.BLUE);
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			try {
				fullcheck.validate(competitors);
				addAth.getScene().setRoot(chooseCyclists());
			} catch (GameFullException a) {
				lab.setText("You cannot add more than 8 athletes in a game");
			}
		});

		/* Button to choose officials*/
		Button addOff = new Button("Add Official");
		addOff.setTextFill(Color.BLUE);
		addOff.setFont(Font.font(20));
		addOff.setOnAction(e -> addOff.getScene().setRoot(chooseOfficial()));

		/* Button to return to main menu. Cannot add any more athletes or official after this */
		Button ret = new Button("Return to main menu");
		ret.setTextFill(Color.RED);
		ret.setFont(Font.font(20));
		ret.setOnAction(e -> {
			gameList.add(newGame);
			System.out.println("Game successfully created");
			ret.getScene().setRoot(newGameDetails());
		});
		pane.add(text1, 3, 0);
		pane.add(addAth, 3, 1);
		pane.add(addOff, 3, 2);
		pane.add(ret, 3, 3);
		pane.add(text2, 3, 4);
		pane.add(lab, 3, 6);

		return pane;
	}

	/*---------------------------------------
	 * --------------------------------------
	 * ----- CHOOSE CYCLISTS DISPLAY---------
	 * --------------------------------------
	 * --------------------------------------
	 */
	public GridPane chooseCyclists() {

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(5);
		Text text1 = new Text(20, 20, "ATHLETE LIST");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 25));
		text1.setUnderline(true);
		Text text2 = new Text(20, 20, "To add a cyclist/superathlete, enter his ID:");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		TextField addAth = new TextField();
		addAth.setFont(Font.font(20));
		addAth.setOnAction(e -> {
			String ID = addAth.getText();

			/*
			 * to check whether the athlete can be added to game and why.
			 * returns to create game menu when finished
			 */
			int cycleError = ((CyclingGame) newGame).chooseCompetitors(competitors, fullList, ID);
			if (cycleError == -2) {
				lab.setText("You cannot add the same athlete into a game");
			} else if (cycleError == -1) {
				lab.setText("The inputted ID does not exist");
			} else if (cycleError == 0) {
				lab.setText("You must choose cyclists or superathletes");
			} else {
				System.out.println("Athlete successfully added");
				addAth.getScene().setRoot(cycleGameMenu());
			}
		});

		/* listing out all athletes available */
		pane.add(text1, 2, 0);
		for (int i = 0; i < fullList.length; i++) {
			Text atxt = new Text(athletesPoints(fullList[i]));
			atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 14));
			pane.add(atxt, 2, i + 1);
		}
		pane.add(text2, 2, fullList.length + 4);
		pane.add(addAth, 3, fullList.length + 4);
		pane.add(lab, 2, fullList.length + 6);

		return pane;
	}

	/*
	 * =========================================================================
	 * decides which print method to use depending on athletes type
	 * 
	 */
	public String athletesPoints(Athletes ath) {
		if (ath instanceof Swimmer) {
			String data = ((Swimmer) ath).print();
			return data;
		} else if (ath instanceof Sprinters) {
			String data = ((Sprinters) ath).print();
			return data;
		} else if (ath instanceof Cyclists) {
			String data = ((Cyclists) ath).print();
			return data;
		} else if (ath instanceof Superathletes) {
			String data = ((Superathletes) ath).print();
			return data;
		}

		return null;
	}

	/*-------------------------------------
	 * ------------------------------------
	 * ----- CHOOSING OFFICIAL MENU--------
	 * ------------------------------------
	 */
	public GridPane chooseOfficial() {
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setHgap(10);
		pane.setVgap(5);

		Text text1 = new Text(20, 20, "OFFICIAL LIST");
		text1.setFont(Font.font("Courier", FontWeight.BOLD, 25));
		text1.setUnderline(true);

		Text text2 = new Text(20, 20, "To add an official, enter his ID:");
		text2.setFont(Font.font("Courier", FontWeight.NORMAL, 23));

		Label lab = new Label();
		lab.setTextFill(Color.RED);
		lab.setFont(Font.font(20));

		TextField addOff = new TextField();
		addOff.setFont(Font.font(20));
		addOff.setOnAction(e -> {
			String ID = addOff.getText();
			officialchoice = chooseOfficial(ID);
			//checks whether text inputted matches official ID
			//returns to create game menu after finish adding
			if (officialchoice == null) {
				lab.setText("The inputted ID does not exist");
			} else {
				newGame.setOfficial(officialchoice);
				System.out.println("Official successfully added");
				if (newGame instanceof SwimmingGame) {
					addOff.getScene().setRoot(swimGameMenu());
				} else if (newGame instanceof RunningGame) {
					addOff.getScene().setRoot(runGameMenu());
				} else {
					addOff.getScene().setRoot(cycleGameMenu());
				}

			}
		});

		pane.add(text1, 2, 0);
		for (int i = 0; i < officialList.length; i++) {
			Text atxt = new Text(officialList[i].print());
			atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 14));
			pane.add(atxt, 2, i + 1);
		}

		pane.add(text2, 2, officialList.length + 4);
		pane.add(addOff, 3, officialList.length + 4);
		pane.add(lab, 2, officialList.length + 5);

		return pane;
	}

	/*----------------------------------------------------------
	 * ---------------------------------------------------------
	 * ---------- TO DISPLAY NEW GAME CREATED DETAILS ----------
	 * ---------------------------------------------------------
	 */
	public GridPane newGameDetails() {
		/* initializing the text and button for game details display */
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(30);
		pane.setVgap(5);
		Text gameText = new Text(20, 20, "CREATED GAME DETAILS");
		gameText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		gameText.setUnderline(true);
		Text offText = new Text(20, 20, "OFFICIAL");
		offText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		offText.setUnderline(true);
		Text athText = new Text(20, 20, "GAME PARTICIPANTS");
		athText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		athText.setUnderline(true);
		Button ret = new Button("Return to Main Menu");
		ret.setTextFill(Color.BLUE);
		ret.setFont(Font.font(15));
		ret.setOnAction(e -> ret.getScene().setRoot(MainMenu()));

		/* adding the text and button to display */
		pane.add(gameText, 0, 0);
		Text gtxt = new Text(newGame.showGame(newGame));
		gtxt.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
		pane.add(gtxt, 0, 1);
		pane.add(offText, 0, 4);

		/* checks whether official has been chosen for game or not */
		if (newGame.getOfficial() == null) {
			Text otxt = new Text("No official in this game");
			otxt.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
			pane.add(otxt, 0, 5);
		} else {
			Text otxt = new Text(newGame.getOfficial().print());
			otxt.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
			pane.add(otxt, 0, 5);
		}

		/* prints competitors in game */
		pane.add(athText, 0, 8);
		for (int i = 0; i < competitors.length; i++) {
			int count = i + 1;
			if (competitors[i] != null) {
				Text atxt = new Text("<" + count + ">" + athletesPoints(competitors[i]));
				atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
				pane.add(atxt, 0, i + 9);
			} else {
				Text atxt = new Text("<" + count + "> No participant");
				atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
				pane.add(atxt, 0, i + 9);
			}
		}

		pane.add(ret, 0, competitors.length + 16);
		return pane;

	}

	/* Choosing an official method */
	public Official chooseOfficial(String offID) {

		int index = -1;
		/* check if id inputted is in list of official */
		for (int i = 0; i < officialList.length; i++) {
			if (officialList[i].getID().equalsIgnoreCase(offID)) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			return null;
		} else {
			Official off = officialList[index];
			return off;

		}

	}

	/************************************ RUNNING THE GAME  **********************************************/
	
	public GridPane startGame(Game theGame) {

		/**COMPETE METHOD **/
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				competitors[i].compete(theGame, competitors[i]);
			}
		}
		Official referee = theGame.getOfficial();
		/** DECIDING WHO IS THE WINNER **/
		referee.summariseScore(competitors, theGame);
		/** UPDATING BOTH GAME RESULTS TABLE AND GAME RESULTS TEXT FILE **/
		db.updateResults(theGame, competitors);
		db.writeGameResults(theGame, competitors);

		/* initializing the text and button for game details display */
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 10.5));
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(30);
		pane.setVgap(5);
		Text resultText = new Text(20, 20, "GAME RESULTS");
		resultText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		resultText.setUnderline(true);

		Button ret = new Button("Return to Main Menu");
		ret.setTextFill(Color.BLUE);
		ret.setFont(Font.font(15));
		ret.setOnAction(e -> ret.getScene().setRoot(MainMenu()));

		/* adding the text and button to display */
		pane.add(resultText, 0, 0);
		Text gtxt = new Text(theGame.getOfficial().currentGamePrint(theGame));
		gtxt.setFont(Font.font("Courier", FontWeight.NORMAL, 25));
		pane.add(gtxt, 0, 1);

		/*prints out the results of the finished current game */
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] != null) {
				String s = theGame.getOfficial().printResults(competitors[i], theGame);
				Text rst = new Text(s);
				rst.setFont(Font.font("Courier", FontWeight.NORMAL, 20));
				pane.add(rst, 0, i + 2);
			}
		}

		pane.add(ret, 0, competitors.length + 16);
		return pane;

	}

	/********************************* PRINTING ALL FINISHED GAMES ********************************************/
	
	public GridPane gameResults() {
		Iterator reader = gameList.iterator();

		/* initializing the text and button for game details display */
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 10.5));
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(30);
		pane.setVgap(1);
		Text resultText = new Text(20, 20, "FINISHED GAMES RESULTS");
		resultText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		resultText.setUnderline(true);

		Button ret = new Button("Return to Main Menu");
		ret.setTextFill(Color.BLUE);
		ret.setFont(Font.font(15));
		ret.setOnAction(e -> ret.getScene().setRoot(MainMenu()));

		/* adding the text and button to display */
		pane.add(resultText, 1, 0);

		int count = 1;
		while (reader.hasNext()) {
			Game theGame = (Game) reader.next();
			String s = gameCheck(theGame);
			if (s != null) {
				Text rst = new Text(s);
				rst.setFont(Font.font("Courier", FontWeight.NORMAL, 13));
				pane.add(rst, 1, count);
				count++;
			}
		}

		pane.add(ret, 0, 0);
		return pane;
	}
	
	/*check the game type to decide which print game method to use */

	public String gameCheck(Game theGame) {

		if (theGame instanceof SwimmingGame) {
			String data = ((SwimmingGame) theGame).printResults();
			return data;
		} else if (theGame instanceof RunningGame) {
			String data = ((RunningGame) theGame).printResults();
			return data;
		} else {
			String data = ((CyclingGame) theGame).printResults();
			return data;
		}

	}

/**************************************** PRINTING ATHLETES POINTS **************************************/
	public GridPane athleteList() {
		/* initializing the text and button for game details display */
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(15.5, 10.5, 15.5, 45.5));
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(30);
		pane.setVgap(2);
		Text gameText = new Text(20, 20, "ATHLETE POINTS");
		gameText.setFont(Font.font("Courier", FontWeight.BOLD, 28));
		gameText.setUnderline(true);
		Button ret = new Button("Return to Main Menu");
		ret.setTextFill(Color.BLUE);/* add button to stage(window) */
		ret.setFont(Font.font(15));
		ret.setOnAction(e -> ret.getScene().setRoot(MainMenu()));

		/* adding the text and button to display */
		pane.add(gameText, 1, 0);
		for (int i = 0; i < fullList.length; i++) {
			if (fullList[i] != null) {
				Text atxt = new Text(athletesPoints(fullList[i]));
				atxt.setFont(Font.font("Courier", FontWeight.NORMAL, 15));
				pane.add(atxt, 1, i + 1);
			}
		}

		pane.add(ret, 0, 0);
		return pane;

	}

}
