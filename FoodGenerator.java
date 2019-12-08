import java.util.Random;
import java.util.ArrayList;
/**
 * Represents a random generator for food classified by "*", and fills the place of that character with the name and increase health value of the food.
 * @author Ethan Pearson, Jema Unger, Lucas Pokrywka, Lauren Wojcik
 */
public class FoodGenerator {
	Random random = new Random();
	ArrayList<Food> foodList = new ArrayList<Food>();
	/**
	 * Creates food and stores each food item in the foodList array list to be spread throughout the board.
	 */
	FoodGenerator() {
		foodList.add(new Food("Beets", 5));
		foodList.add(new Food("Potato", 10));
		foodList.add(new Food("Slice of cake", 10));
		foodList.add(new Food("Cookie", 10));
		foodList.add(new Food("Watermelon Slice",20));
		foodList.add(new Food("Chocolate Frog", 20));
		foodList.add(new Food("Carrot", 5));
		foodList.add(new Food("Raw Steak",20));
		foodList.add(new Food("Apple",20));
		foodList.add(new Food("Baguette", 50));
		foodList.add(new Food("Suspicious Stew",30));
		foodList.add(new Food("Pumpkin Pie", 40));
		foodList.add(new Food("Steak", 40));
		foodList.add(new Food("Whole cake",100));

	}
	/**
	 * Generates random food items that can be applied to each "*" character on the board, allowing the player to come in contact with them.
	 * @return the food that will be generated and placed throughout the board when called in Board.
	 */
	public Food generate(){
		return foodList.get(random.nextInt(foodList.size()));
	}
}
