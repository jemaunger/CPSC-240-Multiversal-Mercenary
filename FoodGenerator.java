import java.util.Random;
import java.util.ArrayList;
//Generates food as "*" throughout the board.
public class FoodGenerator {
    Random random = new Random();
    ArrayList<Food> foodList = new ArrayList<Food>();
//creates and stores the different types of food that can be spread throughout the board.
    FoodGenerator() {
        foodList.add(new Food("Beets", 5));
        foodList.add(new Food("Potato", 5));
        foodList.add(new Food("Slice of cake", 5));
        foodList.add(new Food("Cookie", 10));
        foodList.add(new Food("Watermelon Slice",10));
        foodList.add(new Food("Chocolate Frog", 10));
        foodList.add(new Food("Carrot", 15));
        foodList.add(new Food("Raw Steak",15));
        foodList.add(new Food("Apple",15));
        foodList.add(new Food("Baguette", 25));
        foodList.add(new Food("Suspicious Stew",30));
        foodList.add(new Food("Pumpkin Pie", 40));
        foodList.add(new Food("Steak", 40));
        foodList.add(new Food("Whole cake",70));

    }
//randomly generates food and spreads it out.
    public Food generate(){
        return foodList.get(random.nextInt(foodList.size()));
    }
}
