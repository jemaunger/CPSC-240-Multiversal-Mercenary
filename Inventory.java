import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;
/**
 * This class creates the actual inventory the user is pulling from, allowing th user the ability to print out their inventory, add, drop, and equipweapons and armor. 
 * Additionally has the methods to deal with the players gold amount, increasing and decreasing the gold when the player buys or sells from the store.
 * Also includes the method for using the potions the player has in their inventory.
 * @authors Ethan Pearson, Jema Unger, Lucas Pokrywka, Lauren Wojcik
 */
public class Inventory {
	Scanner stdin = new Scanner(System.in);
	ArrayList<Item> items = new ArrayList<Item>();
	private int maxWeight;
	private Item equippedWeapon = null;
	private Item equippedArmor = null;
	private int gold;

	/**
	 * Constructor that initializes a max weight for the inventory as as well as instatiantes a new item generator named gen, adding three items to the new item generator, one of each type.
	 * @param maxWeight, This is used to limit the amount of items the player can have.
	 */
	Inventory(int maxWeight) {
		this.maxWeight = maxWeight;
		ItemGenerator gen = new ItemGenerator();
		items.add(gen.weaponGenerate());
		items.add(gen.armorGenerate());
		items.add(gen.otherGenerate());
		gold = 150;
	}
	Inventory(Scanner s){
		this.maxWeight = s.nextInt();
		s.nextLine();
		Inventory bag = new Inventory(s);
	}

	/**
	 * Method meant to calculate the players total weight of the items in their inventory by getting the sum of all of the weights of every item in the players inventory.
	 * @return the players current total weight of items in their inventory.
	 */
	public int totalWeight() {
		int total = 0;
		for (int i = 0; i < items.size(); i++)
			total = total + items.get(i).getWeight();
		return total;
	}
	/**
	 * This method adds items into the inventory, which it does by taking the item that the player wants to add, checks to see if adding the item would make the player go over their max weight. 
	 * Only adding the items that will not cause the player to go over their max weight. 
	 * @param item used as a parameter to allow the player to add items directly to their inventory.
	 * @return true if the item was able to be added to the players inventory and false if the item would cause the player to go over the max weight.
	 */
	public boolean add(Item item) {
		if (totalWeight() + item.getWeight() <= maxWeight) {
			items.add(item);
			return true;
		} else
			return false;
	}
	/**
	 * This method prints out all the items currently stored in the players inventory, by looping through the length of the items arraylist displaying their name, weight, gold value, and strength.
	 */
	public void print() {
		System.out.println("Name Weight Value Strength");
		for (int i = 0; i < items.size(); i++)
			System.out.println(i + 1 + ". " + items.get(i));
	}
	/**
	 * Allows the board to access the amount of gold the player has left in their inventory when they go to the shop.
	 * @return the players gold amount.
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * Allows the amount of gold the player has in their inventory to be printed when the player comes in contact with the shop, and is called in the Board class.
	 */	 
	public void priceCheck() {
		System.out.println("Your gold amount is: " + gold);
	}

	/**
	 * Used to subtract the gold amount of the item purchased from the shop by the player.
	 * @param taken is the variable that is taken away from the players gold amount when they purchase an item from the shop.
	 */
	public void takeGold(int taken) {
		gold -= taken;
	}

	/**
	 * Used to add the amount of gold value of the item sold to the shop of the players choosing.
	 * @param added variable that is added to the players gold amount when they sell an item to the shop.
	 */
	public void addGold(int added) {
		gold += added;
	}

	/**
	 * Removes an item from the inventory, it does this by printing out the items in the inventory. Then, it asks the user to choose based on the numbers displayed next to the items.
	 * It ensures that the number used is correctly within the range and removes the item if the number works, if the item is currently equipped, it makes sure to first unequip the item.
	 */

	public void drop() {
		System.out.println("Which item do you want to drop? Press 0 to cancel.");
		print();
		int removed = stdin.nextInt() - 1;
		if (removed >= items.size()) {
			System.out.println("There is no item associated with that number.");
			return;
		} else if (removed < 0) {
			System.out.println("There is no item associated with that number.");
			return;
		} else {
			if (items.get(removed) == equippedWeapon)
				equippedWeapon = null;
			else if (items.get(removed) == equippedArmor)
				equippedArmor = null;
			items.remove(removed);
			return;
		}
	}

