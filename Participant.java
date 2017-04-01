
public abstract class Participant {

	protected String ID;
	protected String name;
	protected int age;
	protected String state;

	/* accessor methods for both athletes classes and official */

	public String getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public String getState() {
		return this.state;
	}

}
