//Food is a restoratative object within our game. It is marked by a * and restores a set number of health to the player.
public class Food {
    private String name;
    private int health;

    //Constructor assigns the name and the amount of health it restores.
    Food(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
}
