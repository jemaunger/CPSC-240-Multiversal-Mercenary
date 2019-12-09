import java.util.Random;
import java.util.ArrayList;
/**
 * Class is used to randomly generate and store enemies to be spread throughout the board, associated with the ampersand character.
 * @author Ethan Pearson
 */
public class EnemyGenerator {
	private Random rng = new Random();
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	/**
	 * Adds all of the enemies that can be randomly generated to the enemyList arraylist, giving them a name, damage, and health amount.
	 */
	EnemyGenerator(){
		enemyList.add(new Enemy("Goblin",  10, 25));
		enemyList.add(new Enemy("Demon",10, 50));
		enemyList.add(new Enemy("Thanos", 50, 75));
		enemyList.add(new Enemy("Voldemort", 50, 125));
		enemyList.add(new Enemy("Ogre", 25,100));
		enemyList.add(new Enemy("Evil Wizard", 25, 150));
		enemyList.add(new Enemy("Alien", 10,25));
		enemyList.add(new Enemy("Dragon", 50, 200));
		enemyList.add(new Enemy("Godzilla", 50, 200));
		enemyList.add(new Enemy("Night Walker", 50,150));
		enemyList.add(new Enemy("Alaskan Bull Worm", 25,40));
		enemyList.add(new Enemy("Bob-omb",10,10));
		enemyList.add(new Enemy("Zombie",10,40));
		enemyList.add(new Enemy("Stormtrooper", 25, 50));
		enemyList.add(new Enemy("Joker", rng.nextInt(75), 100));

	}
	/**
	 * Randomly generates enemies from the arraylist enemyList choosing from the 15 enemies and spreading them throughout the board.
	 * @return the enemies that will be randomly generated and placed on the board for the player to interact with.
	 */
	public Enemy generate(){
		return enemyList.get(rng.nextInt(15));
	}
}
