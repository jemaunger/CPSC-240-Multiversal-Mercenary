import java.util.*;
/**
 * This class stores and randomy generates all of the {@link ItemType} items available in the game spread throughout the board.
 * @author Ethan Pearson
 */
public class ItemGenerator {
	Random rand = new Random();
	ArrayList<Item> itemList = new ArrayList<Item>();
	
	/**
	 * Constructor is used to add 100 items of {@link ItemType} into the itemList arraylist, it stores 30 weapons, 30 armors, and 40 other items that can be picked up by the player. 
	 */
	//While I realized midway through that this could have been much easier if I initialized everything with the arraylist, I was already halfway done at that point.
	//I also may have gone overboard with the number of items, but they were fun to think up.
	//While some items you would typically in a game like Skyrim, most are just weird items I brainstormed with my roommate.
	ItemGenerator() {
		itemList.add(new Item("Iron Dagger", ItemType.WEAPON, 6, 100, 10));
		itemList.add(new Item("Steel Dagger", ItemType.WEAPON, 6, 200, 25));
		itemList.add(new Item("Tungsten Dagger", ItemType.WEAPON, 15, 300, 25));
		itemList.add(new Item("Iron Sword", ItemType.WEAPON, 6, 100, 25));
		itemList.add(new Item("Steel Sword", ItemType.WEAPON, 15, 200, 25));
		itemList.add(new Item("Tungsten Sword", ItemType.WEAPON, 15, 300, 50));
		itemList.add(new Item("Iron Mace", ItemType.WEAPON, 6, 200, 25));
		itemList.add(new Item("Steel Mace", ItemType.WEAPON, 15, 200, 50));
		itemList.add(new Item("Tungsten Mace", ItemType.WEAPON, 15, 300, 50));
		itemList.add(new Item("Iron Hammer", ItemType.WEAPON, 30, 200, 50));
		itemList.add(new Item("Steel Hammer", ItemType.WEAPON, 30, 200, 50));
		itemList.add(new Item("Tungsten Hammer", ItemType.WEAPON, 30, 300, 50));
		itemList.add(new Item("Blue Lightsaber", ItemType.WEAPON, 6, 300, 50));
		itemList.add(new Item("Green Lightsaber", ItemType.WEAPON, 6, 300, 50));
		itemList.add(new Item("Purple Lightsaber", ItemType.WEAPON, 6, 300, 50));
		itemList.add(new Item("Red Lightsaber", ItemType.WEAPON, 6, 300, 50));
		itemList.add(new Item("Pistol", ItemType.WEAPON, 6, 300, 25));
		itemList.add(new Item("Iron Axe", ItemType.WEAPON, 6, 100, 25));
		itemList.add(new Item("Steel Axe", ItemType.WEAPON, 6, 200, 25));
		itemList.add(new Item("Tungsten Axe", ItemType.WEAPON, 6, 200, 25));
		itemList.add(new Item("Boxing Glove", ItemType.WEAPON, 30, 100, 25));
		itemList.add(new Item("Magic Wand", ItemType.WEAPON, 6, 300, 50));
		itemList.add(new Item("Flamethrower", ItemType.WEAPON, 30, 300, 50));
		itemList.add(new Item("Bow", ItemType.WEAPON, 6, 100, 10));
		itemList.add(new Item("Empty Bottle", ItemType.WEAPON, 6, 0, 25));
		itemList.add(new Item("Wooden Stake", ItemType.WEAPON, 15, 100, 50));
		itemList.add(new Item("Fork", ItemType.WEAPON, 6, 100, 25));
		itemList.add(new Item("Spoon", ItemType.WEAPON, 6, 100, 10));
		itemList.add(new Item("Kitchen Knife", ItemType.WEAPON, 6, 100, 25));
		itemList.add(new Item("Lightning Bolt", ItemType.WEAPON, 0, 0, 50));
		itemList.add(new Item("Iron Chainmail", ItemType.ARMOR, 6, 100, 10));
		itemList.add(new Item("Steel Chainmail", ItemType.ARMOR, 6, 100, 25));
		itemList.add(new Item("Titanium Chainmail", ItemType.ARMOR, 15, 200, 50));
		itemList.add(new Item("Iron Plating", ItemType.ARMOR, 15, 200, 25));
		itemList.add(new Item("Steel Plating", ItemType.ARMOR, 15, 200, 50));
		itemList.add(new Item("Titanium Plating", ItemType.ARMOR, 30, 300, 50));
		itemList.add(new Item("Iron Armor", ItemType.ARMOR, 15, 200, 50));
		itemList.add(new Item("Steel Armor", ItemType.ARMOR, 30, 200, 50));
		itemList.add(new Item("Titanium Armor", ItemType.ARMOR, 30, 300, 50));
		itemList.add(new Item("Iron Suit", ItemType.ARMOR, 6, 200, 25));
		itemList.add(new Item("Steel Suit", ItemType.ARMOR, 15, 300, 50));
		itemList.add(new Item("Titanium Suit", ItemType.ARMOR, 15, 300, 50));
		itemList.add(new Item("Super Suit", ItemType.ARMOR, 15, 300, 50));
		itemList.add(new Item("Nanotech Suit", ItemType.ARMOR, 6, 300, 50));
		itemList.add(new Item("Skeleton Suit", ItemType.ARMOR, 6, 100, 25));
		itemList.add(new Item("Exo Suit", ItemType.ARMOR, 6, 100, 50));
		itemList.add(new Item("Civilian Clothing", ItemType.ARMOR, 6, 100, 10));
		itemList.add(new Item("Wizard Robes", ItemType.ARMOR, 6, 200, 10));
		itemList.add(new Item("Hazmat Suit", ItemType.ARMOR, 15, 100, 25));
		itemList.add(new Item("Jedi Robes", ItemType.ARMOR, 6, 100, 10));
		itemList.add(new Item("Police Uniform", ItemType.ARMOR, 6, 200, 25));
		itemList.add(new Item("Suit & Tie", ItemType.ARMOR, 6, 300, 10));
		itemList.add(new Item("Paper Armor", ItemType.ARMOR, 6, 0, 10));
		itemList.add(new Item("Snowsuit", ItemType.ARMOR, 30, 200, 10));
		itemList.add(new Item("Batman Suit", ItemType.ARMOR, 15, 300, 50));
		itemList.add(new Item("Garbage Bag", ItemType.ARMOR, 0, 0, 0));
		itemList.add(new Item("Ball Gown", ItemType.ARMOR, 15, 300, 10));
		itemList.add(new Item("Spider-Man Suit", ItemType.ARMOR, 6, 100, 25));
		itemList.add(new Item("Leather Armor", ItemType.ARMOR, 6, 200, 25));
		itemList.add(new Item("Glass Armor", ItemType.ARMOR, 6, 300, 10));
		itemList.add(new Item("Spell Book", ItemType.OTHER, 30, 300, 0));
		itemList.add(new Item("Harry Potter and the Sorcerer's Stone", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Chamber of Secrets", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Prisoner of Azkaban", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Goblet of Fire", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Order of the Phoenix", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Half-Blood Prince", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Harry Potter and the Deathly Hallows", ItemType.OTHER, 30, 200, 0));
		itemList.add(new Item("Whoopie Cushion", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Pen", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Pencil", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Silverfish Corpse", ItemType.OTHER, 6, 0, 0));
		itemList.add(new Item("Iron Ingot", ItemType.OTHER, 15, 100, 0));
		itemList.add(new Item("Steel Ingot", ItemType.OTHER, 15, 200, 0));
		itemList.add(new Item("Tungsten Ingot", ItemType.OTHER, 15, 300, 0));
		itemList.add(new Item("Titanium Ingot", ItemType.OTHER, 15, 300, 0));
		itemList.add(new Item("The Complete Works of William Shakespeare", ItemType.OTHER, 30, 300, 0));
		itemList.add(new Item("Mobile Device", ItemType.OTHER, 6, 300, 0));
		itemList.add(new Item("Coffee Beans", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Caocao Beans", ItemType.OTHER, 6, 200, 0));
		itemList.add(new Item("Chocolate Bar", ItemType.OTHER, 6, 300, 0));
		itemList.add(new Item("Cat", ItemType.OTHER, 15, 200, 0));
		itemList.add(new Item("Dog", ItemType.OTHER, 15, 200, 0));
		itemList.add(new Item("Horse", ItemType.OTHER, 30, 300, 0));
		itemList.add(new Item("Walkie-Talkie", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Wooden Bowl", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Cup", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Straw", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Foam Finger", ItemType.OTHER, 6, 200, 0));
		itemList.add(new Item("Snowglobe", ItemType.OTHER, 6, 200, 0));
		itemList.add(new Item("Broom", ItemType.OTHER, 15, 100, 0));
		itemList.add(new Item("Mortar & Pestle", ItemType.OTHER, 15, 200, 0));
		itemList.add(new Item("Honey", ItemType.OTHER, 30, 100, 0));
		itemList.add(new Item("Teddy Bear", ItemType.OTHER, 6, 200, 0));
		itemList.add(new Item("Feather", ItemType.OTHER, 0, 300, 0));
		itemList.add(new Item("Broken Sword Hilt", ItemType.OTHER, 6, 100, 0));
		itemList.add(new Item("Destroyed Gauntlet", ItemType.OTHER, 15, 100, 0));
		itemList.add(new Item("Dirty Sock", ItemType.OTHER, 0, 0, 0));
		itemList.add(new Item("Television", ItemType.OTHER, 30, 300, 0));
		itemList.add(new Item("Jar of Dirt", ItemType.OTHER, 6, 300, 0));
	}

	/**
	 * Used to randomly generate all of the items from the list above. 
	 * @return the randomly generated items.
	 */
	public Item generate() {
		return itemList.get(rand.nextInt(100));
	}

	/**
	 * While using the {@link randRange} method, randomly generates weapons to be places around the board within the first 30 items of the list.
	 * @return the randomly generated weapons.
	 */
	public Item weaponGenerate() {
		return itemList.get(randRange(0, 29));
	}

	/**
	 * Uses the {@link randRange} method to randomly generate armor to be placed around the board within the 31st to 60th items.
	 * @return randomly generated pieces of armor.
	 */
	public Item armorGenerate() {
		return itemList.get(randRange(30, 59));
	}

	/**
	 * Uses the {@link randRange} method to randomly generate other items to be placed throughout the board within the 61st to 100th items.
	 * @return randomly generated other items.
	 */
	public Item otherGenerate() {
		return itemList.get(randRange(60, 99));
	}

	/**
	 * Used to sort through the item generators above based on their range and randomly generate the items to be placed around the board.
	 * @param min is the smallest variable in the range of items.
	 * @param max is the largest variable in the range of items.
	 * @throws IllegalArgumentException when the max variable comes up less than the min variable.
	 * @return the random object to be used to randomly generate the above items.
	 */
	//Link to where this method was found is at the bottom of the Item class.
	private static int randRange(int min, int max) {
		if(min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
