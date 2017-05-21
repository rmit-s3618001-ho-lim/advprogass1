// author: Cherng Ho Lim s3618001

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class Database {

	public Database() {
	}

	/***************************** DATABASE ***********************************/
	
	// to connect to the database, returns false if connection cannot be made
	// also drop and creates participants and game results tables
	public boolean connection() {

		
		Connection connection = null;


		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:AssignDB", "sa", "123");

			if (connection != null) {
				System.out.println("Connection created successfully");

			} else {
				System.out.println("Problem with creating connection");
			}

			// creating new Participants and gameresults table
			connection.prepareStatement("DROP table participants if exists;").execute();
			connection.prepareStatement("DROP table gameResults if exists;").execute();

			connection.prepareStatement("CREATE table participants (ID varchar(10) not null, "
					+ "TYPE varchar(15) not null, NAME varchar(30) not null, AGE integer, "
					+ "STATE varchar(5) not null );").execute();

			connection.prepareStatement("CREATE table gameResults (GAMEID varchar(10) not null, "
					+ "OFFICIAL varchar(30) not null, ATHLETE varchar(30) not null, RESULT double, "
					+ "SCORE integer);").execute();

			connection.commit();

			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// insert the participants into participants database
	public void insertParticipants() {
		
		Connection connection = null;

		
		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:AssignDB", "sa", "123");

			/** INSERTING PARTICIPANTS **/
			/* Swimmers */
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SW01', 'Swimmer', 'Bob', 34 , 'VIC');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SW02', 'Swimmer', 'Terry', 50, 'ACT');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SW03', 'Swimmer', 'James', 60, 'NT');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SW04', 'Swimmer', 'Lee', 29, 'QLD');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SW05', 'Swimmer', 'Lim', 32, 'VIC');").execute();

			/* Cyclists */
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('CY01', 'Cyclists', 'Wayne', 34, 'VIC');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('CY02', 'Cyclists', 'Woody', 50, 'QLD');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('CY03', 'Cyclists', 'Bruce', 60, 'TAS');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('CY04', 'Cyclists', 'Lang', 19, 'VIC');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('CY05', 'Cyclists', 'Chris', 22, 'NSW');").execute();

			/* Sprinters */
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP01', 'Sprinters', 'Michael', 34, 'SA');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP02', 'Sprinters', 'Justin', 50, 'TAS');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP03', 'Sprinters', 'Tom', 40, 'ACT');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP04', 'Sprinters', 'Jerry', 19, 'WA');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP05', 'Sprinters', 'Bolt', 12, 'QLD');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SP06', 'Sprinters', 'Little Green', 2, 'VIC');").execute();

			/* superathletes */
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SU01', 'Super', 'SuperJohn', 34, 'VIC');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SU02', 'Super', 'SuperBob', 50, 'NSW');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('SU03', 'Super', 'SuperJim', 60, 'VIC');").execute();

			/* official */
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('OF01', 'Official', 'Jimmy Firecracker', 34, 'VIC');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('OF02', 'Official', 'John Smith', 50, 'WA');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('OF03', 'Official', 'Chicken McTasty', 23, 'TAS');").execute();
			connection.prepareStatement("INSERT into participants (id, type, name, age, state)"
					+ "VALUES ('OF04', 'Official', 'Ducky McCrispy', 54, 'TAS');").execute();

			connection.commit();

		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			System.out.println("Connection to the database has failed!");
		}

	}

	// to insert the participants into arraylist for program from database
	public void getAllParticipants(ArrayList<Swimmer> fullSwim, ArrayList<Sprinters> fullSprint,
			ArrayList<Cyclists> fullCycle, ArrayList<Superathletes> fullSuper, ArrayList<Official> fullOff) {

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;


		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:AssignDB", "sa", "123");

			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM participants");

			//input the participants into their rightful arraylist
			while (rs.next()) {
				String types = rs.getString("type");
				if (types.equalsIgnoreCase("Swimmer")) {
					Swimmer swim = new Swimmer(rs.getString("id"), rs.getString("type"), rs.getString("name"),
							rs.getInt("age"), rs.getString("state"));
					fullSwim.add(swim);
				} else if (types.equalsIgnoreCase("Cyclists")) {
					Cyclists cycle = new Cyclists(rs.getString("id"), rs.getString("type"), rs.getString("name"),
							rs.getInt("age"), rs.getString("state"));
					fullCycle.add(cycle);
				} else if (types.equalsIgnoreCase("Sprinters")) {
					Sprinters sprint = new Sprinters(rs.getString("id"), rs.getString("type"), rs.getString("name"),
							rs.getInt("age"), rs.getString("state"));
					fullSprint.add(sprint);
				} else if (types.equalsIgnoreCase("Official")) {
					Official off = new Official(rs.getString("id"), rs.getString("type"), rs.getString("name"),
							rs.getInt("age"), rs.getString("state"));
					fullOff.add(off);
				} else {
					Superathletes sup = new Superathletes(rs.getString("id"), rs.getString("type"),
							rs.getString("name"), rs.getInt("age"), rs.getString("state"));
					fullSuper.add(sup);
				}

			}

			connection.commit();

		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			System.out.println("Connection to the database has failed!");
		}

	}

	//update the game results table after a game has finished
	public void updateResults(Game game, Athletes[] competitors) {

		Connection connection = null;

		

		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:AssignDB", "sa", "123");

			String gameID = game.getGameID();
			String offName = game.getOfficial().getName();

			for (int i = 0; i < competitors.length; i++) {
				if (competitors[i] != null) {
					String athName = competitors[i].getName();
					int time = competitors[i].getTime();
					int score = 0;
					if (i == 0) {
						score = 5;
					} else if (i == 1) {
						score = 3;
					} else if (i == 2) {
						score = 1;
					}
					connection.prepareStatement(
							"INSERT into gameResults (gameid, official, athlete, result, score)" + "VALUES ('" + gameID
									+ "', '" + offName + "', '" + athName + "', '" + time + "'," + " '" + score + "');")
							.execute();
				}

			}

			System.out.println("gameResults database updated...");
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			System.out.println("Connection to the database has failed!");
		}

	}
	
	
	/***************************** TEXTFILE ***********************************/
	
	// if database fails, insert the participants into arraylist from textfile
	public boolean readParticipants(ArrayList<Swimmer> fullSwim, ArrayList<Sprinters> fullSprint,
			ArrayList<Cyclists> fullCycle, ArrayList<Superathletes> fullSuper, ArrayList<Official> fullOff) {
		int count = 0;

		try {
			Scanner read = new Scanner(new File("participants.txt"));
			do {
				try {
					// obtaining the line from textfile and splitting it
					boolean same = false;
					String line = read.nextLine();
					String[] values = line.split(", |\n");
					int age = Integer.parseInt(values[3]);

					// checks if any of the variable for participant is missing
					if (values[0].length() <= 0 || values[1].length() <= 0 || values[2].length() <= 0
							|| values[4].length() <= 0) {

						count++;
					} else {
						// if participant is swimmer
						if (values[1].equalsIgnoreCase("Swimmer")) {
							Swimmer swim = new Swimmer(values[0], values[1], values[2], age, values[4]);
							Iterator reader = fullSwim.iterator();
							// checks for duplicates
							while (reader.hasNext()) {
								Swimmer sw = (Swimmer) reader.next();
								if (sw.getID().equalsIgnoreCase(values[0])) {
									same = true;
								}
							}
							if (same == false) {
								fullSwim.add(swim);
							} else {
								count++;
							}
							// if participant is cyclists
						} else if (values[1].equalsIgnoreCase("Cyclists")) {
							Cyclists cycle = new Cyclists(values[0], values[1], values[2], age, values[4]);
							Iterator reader = fullCycle.iterator();
							while (reader.hasNext()) {
								Cyclists cy = (Cyclists) reader.next();
								if (cy.getID().equalsIgnoreCase(values[0])) {
									same = true;
								}
							}
							if (same == false) {
								fullCycle.add(cycle);
							} else {
								count++;
							}

							// if participant is sprinters
						} else if (values[1].equalsIgnoreCase("Sprinters")) {
							Sprinters sprint = new Sprinters(values[0], values[1], values[2], age, values[4]);
							Iterator reader = fullSprint.iterator();
							while (reader.hasNext()) {
								Sprinters spr = (Sprinters) reader.next();
								if (spr.getID().equalsIgnoreCase(values[0])) {
									same = true;
								}
							}
							if (same == false) {
								fullSprint.add(sprint);
							} else {
								count++;
							}
							// if participant is official
						} else if (values[1].equalsIgnoreCase("Official")) {
							Official off = new Official(values[0], values[1], values[2], age, values[4]);
							Iterator reader = fullOff.iterator();
							while (reader.hasNext()) {
								Official offe = (Official) reader.next();
								if (offe.getID().equalsIgnoreCase(values[0])) {
									same = true;
								}

							}
							if (same == false) {
								fullOff.add(off);
							} else {
								count++;
							}
							// others are considered superathletes
						} else {
							Superathletes sup = new Superathletes(values[0], values[1], values[2], age, values[4]);
							Iterator reader = fullSuper.iterator();
							while (reader.hasNext()) {
								Superathletes su = (Superathletes) reader.next();
								if (su.getID().equalsIgnoreCase(values[0])) {
									same = true;
								}
							}
							if (same == false) {
								fullSuper.add(sup);
							} else {
								count++;
							}

						}
					}

				} catch (Exception e) {
					count++; // counts number of invalid data entry
				}

			} while (read.hasNext());
			read.close();

		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			return false;
		}

		System.out.println(+count + " invalid text file entries,");
		return true;
	}

	//creates gameresults textfile and updates it with current game
	public void writeGameResults(Game game, Athletes[] competitors) {

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		String gameID = game.getGameID();
		String offName = game.getOfficial().getName();

		Athletes winner1 = game.getWinner1();
		Athletes winner2 = game.getWinner2();
		Athletes winner3 = game.getWinner3();

		FileWriter writer = null;

		try {
			//checks if textfile already exists or not
			File file = new File("gameResults.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new FileWriter(file,true);
			writer.write(gameID);
			writer.write(", " + offName);
			writer.write(", " + df.format(date) + "\r\n");

			//writing the athletes details
			for (int i = 0; i < competitors.length; i++) {
				if (competitors[i] != null) {
					writer.write(competitors[i].getID());
					writer.write(", " + competitors[i].getTime());
					if (competitors[i] == winner1) {
						writer.write(", 5 \r\n");
					} else if (competitors[i] == winner2) {
						writer.write(", 3 \r\n");
					} else if (competitors[i] == winner3) {
						writer.write(", 1 \r\n");
					} else {
						writer.write(", 0 \r\n");
					}
				}
			}
			writer.write("\r\n");
			writer.write("\r\n");
			writer.close();
			
			System.out.println("gameResults.txt updated...");

		} catch (IOException e) {
			System.out.println("Connection error");
		}

	}

}
