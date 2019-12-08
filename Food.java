/**
 * Represnts food on the board associated with "*", Food is used to construct and get the names and amount of health the food will increase the players health.
 * @author Lucas Pokrywka, Lauren Wojcik
 */
public class Food {
    private String name;
    private int health;
    /**
     * Constructor is used to get the name and health amount of the food the player comes in contact with.
     * @param name gets the name of the food the player comes in contact with from the {@link FoodGenerator}.
     * @param health gets the amount of health the food will increase the characters health by from the {@link FoodGenerator}.
     */
    Food(String name, int health) {
        this.name = name;
        this.health = health;
    }
    /**
     * Getter that is used to get the name of the food and return it when the player comes in contact with from the constructor.
     * @return name of the food the player comes in contact with.
     */
    public String getName() { return name; }
    /**
     * Getter that is used to find the health amount of the food the character comes in contact with from the constructor.
     * @return the health amount associated with the food the player comes in contact with.
     */
    public int getHealth() { return health; }
}
