import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Represents the playable board the player views after passing through the initial plotline and prompts from {@link Main}. 
 * @author Ethan Pearson, Jema Unger, Lucas Pokrywka, Lauren Wojcik
 */
public class Board  {

	// Instance variables
	private FileInputStream fileIn;
	private Scanner scnr;
	private FileInputStream fileIn2;
	private Scanner scnr2;
	private FileOutputStream fileOut;
	private PrintWriter out;
	private static String fileName = "room1.txt";
	private static Board instance;
	private String[] lines = new String[32];
	private char[][] grid = new char[32][32];
	Inventory inventory;
	private static int badGuyIDCounter = 13;
	private PotionGenerator genPotion;
	private static Shop market;

	//Protected 
	protected char choice;
	protected Character player;
	protected EnemyGenerator genEnemy;
	protected FoodGenerator genFood;
	protected ItemGenerator genItem = new ItemGenerator();
	protected Random rng = new Random();

	//	Protected Room room2;
	protected Scanner input = new Scanner(System.in);

	//Instance variables to be saved.
	private char characterLocation;
	private int characterHealth;
	private Item characterItems;
	/**
	 * Used when setting up the board after it has been saved, it takes the grid from grid.txt and also takes in the save file so the player can restore their game.
	 */
	public void setUp() {
		try{
			fileIn2 = new FileInputStream("grid.txt");
			scnr2 = new Scanner(fileIn2);
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
			System.exit(2);
		}
		for(int i = 0; i < 32; i++){
			lines[i] = scnr2.nextLine();
		}
		for(int x = 0; x < 32; x++){
			char[] cArray = lines[x].toCharArray();
			for(int y = 0; y < 32; y++){
				grid[x][y] = cArray[y];
			}
		}
		try{
			fileIn2 = new FileInputStream("Game.sav");
			scnr2 = new Scanner(fileIn2);
		}
		catch(FileNotFoundException e){
			System.out.println("file was not found");
			System.exit(2);
		}
	}
	/**
	 * Used to save the state of the grid, the location and health of the player, and what is in their inventory.
	 * @param pw PrintWriter to save the state of the game.
	 */
	public void saveGame(PrintWriter pw){
		roomChange(22);
		pw.println(badGuyIDCounter);
		pw.println(player);
		pw.println(inventory);

		pw.close();
		System.out.println("File saved successfully!");
	}
	/**
	 * Restores the state of the saved game, including the characters location health, items, also all of the placements of enemies and items across the board.
	 * @param s Scanner that reads in the state of the game from Game.sav.
	 */
	public Board(Scanner s){
		player = new Character(s);

		for(int i = 0; i < 32; i++){
			lines[i] = s.nextLine();
		}
		for(int i = 0; i < 32; i++){
			char[] holdLine = lines[i].toCharArray();
			for(int j = 0; j < 32; j++){
				grid[i][j] = holdLine[j];
				System.out.println(grid[i][j]);
				return;
			}
			return;
		}

	}
	/**
	 * Constructor which reads in a file in from a scanner, reading in the room.txt files, also instantiates a new player and {@link Inventory}, as well as a {@link FoodGenerator}.
	 * @throws FileNotFoundException when it is unable to find the room.txt files. 
	 */
	Board() {
		try{
			fileIn = new FileInputStream(fileName);
			scnr = new Scanner(fileIn);

		}
		catch(FileNotFoundException e){
			System.out.println("Room text file was not found");
			System.exit(2);
		}
		for (int i = 0; i < 32; i++){
			lines[i] = scnr.nextLine();
			player = new Character("@");
			genFood = new FoodGenerator();
			inventory = new Inventory(100);
		}
		for (int x = 0; x < 32; x++){
			char[] holdLine = lines[x].toCharArray();
			for ( int y = 0; y < 32; y++){
				grid[x][y] = holdLine[y];
			}
		}			
	}
	/**
	 * Used to make the board which it instantiates a singleton, ensuring there will only be one instance of a board at a time.
	 * @return the singleton board instance.
	 */
	public static synchronized Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}
	/**
	 * Used to print out the board instance from a file read in from the constructor.
	 */
	public void printBoard() {
		for (char[] space : grid) {
			for (char j : space) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		if (badGuyIDCounter == 7){
			grid[30][31] = 'D';
		}
		else if (badGuyIDCounter == 3){
			grid[0][1] = 'D';
		}

	}
	/**
	 * When a player defeats a certain number of enemies doors appear on the board allowing the player to enter seperate rooms, move and save between them
	 * @param num represents the number of enemies left on the board.
	 */
	public void roomChange(int num){
		if (num == 7){
			try{
				fileIn2 = new FileInputStream("room2.txt");
				scnr2 = new Scanner(fileIn2);
			}
			catch(FileNotFoundException e){
				System.out.println("file not found");
				System.exit(2);
			}
			for (int i = 0; i < 32; i++){
				lines[i] = scnr2.nextLine();
			}
			for (int x = 0; x < 32; x++){
				char[] cArray = lines[x].toCharArray();
				for (int y = 0; y < 32; y++){
					grid[x][y] = cArray[y];
				}
			}
		}
		else if (num == 3){
			try{
				fileIn2 = new FileInputStream("room3.txt");
				scnr2 = new Scanner(fileIn2);
			}
			catch(FileNotFoundException e){
				System.out.println("file not found");
				System.exit(2);
			}
			for (int i = 0; i < 32; i++){
				lines[i] = scnr2.nextLine();
			}
			for (int x = 0; x < 32; x++){
				char[] cArray = lines[x].toCharArray();
				for(int y = 0; y < 32; y++){
					grid[x][y] = cArray[y];
				}
			}
		}
		else {
			try{
				fileOut = new FileOutputStream("grid.txt");
				out = new PrintWriter(fileOut);
			}
			catch(IOException ex){
				System.out.println("file not found");
				System.exit(2);
			}
			for(char[] space: grid){
				for(char j: space){
					out.print(j);
				}
				out.println();
			}
			out.flush();
		}
	}
	/**
	 * Used to make it so the player is able to battle against the enemies on the board when they come in contact with them.
	 * @param player Character object used by the player to battle against enemies. 
	 * @param enemy Enemy object used to battle against the player.
	 * @return true if the player runs into an enemy and starts a battle.
	 */
	public boolean battle(Enemy enemy, Character player) {
		//When player and enemy battle, player will equip weapon and armor
		System.out.println("You've encountered an enemy!");
		System.out.printf("Name: %s, Damage: %d, Health: %d %n", enemy.getName(), enemy.getDamage(), enemy.getHealth());
		System.out.printf("Your health is %d %n", Character.player().getHealth());
		System.out.print("Would you like to battle (Y or N)? ");
		this.choice = input.next().charAt(0);

		if ((this.choice == 'Y') | (this.choice == 'y')) {
			inventory.equipWeapon();
			Item weapon = inventory.getEquippedWeapon();

			inventory.equipArmor();
			Item armor = inventory.getEquippedArmor();

			Character.setDamage(weapon);
			Character.setFullArmor(armor);

			int enemyHealth = enemy.getHealth();
			int enemyDamage = enemy.getDamage();
			String enemyName = enemy.getName();
			int playerHealth = Character.player().getHealth() + player.getArmor();
			int playerDamage = player.getDamage();
			int roundCounter = 0;

			do {
				System.out.printf("Round %d: %n", roundCounter + 1);
				enemyHealth = enemyHealth - playerDamage;
				if (enemyHealth <= 0) {
					System.out.printf("You attacked %s for %d damage. %n", enemyName, playerDamage);
					System.out.printf("Congrats! You defeated %s! %n", enemyName);
					System.out.printf("Your health total is at %d %n", playerHealth);
					badGuyIDCounter--;
					System.out.println("You still have to defeat " + badGuyIDCounter + " more enemies.");

					try {

						Thread.sleep(2000);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					return true;
				} else {
					playerHealth = playerHealth - enemyDamage;
					player.setHealth(playerHealth);
					System.out.printf("You attacked %s for %d damage. %n", enemyName, playerDamage);
					System.out.printf("%s attacked you for %d damage. %n", enemyName, enemyDamage);
					System.out.printf("Enemy health total is at %d. %n", enemyHealth);
					System.out.printf("Your health total is at %d. %n", playerHealth);
					System.out.println();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				roundCounter++;

			} while ((playerHealth > 0) && (enemyHealth > 0));

			if (playerHealth <= 0) {
				System.out.println("Though you fought bravely, you were no match for " + enemyName);
				System.out.println("Game over!");
				System.exit(0);
				return false;

			}  
			else {
				System.out.printf("%d more enemies remain... %n", badGuyIDCounter);
				System.out.println();

				return true;
			}
		}else {
			return false;
		}		
	}	
	/**
	 * Adds the ability of the player and enemies on the board to move around the board, enemies move randomly throughout the grid, and players move with w,a,s,d.
	 * Also contains the actions for the player to input such as equip, view inventory, print health, drop items, save the game, print the menu, and quit.
	 * @param play character used to move the player and enemies around the board. 
	 */
	public void play(char play) {
		System.out.println();

		//Keep track of character's current position
		int row = 0;
		int column = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '@') {
					row = i;
					column = j;
				}
			}
		}

		//Print health
		if (play =='H') {
			System.out.println(Character.player().getHealth());
		}
		//Drop an item from inventory
		if (play == 'D') {
			inventory.drop();
			inventory.print();
			printBoard();
		}

		//Print inventory
		if (play == 'I') {
			inventory.print();
			printBoard();
		}

		//Print menu again
		if (play == 'M') {
			Main.printMenu();
		}

		//Exit game
		if (play == 'Q') {
			System.out.println("Thank you for playing!");
			System.exit(0);
		}

		if(play == 'S') {
			try{
				File file = new File("Game.sav");
				PrintWriter pw = new PrintWriter(file);
				saveGame(pw);
			}catch(FileNotFoundException e){
				System.out.println("Failed to save!");
				e.printStackTrace();
			}
		}

		//Movements
		//Move up
		if (play == 'w') {
			//If character runs into an item (O), choose to pick it up or not
			if (grid[row - 1][column] == 'O') {
				genItem = new ItemGenerator();
				Item item = genItem.generate();

				System.out.printf("You've found %s (Weight: %d, Value: %d Strength: %d)! %n", item.getName(), item.getWeight(), item.getValue(), item.getStrength());
				System.out.print("Would you like to add it to your inventory (Y or N)?");
				char choice = input.next().charAt(0);
				if ((choice == 'y') || (choice == 'Y')) {
					inventory.add(item);
					inventory.print();
				}

			}
			else if (grid[row -1][column] == 'D'){
				System.out.println("Would you like the enter the door (Y or N)?");
				char choice2 = input.next().charAt(0);
				if ((choice2 == 'y') || (choice2 == 'Y')){
					roomChange(badGuyIDCounter);
				}
			}
			try {
				//Don't allow character to move through walls
				if ((grid[row - 1][column] == '|') | (grid[row - 1][column] == '_')) {
					grid[row][column] = '@';
				}

				else if (grid[row - 1][column] == '*') {
					Food food = genFood.generate();
					grid[row - 1][column] = '@';
					grid[row][column] = '.';
					Character.player().eatFood(food);
				}


				//If character runs into enemy (&), choose to battle or not
				else if ((grid[row - 1][column] == '&')) {
					genEnemy = new EnemyGenerator();
					Enemy enemy = genEnemy.generate();
					boolean battleResult = battle(enemy, player);
					if (battleResult) {
						grid[row][column] = '.';
						grid[row - 1][column] = '@';
					}
					if(!battleResult) {
						grid[row][column] = '@';
					}
				}
				//If character runs into potion (!), choose a weapon or armor to boost damage
				else if ((grid[row - 1][column] == '!')) {
					genPotion = new PotionGenerator();
					Potion potion = genPotion.generate();
					grid[row - 1][column] = '@';
					grid[row][column] = '.';

					inventory.usePotion(potion);
				}
				else if(grid[row-1][column] == '$') {
					market = new Shop();
					System.out.println("Welcome to the shop! Here is what's in stock:");
					market.print();
					inventory.priceCheck();
					System.out.println("Would you like to buy or sell something? Press 'B' to buy, 'S' to sell, and 'E' to exit.");
					char shopChoice = input.next().charAt(0);
					if(shopChoice == 'B' || shopChoice == 'b') {
						market.buy(inventory);
					}else if(shopChoice == 'S' || shopChoice == 's') {
						market.sell(inventory);
					}
					int randRow = 0;
					int randCol = 0;
					while(grid[randRow][randCol] != '$' && grid[randRow][randCol] != grid[row-1][column]) {
						randRow = rng.nextInt(32);
						randCol = rng.nextInt(32);
						if(grid[randRow][randCol] == '.') {
							grid[randRow][randCol] = '$';
						}
					}
					grid[row-1][column] = '@';
					grid[row][column] = '.';
				}
				else {
					grid[row - 1][column] = '@';
					grid[row][column] = '.';
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println("Invalid move, try again");
			}
			//enemy movement
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if(grid[x][y] == '&') {
						int k = rng.nextInt(4);
						if(k == 1) {
							if(grid[x+1][y] == '.') {
								grid[x][y] = '.';
								grid[x+1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 2) {	
							if(grid[x-1][y] == '.') {
								grid[x][y] = '.';
								grid[x-1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 3) {
							if(grid[x][y+1] == '.') {
								grid[x][y] = '.';
								grid[x][y+1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else {
							if(grid[x][y-1] == '.') {
								grid[x][y] = '.';
								grid[x][y-1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}
					}

				}
			}
			printBoard();
		}
		//Move left
		if (play == 'a') {
			//If character runs into item, choose to pick it up or not
			if (grid[row][column - 1] == 'O') {
				genItem = new ItemGenerator();
				Item item = genItem.generate();

				System.out.printf("You've found %s (Weight: %d, Value: %d Strength: %d)! %n", item.getName(), item.getWeight(), item.getValue(), item.getStrength());
				System.out.print("Would you like to add it to your inventory (Y or N)? ");
				char choice = input.next().charAt(0);
				if ((choice == 'y') || (choice == 'Y')) {
					inventory.add(item);
					inventory.print();
				}

			}
			else if (grid[row][column -1] == 'D'){
				System.out.println("Would you like to go through the door (Y or N)?");
				char choice2 = input.next().charAt(0);
				if ((choice2 == 'y') || (choice2 == 'Y')){
					roomChange(badGuyIDCounter);
				}
			}
			try {
				//Don't allow character to move through walls
				if ((grid[row][column - 1] == '|') | (grid[row][column - 1] == '_')) {
					grid[row][column] = '@';
				}

				else if(grid[row][column - 1] == '*') {
					Food food = genFood.generate();
					grid[row][column - 1] = '@';
					grid [row][column] = '.';
					Character.player().eatFood(food);
				}

				//If character runs into enemy, choose to battle or not
				else if ((grid[row][column - 1] == '&')) {
					genEnemy = new EnemyGenerator();
					Enemy enemy = genEnemy.generate();
					boolean battleResult = battle(enemy, player);
					if (battleResult) {
						grid[row][column] = '.';
						grid[row][column - 1] = '@';
					}
					if (!battleResult) {
						grid[row][column] = '@';
					}
				}
				//If character runs into potion (!), choose a weapon or armor to boost damage
				else if ((grid[row][column - 1] == '!')) {
					genPotion = new PotionGenerator();
					Potion potion = genPotion.generate();
					grid[row][column - 1] = '@';
					grid[row][column] = '.';

					inventory.usePotion(potion);
				}

				else if(grid[row][column-1] == '$') {
					market = new Shop();
					System.out.println("Welcome to the shop! Here is what's in stock:");
					market.print();
					inventory.priceCheck();
					System.out.println("Would you like to buy or sell something? Press 'B' to buy, 'S' to sell, and 'E' to exit.");
					char shopChoice = input.next().charAt(0);
					if(shopChoice == 'B' || shopChoice == 'b') {
						market.buy(inventory);
					}else if(shopChoice == 'S' || shopChoice == 's') {
						market.sell(inventory);
					}
					int randRow = 0;
					int randCol = 0;
					while(grid[randRow][randCol] != '$' && grid[randRow][randCol] != grid[row][column-1]) {
						randRow = rng.nextInt(32);
						randCol = rng.nextInt(32);
						if(grid[randRow][randCol] == '.') {
							grid[randRow][randCol] = '$';
						}
					}
					grid[row][column-1] = '@';
					grid[row][column] = '.';
				}


				else {
					grid[row][column - 1] = '@';
					grid[row][column] = '.';
				}

			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println("Invalid move, try again");
			}
			//enemy movement	
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if(grid[x][y] == '&') {
						int k = rng.nextInt(4);
						if(k == 1) {
							if(grid[x+1][y] == '.') {
								grid[x][y] = '.';
								grid[x+1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 2) {	
							if(grid[x-1][y] == '.') {
								grid[x][y] = '.';
								grid[x-1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 3) {
							if(grid[x][y+1] == '.') {
								grid[x][y] = '.';
								grid[x][y+1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else {
							if(grid[x][y-1] == '.') {
								grid[x][y] = '.';
								grid[x][y-1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}
					}
				}
			}
			printBoard();
		}

		//Move down
		if (play == 's') {
			//If character runs into item, choose to pick it up or not
			if (grid[row + 1][column] == 'O') {
				genItem = new ItemGenerator();
				Item item = genItem.generate();

				System.out.printf("You've found %s (Weight: %d, Value: %d Strength: %d)! %n", item.getName(), item.getWeight(), item.getValue(), item.getStrength());
				System.out.print("Would you like to add it to your inventory (Y or N)? ");
				char choice = input.next().charAt(0);
				if ((choice == 'y') || (choice == 'Y')) {
					inventory.add(item);
					inventory.print();
				}

			}
			else if (grid[row+1][column] == 'D'){
				System.out.println("Would you like to go through the door (Y or N)?");
				char choice2 = input.next().charAt(0);
				if ((choice2 == 'y') || (choice2 == 'Y')){
					roomChange(badGuyIDCounter);
				}
			}
			try {
				//Don't allow character to move through walls
				if ((grid[row + 1][column] == '|') | (grid[row + 1][column] == '_')) {
					grid[row][column] = '@';
				}

				else if (grid[row + 1][column] == '*') {
					Food food = genFood.generate();
					grid[row + 1][column] = '@';
					grid[row][column] = '.';
					Character.player().eatFood(food);
				}

				//If character runs into enemy, choose to battle
				else if ((grid[row + 1][column] == '&')) {
					genEnemy = new EnemyGenerator();
					Enemy enemy = genEnemy.generate();
					boolean battleResult = battle(enemy, player);
					if (battleResult) {
						grid[row][column] = '.';
						grid[row + 1][column] = '@';
					}
					if (!battleResult) {
						grid[row][column] = '@';
					}
				}
				//If character runs into potion (!), choose a weapon or armor to boost damage
				else if ((grid[row + 1][column] == '!')) {
					genPotion = new PotionGenerator();
					Potion potion = genPotion.generate();
					grid[row + 1][column] = '@';
					grid[row][column] = '.';

					inventory.usePotion(potion);
				}

				else if(grid[row+1][column] == '$') {
					market = new Shop();
					System.out.println("Welcome to the shop! Here is what's in stock:");
					market.print();
					inventory.priceCheck();
					System.out.println("Would you like to buy or sell something? Press 'B' to buy, 'S' to sell, and 'E' to exit.");
					char shopChoice = input.next().charAt(0);
					if(shopChoice == 'B' || shopChoice == 'b') {
						market.buy(inventory);
					}else if(shopChoice == 'S' || shopChoice == 's') {
						market.sell(inventory);
					}
					int randRow = 0;
					int randCol = 0;
					while(grid[randRow][randCol] != '$' && grid[randRow][randCol] != grid[row+1][column]) {
						randRow = rng.nextInt(32);
						randCol = rng.nextInt(32);
						if(grid[randRow][randCol] == '.') {
							grid[randRow][randCol] = '$';
						}
					}
					grid[row+1][column] = '@';
					grid[row][column] = '.';
				}

				else {
					grid[row + 1][column] = '@';
					grid[row][column] = '.';
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println("Invalid move, try again");
			}
			// enemy movement
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if(grid[x][y] == '&') {
						int k = rng.nextInt(4);
						if(k == 1) {
							if(grid[x+1][y] == '.') {
								grid[x][y] = '.';
								grid[x+1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 2) {	
							if(grid[x-1][y] == '.') {
								grid[x][y] = '.';
								grid[x-1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 3) {
							if(grid[x][y+1] == '.') {
								grid[x][y] = '.';
								grid[x][y+1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}
						else {
							if(grid[x][y-1] == '.') {
								grid[x][y] = '.';
								grid[x][y-1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}
					}
				}
			}
			printBoard();
		}

		//Move right
		if (play == 'd') {
			//If character runs into item, choose to pick it up
			if (grid[row][column + 1] == 'O') {
				genItem = new ItemGenerator();
				Item item = genItem.generate(); //Generate a new random item

				System.out.printf("You've found %s (Weight: %d, Value: %d Strength: %d)! %n", item.getName(), item.getWeight(), item.getValue(), item.getStrength());
				System.out.print("Would you like to add it to your inventory (Y or N)? ");
				char choice = input.next().charAt(0);
				if ((choice == 'y') || (choice == 'Y')) {
					inventory.add(item);
					inventory.print();
				}

			}
			else if (grid[row][column+1] == 'D'){
				System.out.println("Would you like to go through the door (Y or N)?");
				char choice2 = input.next().charAt(0);
				if((choice2 == 'y') || (choice2 == 'Y')){
					roomChange(badGuyIDCounter);
				}
			}
			try {
				//Don't allow character to move through walls
				if ((grid[row][column + 1] == '|') | (grid[row][column + 1] == '_')) {
					grid[row][column] = '@';
				}
				//Eat food
				else if (grid[row][column + 1] == '*') {
					Food food = genFood.generate();
					grid[row][column + 1] = '@';
					grid[row][column] = '.';
					Character.player().eatFood(food);
				}
				//If character runs into enemy, call Battle method
				else if ((grid[row][column + 1] == '&')) {
					genEnemy = new EnemyGenerator();
					Enemy enemy = genEnemy.generate();
					boolean battleResult = battle(enemy, player);
					if (battleResult) {
						grid[row][column] = '.';
						grid[row][column + 1] = '@';
					}
					if (!battleResult) {
						grid[row][column] = '@';
					}

				}
				//If character runs into potion (!), choose a weapon or armor to boost damage
				else if ((grid[row][column + 1] == '!')) {
					genPotion = new PotionGenerator();
					Potion potion = genPotion.generate();
					grid[row][column + 1] = '@';
					grid[row][column] = '.';

					inventory.usePotion(potion);
				}

				else if(grid[row][column+1] == '$') {
					market = new Shop();
					System.out.println("Welcome to the shop! Here is what's in stock:");
					market.print();
					inventory.priceCheck();
					System.out.println("Would you like to buy or sell something? Press 'B' to buy, 'S' to sell, and 'E' to exit.");
					char shopChoice = input.next().charAt(0);
					if(shopChoice == 'B' || shopChoice == 'b') {
						market.buy(inventory);
					}else if(shopChoice == 'S' || shopChoice == 's') {
						market.sell(inventory);
					}
					int randRow = 0;
					int randCol = 0;
					while(grid[randRow][randCol] != '$' && grid[randRow][randCol] != grid[row][column+1]) {
						randRow = rng.nextInt(32);
						randCol = rng.nextInt(32);
						if(grid[randRow][randCol] == '.') {
							grid[randRow][randCol] = '$';
						}
					}
					grid[row][column+1] = '@';
					grid[row][column] = '.';
				}

				else {
					grid[row][column + 1] = '@';
					grid[row][column] = '.';
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				System.out.println("Invalid move, try again");

			}
			// enemy movement
			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if(grid[x][y] == '&') {
						int k = rng.nextInt(4);
						if(k == 1) {
							if(grid[x+1][y] == '.') {
								grid[x][y] = '.';
								grid[x+1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 2) {	
							if(grid[x-1][y] == '.') {
								grid[x][y] = '.';
								grid[x-1][y] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else if(k == 3) {
							if(grid[x][y+1] == '.') {
								grid[x][y] = '.';
								grid[x][y+1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}else {
							if(grid[x][y-1] == '.') {
								grid[x][y] = '.';
								grid[x][y-1] = '&';
							} else {
								grid[x][y] = '&';
							}
						}
					}
				}
			}
			printBoard();
		}
	}

}