	/**
	 * Equips one of the weapons within the inventory, it first checks if the user has a weapon currently equipped, and informs which weapon that is.
	 * Then, it scans all the items within the inventory, printing only the weapons, the user chooses a weapon based on the numbers next to the printed weapons.
	 */
	public void equipWeapon() {
		if (equippedWeapon != null) {
			System.out.println("Your currently equipped weapon is: " + equippedWeapon);
		}
		int count = 1;
		System.out.println("Which weapon would you like to equip? Press 0 to equip none.");
		System.out.println("(Hint: Choose the weapon with the highest Strength)");
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getType() == ItemType.WEAPON) {
				System.out.printf("%d. %s, %d %n", count, items.get(i).getName(), items.get(i).getStrength());
				count++;
			}
		}
		int selected = stdin.nextInt();
		if(selected == 0) {
			equippedWeapon = null;
			return;
		}
		int recount = 1;
		for (int i = 0; i < items.size(); i++) {
			if (recount == selected && items.get(i).getType() == ItemType.WEAPON) {
				equippedWeapon = items.get(i);
				return;
			} else if (items.get(i).getType() == ItemType.WEAPON)
				recount++;
		}
		System.out.println("There was an error equipping your weapon. Please try again.");
		return;
	}
	/**
	 * Equips one of the armors within the inventory, it first checks if a user has any armor equipped currently and prints back which armor it is.
	 * It then scans through all of the items in the inventory with the armor item type, and asks the user to choose which armor they would like to equip for battle.
	 */
	public void equipArmor() {
		if (equippedArmor != null)
			System.out.println("Your currently equipped armor is: " + equippedArmor);
		int count = 1;
		System.out.println("Which armor would you like to equip? Press 0 to equip none.");
		System.out.println("(Hint: Choose the armor with the highest Strength)");
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getType() == ItemType.ARMOR) {
				System.out.printf("%d. %s, %d %n", count, items.get(i).getName(), items.get(i).getStrength());
				count++;
			}
		}
		int selected = stdin.nextInt();
		if(selected == 0) {
			equippedArmor = null;
			return;
		}
		int recount = 1;
		for (int i = 0; i < items.size(); i++) {
			if (recount == selected && items.get(i).getType() == ItemType.ARMOR) {
				equippedArmor = items.get(i);
				return;
			} else if (items.get(i).getType() == ItemType.ARMOR)
				recount++;
		}
		System.out.println("There was an error equipping your armor. Please try again.");
		return;
	}
	/**
	 * Allows the player to choose which item's damage value, both armor and weapons to increase in value by the amount given by the specific potion.
	 * @param potion uses the Potion object to give the potions the ability to increase items damage.
	 */
	public void usePotion(Potion potion) {
		System.out.printf("You've found %s! Choose an item to boost its strength by %d points. %n", potion.getName(), potion.getHealth());
		int count = 1;
		for (int i = 0; i < items.size(); i++) {
			if ((items.get(i).getType() == ItemType.ARMOR) | (items.get(i).getType() == ItemType.WEAPON)) {
				System.out.printf("%d. %s (%s), %d %n", count, items.get(i).getName(), items.get(i).getType(), items.get(i).getStrength());
				count++;
			}

		}
		System.out.print(": ");
		int choice = stdin.nextInt();
		choice = choice - 1;
		int currentDamage = items.get(choice).getStrength();

		increaseDamage(choice, currentDamage, potion.getHealth());
		System.out.printf("%s now has %d strength %n", items.get(choice).getName(), items.get(choice).getStrength());
	}

	/**
	 * Used when the player uses a potion on an item this method is called to increase the damage value on the item the player selects.
	 * @param index an int that is used to select the items the player chooses to increase that items damage value.
	 * @param currentDamage takes the current damage of the item the player chooses to be added to boost.
	 * @param boost is set to the value of the potion the player came in contact with the be added to the current damage of the players item and adds them together.
	 */
	public void increaseDamage(int index, int currentDamage, int boost) {
		items.get(index).setStrength(currentDamage + boost);

	}

	/**
	 * When the player comes in contact with an enemy and chooses a weapon to equip this method is called to get the weapon that the player chose to equip for that certain battle.
	 * return the weapon the player chose to equip.
	 */
	public Item getEquippedWeapon() {
		return equippedWeapon;
	}

	/**
	 * When the player comes in contact with an enemy and chooses a piece of armor to equip this method is called to get the armor the player equipped.
	 * @return the piece of armor the player chose to equip.
	 */
	public Item getEquippedArmor() {
		return equippedArmor;
	}

	/**
	 * To string method to return the players current items in their inventory.
	 * @return the current items in the players inventory.
	 */
	public String toString() {
		String results = "";
		for(Item i : items) {
			results += i.toString() + " ";
		}
		return results;
	}
}
