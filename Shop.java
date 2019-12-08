import java.util.Scanner;
/**
 * Represents the shop that is placed on the board with the character "$", it always starts on the farthest right and upmost point on the board.
 * After the player visits the shop once, the it moves to a random location on the board, to prevent players spamming the shop for specific items.
 * @author Ethan Pearson
 */
public class Shop {
	Scanner stdin = new Scanner(System.in);
	Inventory stock;
	private ItemGenerator gen;
	/**
	 * Shop costructor that instantiates a new {@link Inventory} and {@link ItemGenerator} and add seven randomly generated items to the stock.
	 */
	public Shop() {
		stock = new Inventory(1000);
		gen = new ItemGenerator();

		stock.add(gen.generate());
		stock.add(gen.generate());
		stock.add(gen.generate());
		stock.add(gen.generate());
		stock.add(gen.generate());
		stock.add(gen.generate());
		stock.add(gen.generate());
	}
	/**
	 * When a player comes in contact with the store and they would like to purchase something, the shop prints out its stock for the player.
	 * The player is then prompted to choose which {@link Item} they would like to buy, when the player chooses one the buy method checks
	 * that the player has enough gold in their {@link Inventory} and if they do, the item is purchased and added to the players inventory.
	 * @param charIn an {@link Inventory} object used to store the randomly generated items when a player wants to buy one.
	 */
	public void buy(Inventory charIn) {
		boolean purchaseCompleted = false;
		while(purchaseCompleted == false) {
			System.out.println("Select the number of the item that you would like to purchase. Enter 0 to cancel.");
			int bought = stdin.nextInt()-1;
			if(bought >= stock.items.size()) {
				System.out.println("There is no item connected to that number! Please try again.");
				purchaseCompleted = false;
			}else if(bought < 0) {
				purchaseCompleted = true;
			}else {
				Item purchased = stock.items.get(bought);
				if(purchased.getValue() <= charIn.getGold()) {
					charIn.takeGold(purchased.getValue());
					charIn.add(purchased);
					stock.items.remove(bought);
					System.out.println("Pleasure doing business with you.");
					charIn.print();
					charIn.priceCheck();
					System.out.println("Would you like to make another purchase or sell an item? Press 'B' to buy, 'S' to sell, or 'E' to exit.");
					char choice = stdin.next().charAt(0);
					if(choice == 'B' || choice == 'b') {
						stock.print();
						purchaseCompleted = false;
					}else if(choice == 'S' || choice == 's') {
						sell(charIn);
						purchaseCompleted = true;
					}else {
						purchaseCompleted = true;
					}
				}else {
					System.out.println("This item is too expensive! Please try again!");
					purchaseCompleted = false;
				}
			}
		}
	}
	/**
	 * Used when the player comes in contact with the shop with the intention of selling some of the items in their inventory to the shop, this prints out the contents
	 * of the players inventory using the print method. The player chooses which item they would like to sell and the shop purchases it from them.
	 * @param charIn an {@link Inventory} object which is used store the items that are available in the players inventory for them to sell to the shop.
	 */
	public void sell(Inventory charIn) {
		charIn.print();
		boolean saleCompleted = false;
		while(saleCompleted == false) {
			System.out.println("Select the number of the item you would like to sell.");
			int sale = stdin.nextInt()-1;
			if(sale >= charIn.items.size() || sale < 0) {
				System.out.println("There is no item connected to that number! Please try again.");
				saleCompleted = false;
			}else {
				Item sold = charIn.items.get(sale);
				charIn.addGold(sold.getValue());
				charIn.items.remove(sale);
				System.out.println("Pleasure doing business with you.");
				charIn.print();
				charIn.priceCheck();
				System.out.println("Would you like to make another sale or purchase an item? Press 'B' to buy, 'S' to sell, or 'E' to exit.");
				char choice = stdin.next().charAt(0);
				if(choice == 'B' || choice == 'b') {
					stock.print();
					buy(charIn);
					saleCompleted = true;
				}else if(choice == 'S' || choice == 's') {
					saleCompleted = false;
				}else {
					saleCompleted = true;
				}
			}
		}
	}
	/**
	 * Allows the randomly generated items in the shops stock to be printed to the screen, is also used when the player first comes in contact with the shop, displaying both 
	 * the shops stock and the players inventory.
	 */
	public void print() {
		stock.print();
	}
}
