import java.util.ArrayList;

public class Exceptions {

}

class gameExist {
	public gameExist() {
	};

	public void validate(ArrayList<Game> gameList) throws GameNotExistException {

		if (gameList.size() == 0) {
			throw new GameNotExistException("There is no game to run");
		}
	}
}

class NotEnoughAthletes {
	public NotEnoughAthletes() {
	};

	public void validate(Athletes[] compete) throws TooFewAthleteException {
		int count = 0;
		for (int i = 0; i < compete.length; i++) {
			if (compete[i] == null) {
				count++;
			}
		}
		if (count >= 5) {
			throw new TooFewAthleteException("There is not enough athletes in this game.");
		}
	}
}

class gameRunOnce {
	public gameRunOnce() {
	};

	public void validate(Game gam) throws GameHadRunException {
		if (gam.getWinner1() != null || gam.getWinner2() != null || gam.getWinner1() != null) {
			throw new GameHadRunException("The game has already been run");
		}
	}

}

class NoReferee {
	public NoReferee() {
	};

	public void validate(Game gam) throws NoRefereeException {
		if (gam.getOfficial() == null) {
			throw new NoRefereeException("There is no referee in this game");
		}
	}

}

class gameFull {
	public gameFull() {
	};

	public void validate(Athletes[] compete) throws GameFullException {
		int count = 8;
		for (int i = 0; i < compete.length; i++) {
			if (compete[i] == null) {
				count--;
			}
		}
		if (count >= 8) {
			throw new GameFullException("You cannot add more than 8 athletes in a game");
		}
	}

}


class WrongCycleGame {
	public WrongCycleGame() {
	};

	public void validate(Athletes ath) throws WrongTypeException {
		if (ath instanceof Swimmer || ath instanceof Sprinters) {
			throw new WrongTypeException("You must choose cyclists or superathletes");
		}
	}
}

class WrongSwimGame {
	public WrongSwimGame() {
	};

	public void validate(Athletes ath) throws WrongTypeException {
		if (ath instanceof Cyclists || ath instanceof Sprinters) {
			throw new WrongTypeException("You must choose swimmers or superathletes");
		}
	}
}

class WrongSprintGame {
	public WrongSprintGame() {
	};

	public void validate(Athletes ath) throws WrongTypeException {
		if (ath instanceof Swimmer || ath instanceof Cyclists) {
			throw new WrongTypeException("You must choose sprinters or superathletes");
		}
	}
}

class SameAth {
	public SameAth() {
	};

	public void validate(Athletes[] athList, String s, int count) throws SameAthleteException {
		boolean sameAth = false;
		/* check if same athlete is in game already of not */
		for (int j = 0; j < count; j++) {
			if (s.equalsIgnoreCase(athList[j].getID())) {
				sameAth = true;
			}
		}

		if (sameAth == true) {
			throw new SameAthleteException("You cannot add the same athlete into a game");
		}
	}
}
