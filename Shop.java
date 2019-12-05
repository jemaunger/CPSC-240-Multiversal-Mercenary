import java.util.Scanner;

public class Shop {
	Scanner stdin = new Scanner(System.in);
	Inventory stock;
	private ItemGenerator gen;

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
}
