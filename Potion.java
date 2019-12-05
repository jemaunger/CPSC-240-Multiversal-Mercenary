public class Potion {
	private String name;
	private int health;

	//Constructor assigns the name and the amount of health it restores.
	public Potion(String name, int health) {
		this.name = name;
		this.health = health;
	}

	public String getName() { return name; }
	public int getHealth() { return health; }
}

