import java.util.Scanner;
import java.util.Random;
/**
 * Represents the Item object that is created by the {@link ItemGenerator} and used and stored by the {@link Inventory}, they are represented by "O" on the board.
 * @author Ethan Pearson, Jema Unger
 */
public class Item {
	Random rand = new Random();
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;

	/**
	 * Constructor that gets the name of the item the player either has initially placed in their inventory or come in contact with on the board, takes the name, type, weight, value, and strength
	 * from the {@link ItemGenerator}.
	 * Also assigns random values for each interger value based on their set values in the Arraylist from {@link ItemGenerator}.
	 * @param name gets the name of the item from the {@link ItemGenerator}.
	 * @param type gets the type of the item created in {@link ItemType} and set in {@link ItemGenerator}.
	 * @param weight gets the weight of the item from the arraylist in {@link ItemGenerator}.
	 * @param value gets the value of the item from {@link ItemGenerator}.
	 * @param strength gets the strength of the item from {@link ItemGenerator}.
	 */
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
	/**
	 * Item constructor with scanner as a parameter is used to restore the state of the items in the players inventory from a save file.
	 * @param s scanner used to restore the items the player has in their inventory from a save file.
	 */
	Item(Scanner s){
		this.weight = s.nextInt();
		this.value = s.nextInt();
		this.name = s.nextLine();
		this.strength = s.nextInt();
	}	
	/**
	 * Gets the weight of the items in the players inventory, and the weight of the items available in the shop.
	 * @return the weight of the item from the constructor.
	 */
	public int getWeight() {
		return weight;
	}
	/** 
	 * Gets the gold value of the items in the players inventory or items that are available at the shop.
	 * @return the value of the item from the constructor.
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Gets the weight of the item in the players inventory or items that are available in the shop.
	 * @return the name of the item from the constructor.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the strength of the item in the players inventory or items that are available in the shop.
	 * @return strength of the item from the constructor.
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * Gets the type of the items in the players inventory or the items available when the player goes to the shop.
	 * @return type of the items from the constructor.
	 */
	public ItemType getType() {
		return type;
	}
	/**
	 * Sets the strength of the items available in the players inventory when they decide to do battle with an enemy.
	 * @param strength is used to set the strength taken from {@link getStrength} which is constructed above.
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * To string method is used to construct a string to be displayed when the player wants to see their inventory, or visits the shop.
	 * @return a string for the name, type, weight, value, and strength of the items available.
	 */
	public String toString() {
		return name + " " + type + " " + weight + " " + value + " " + strength;
	}
	/**
	 * Allows the program to be randomized when used above in the constructor to randomize the values of the intergers using a custome range.
	 * @param min set as the lowest value the range can reach.
	 * @param max used as the maxixmum value the range can reach and should always be greater than the min.
	 * @return true as long as the min value is less than the max.
	 * @throws IllegalArgumentException is thrown when the min value is greater than the max.
	 */
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
