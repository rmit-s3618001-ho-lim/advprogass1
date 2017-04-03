//author : Zhipeng Li s3581721


public abstract class Athletes extends Participant {

	private int points;
	private int time;

	/* athletes constructors */
	public Athletes(String IDs, String names, int ages, String states) {
		super.ID = IDs;
		super.name = names;
		super.age = ages;
		super.state = states;
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

	public abstract void print();

}
