import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*;

//This class creates the actual inventory the user is pulling from.
//The majority of the methods used in the Main file come from here.
//All the methods function to alter the inventory in some way.
public class Inventory {
    Scanner stdin = new Scanner(System.in);
    ArrayList<Item> items = new ArrayList<Item>();
    private int maxWeight;
    private Item equippedWeapon = null;
    private Item equippedArmor = null;

	//This initialization includes a max weight.
	//This is used to limit the amount of items the player can have.
	Inventory(int maxWeight) {
		this.maxWeight = maxWeight;
		ItemGenerator gen = new ItemGenerator();
		items.add(gen.weaponGenerate());
		items.add(gen.armorGenerate());
		items.add(gen.otherGenerate());
	}
	Inventory(Scanner s){
		this.maxWeight = s.nextInt();
		s.nextLine();
		Inventory bag = new Inventory(s);
	}

    //This method keeps track of the current weight of the inventory.
    //It does this by going through the items currently in the inventory and adding the weight.
    public int totalWeight() {
        int total = 0;
        for (int i = 0; i < items.size(); i++)
            total = total + items.get(i).getWeight();
        return total;
    }

    //This method adds items into the inventory.
    //It does this by first taking the item that the player wants to add.
    //It checks to see if the item's weight would exceed the max weight of the inventory.
    //If the max weight would not be reached, the item is added to the inventory.
    //If the max weight would be reached, the item is not added to the inventory.
    public boolean add(Item item) {
        if (totalWeight() + item.getWeight() <= maxWeight) {
            items.add(item);
            return true;
        } else
            return false;
    }

    //This method prints out all the items currently stored in the inventory.
    public void print() {
        System.out.println("Name Weight Value Strength");
        for (int i = 0; i < items.size(); i++)
            System.out.println(i + 1 + ". " + items.get(i));
    }


    //This method removes an item from the inventory.
    //It does this by printing out the items in the inventory.
    //Then, it asks the user to choose based on the numbers displayed next to the items.
    //It ensures that the number used is correctly within the range and removes the item if the number works.
    //If the item is currently equipped, it makes sure to first unequip the item.
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

    //This method equips one of the weapons within the inventory.
    //It first checks if the user has a weapon currently equipped, and informs which weapon that is.
    //Then, it scans all the items within the inventory, printing only the weapons.
    //The user chooses a weapon based on the numbers next to the printed weapons.
    //The method goes back through the inventory to find the weapon the user chose.
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

    //This method equips one of the armors within the inventory.
    //It does this the same way the previous method equipped weapons.
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

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public String toString() {
	    String results = "";
	    for(Item i : items) {
		    results += i.toString() + " ";
	    }
	    return results;
    }
}
