import java.util.Random;
import java.util.ArrayList;
//Generates food as "*" throughout the board.
public class FoodGenerator {
    Random random = new Random();
    ArrayList<Food> foodList = new ArrayList<Food>();
//creates and stores the different types of food that can be spread throughout the board.
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
//randomly generates food and spreads it out.
    public Food generate(){
        return foodList.get(random.nextInt(foodList.size()));
    }
}
