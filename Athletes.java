
public abstract class Athletes extends Participant implements Comparable<Athletes> {

	private int points;
	private int time;

	/* athletes constructors */
	public Athletes(String IDs, String type, String names, int ages, String states) {
		super.ID = IDs;
		super.name = names;
		super.age = ages;
		super.state = states;
		super.type = type;
		points = 0;
		time = 0;
	}

	public Athletes() {
	};

	/* athletes-specific accessor and mutators methods */
	public int getPoints() {
		return this.points;
	}

	public int getTime() {
		return this.time;
	}

	public void setPoints(int pointsWon) {
		this.points = points + pointsWon;
	}

	public void setTime(int timeCom) {
		this.time = timeCom;
	}

	public abstract void compete(Game gam, Athletes ath);

	public abstract String print();

	public int compareTo(Athletes ath) {
		int compareTime = ((Athletes) ath).getTime();

		//ascending order
		return this.time - compareTime;
		
	}



}
