import java.util.Random;

//stores the information available for each different enemy.
public class Enemy {
    private String name;
    private int damage;
    private int health;
    private Random rng;
    private int badGuyID;
    private static int availableID = 0;

    Enemy(String name, int damage, int health) {
        this.name = name;
		if(damage == 10)
			this.damage = randRange(1, 25);
		else if(damage == 25)
			this.damage = randRange(21, 35);
		else if(damage == 50)
			this.damage = randRange(31, 50);
		else
			this.damage = damage;
        this.health = health;
        badGuyID = availableID;
        availableID++;


    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getBadGuyID() {
        return badGuyID;
    }

    public String toString() {
        return name + " " + health + " " + damage;
    }	
    //Check Item comment for randRange().
	private static int randRange(int min, int max) {
		if(min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
