import java.util.Scanner;
import java.util.Random;

//This class creates the Item object.
//This object stores every item that can be stored in the inventory.
//Each Item has a name, type, weight, value, and strength, with each integer value randomized.
public class Item {
	Random rand = new Random();
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;

	//This constructor creates a new Item object.
	//The name and type arg taken straight from the constructor, while the weight, value, and strength are all randomized.
	//To make this work, I created what I call light, medium, and heavy classes for each value, as indiated by the base values entered into the constructors.
	//This is not separated by type, because I thought it would be funny if a sword weighed more than a horse, or a feather was more valuable than a lightsaber.
	public Item (String name, ItemType type, int weight, int value, int strength) {
		this.type = type;
		this.name = name;
		if(weight == 6)
			this.weight = randRange(0, 12);
		else if(weight == 15)
			this.weight = randRange(7, 23);
		else if(weight == 30)
			this.weight = randRange(18, 30);
		else
			this.weight = weight;
		if(value == 100)
			this.value = randRange(0, 115);
		else if(value == 200)
			this.value = randRange(76, 215);
		else if(value == 300)
			this.value = randRange(175, 300);
		else
			this.value = value;
		if(strength == 10)
			this.strength = randRange(1, 25);
		else if(strength == 25)
			this.strength = randRange(21, 35);
		else if(strength == 50)
			this.strength = randRange(31, 50);
		else
			this.strength = strength;
	}
	Item(Scanner s){
		this.weight = s.nextInt();
		this.value = s.nextInt();
		this.name = s.nextLine();
		this.strength = s.nextInt();
	}	
	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public int getStrength() {
		return strength;
	}

	public ItemType getType() {
		return type;
	}

	public String toString() {
		return name + " " + type + " " + weight + " " + value + " " + strength;
	}

	//This method allows the program to randomize using a custom range.
	//I wanted this so I could separate the randomization between multiple classes.
	//This is the only method I did not write myself, as I found it here:
	//https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
	private static int randRange(int min, int max) {
		if(min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
