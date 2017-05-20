

public abstract class Participant {

	protected String ID;
	protected String name;
	protected int age;
	protected String state;
	protected String type;

	/* accessor and mutator methods for both athletes classes and official */


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
	
	public String getType() {
		return this.type;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	/*added type */

}
