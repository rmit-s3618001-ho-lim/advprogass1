// author: Cherng Ho Lim s3618001

public class Game {

	private String gameID;
	private Official officer;
	private Athletes winner1;
	private Athletes winner2;
	private Athletes winner3;

	// game constructor
	public Game(String ID, Official off) {
		gameID = ID;
		officer = off;
		winner1 = null;
		winner2 = null;
		winner3 = null;
	}

	public Game() {
	}

	// game accessor and mutator methods
	public String getGameID() {
		return this.gameID;
	}

	public Official getOfficial() {
		return this.officer;
	}

	public Athletes getWinner1() {
		return this.winner1;
	}

	public Athletes getWinner2() {
		return this.winner2;
	}

	public Athletes getWinner3() {
		return this.winner3;
	}
	
	public void setOfficial(Official theOfficial) {
		this.officer = theOfficial;
	}
	
	public void setWinner1(Athletes first) {
		this.winner1 = first;
	}

	public void setWinner2(Athletes second) {
		this.winner2 = second;
	}

	public void setWinner3(Athletes third) {
		this.winner3 = third;
	}
	
	/* choosing print method depending on type of current game */
	public String showGame(Game theGame) {
		if (theGame instanceof SwimmingGame) {
			String data = ((SwimmingGame) theGame).print();
			return data;
		} else if (theGame instanceof RunningGame) {
			String data = ((RunningGame) theGame).print();
			return data;
		} else {
			String data = ((CyclingGame) theGame).print();
			return data;
		}

	}

}

