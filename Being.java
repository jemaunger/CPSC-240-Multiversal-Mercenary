//This class is the superclass for Character and Enemy. It stores a being's name, health, and damage

public class Being {
	private String name;
	private int health;
	private int damage;

	public Being() {

	}

	public int getHealth() {
		return this.health;
	}

	public String getName() {
		return this.name;
	}

	public int getDamage() {
		return this.damage;
	}
}
