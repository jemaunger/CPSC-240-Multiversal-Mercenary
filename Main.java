import java.util.Scanner;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Used to represent the start of the game allowing the player to go through the initial menus and storyline to be able to play through the game. 
 * @author Ethan Pearson, Jema Unger, Lucas Pokrywka, Lauren Wojcik
 */
public class Main {
	
	/**
	 * Contains the initial message of asking the player whether they want to restore from a previous save, then goes through the storyline and context of the game.
	 * Then is used to print out the menu of the game so the player knows the controls and can reference back to them, after they have gone through the inital menus,
	 * the board is printed and the player can play the game.
	 * @param args a string that can be used after compiling the program when trying to run, to add arguments to the end of the java Main command.
	 * @throws Exception FileNotFoundException when the Game.sav file cannot be found.
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		Board board = Board.getInstance();

		String fileName = "";
		char choice;

		System.out.println("Would you like to start from a previous save (Y or N)? ");
		choice = input.next().charAt(0);
		if((choice == 'N') | (choice == 'n')){
			System.out.println("All events you see in this game are real. They simply take place a long time ago, in a " + "galaxy far, far away...");

			System.out.println();

			System.out.println("The multidimensional barrier has been torn open, throwing a multitude of beings and items into our world.");
			System.out.println("Unfortunately, through a sheer statistical anomaly, all but one of these beings are evil!");
			System.out.println("Our hero, torn from their own world and/or time, now fights to rid our world of evil before ");
			System.out.println("returning to their own world (which will happen offscreen).");
			System.out.println("Can you help guide them toward where they need to be?");

			System.out.println();

			System.out.println("Your goal: Defeat all of the enemies on the board (denoted by &). There are 3 boards total.");
			System.out.println("Once you have conquered all enemies, a door will magically appear and you will be able to ");
			System.out.println("move to the next room. Clear all the rooms and you win!");
			System.out.println();

			//Generate a new board and print it to the screen
			System.out.printf("Welcome, %s! %n", Character.player().getName());

			System.out.println("Below is list of symbols and actions that you can make with your character.");
			System.out.println("To begin, please choose an action from the menu: ");
			System.out.println("(You must press enter between every action)");
		} if((choice == 'Y') | (choice == 'y')){
		  	board.setUp();
		  }
		/*	File loadFile = new File("Game.sav");
			Scanner in;
			try{
				in = new Scanner(loadFile);

				Enemy enemy = new Enemy(in);

				System.out.println("Finding file.....");
				Item i = new Item(in);
				Inventory inv = new Inventory(in);			

				while(in.hasNextLine()){
					System.out.println(in.nextLine());
				}	
			}catch(FileNotFoundException e){
				e.printStackTrace();	
			}
			//Board loadBoard = new Board(in);	
			//Character player = new Character(in);

			return;
		}*/
		printMenu();
		/*
		   try {
		   Thread.sleep(6000);
		   }catch (InterruptedException e) {
		   Thread.currentThread().interrupt();
		   }
		 */

		board.printBoard();
		System.out.print(": ");

		while (input.hasNext()) {
			do {
				choice = input.next().charAt(0);
				board.play(choice);

			} while ((choice != 'Q') | (choice != 'q'));

		}
	}
	/**
	 * Generates the menu which is printed after the initial story and prompts the player goes throught, also the player can choose to print out the menu at any point in the game
	 * with the command M. Menu shows all of the command options available for the player to use as well as a key that tells the player which symbols represent which objects.
	 */
	public static void printMenu() {
		System.out.println("Actions: ");
		System.out.println("     D = Drop an item in inventory");
		System.out.println("     I = Print item inventory");
		System.out.println("     H = Print current health status");
		System.out.println("     M = Print menu");
		System.out.println("     Q = Quit game");
		System.out.println("     S = Save game");
		System.out.println();

		System.out.println("     w = move character up");
		System.out.println("     s = move character down");
		System.out.println("     a = move character left");
		System.out.println("     d = move character right");
		System.out.println();

		System.out.println("Symbols:");
		System.out.println("     @ = Your character");
		System.out.println("     O = Item that can be picked up");
		System.out.println("     & = Enemy");
		System.out.println("     * = Food");
		System.out.println("     $ = Shop");
		System.out.println("     ! = Potion");
		System.out.println("     D = Door");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
	}

}
