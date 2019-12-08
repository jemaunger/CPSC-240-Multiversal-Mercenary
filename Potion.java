/**
 * Represents the potions that are placed on the board when the player comes in contact with "!", and associates them with their names and damage increase interger.
 * @author Jema Unger
 */
public class Potion {
	private String name;
	private int health;
	/**
	 * Constructor that gets the name and damage increase interger from the {@link PotionGenerator}.
	 * @param name represents the potions name.
	 * @param health represents the damage the potion will increase the weapons and armor damage when the player chooses.
	 */
	public Potion(String name, int health) {
		this.name = name;
		this.health = health;
	} 
	/**
	 * Gets the name of the potion from the constructor.
	 * @return the name recieved from the potion generator when the character comes in contact with the potion.
	 */
	public String getName() { return name; }
	/**
	 * Gets the amount of damage the potion will add to the item specified by the player from the constructor.
	 * @return the amount of damage the potion will increase the item the player chooses when they are prompted.
	 */
	public int getHealth() { return health; }
}

