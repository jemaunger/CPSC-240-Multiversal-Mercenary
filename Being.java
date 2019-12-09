import java.util.Scanner;
/**
 * Represents the super class that both {@link Character} and {@link Enemy} inherit their names, health amount, and damage.
 * @author Jema Unger, Lucas Pokrywka
 */
public class Being {
	private String name;
	private int health;
	private int damage;

	/**
	 * Empty being constructor that constructs a being for both {@link Character} and {@link Enemy} to inherit from.
	 */
	public Being() {

	}
	/**
	 * Used as a getter for retrieving the player and enemies health from {@link Character} and {@link Enemy}.
	 * @return the health of both the player and enemy the player comes in contact with.
	 */
	public int getHealth() {
		return this.health;
	}
	/**
	 * Used to get the name of the player and enemy, retrieves the players name at the beginning of the game, and the enemies name only when the player comes in contact with an ampersand.
	 * @return the name of both the player and the enemy the player comes in contact with on the board. 
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Used to get the damage of the player and enemy, retrieves both of them when the player comes in contact with an enemy and decides to battle.
	 * @return the damage amount for both the player and the enemy.
	 */
	public int getDamage() {
		return this.damage;
	}

}
