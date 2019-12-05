import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Board  {
	//Instance variables
	private FileInputStream fileIn;
	private Scanner scnr;
	private static String fileName = "room1.txt";
	private static Board instance;
	private String[] lines = new String[32];
	private char[][] grid = new char[32][32];
	Inventory inventory;
	private static int badGuyIDCounter = 13;

	//Protected 
	protected char choice;
	protected Character player;
	protected EnemyGenerator genEnemy;
	protected FoodGenerator genFood;
	protected ItemGenerator genItem = new ItemGenerator();
	protected Random rng = new Random();
	protected Room room2;
	protected Scanner input = new Scanner(System.in);

	//Instance variables to be saved.
	private char characterLocation;
	private int characterHealth;
	private Item characterItems;

	public void saveGame(PrintWriter pw){
		pw.println(player);
		pw.println(Arrays.deepToString(grid));
		pw.println(inventory);

		pw.close();
		System.out.println("File saved successfully!");
	}

	//restores the state of the saved game, including the characters location health, items, also all of the placements of enemies and items across the board.
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

	//Singleton
	public static synchronized Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	//Display the game board to the screen 
	public void printBoard() {
		for (char[] space : grid) {
			for (char j : space) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}

	//Battle
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

					if (badGuyIDCounter == 11) {
						Room room2 = new Room("room2.txt");
						grid[12][31] = 'D';
					}
					try {

						Thread.sleep(2000);
						//input.nextLine();
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

				//If player has defeated all of the enemies and is in Room 1, create a new Room object called room2
			} else if ((badGuyIDCounter == 0) & (fileIn.equals("room1.txt"))) {
				System.out.println("You defeated all of the enemies! But there are still more rooms...");
				//Create a new room
				return false;

				//Using this to test generating a new room, will delete later
			}
			//	else if (badGuyIDCounter == 11) {
			//	Room room2 = new Room();
			/*	else if (badGuyIDCounter == 11) {
				Room room2 = new Room()

				}*/

			//Using this to test generating a new room, will delete later	


			else if (badGuyIDCounter <= 0) {
				room2 = new Room("room2.txt");
				grid[12][32] = 'D';
			}

			else {
				System.out.printf("%d more enemies remain... %n", badGuyIDCounter);
				System.out.println();

				return false;
			}
		}

		return false;
		}
		//Game actions (move, equip, drop, etc)
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
			}

			//Print inventory
			if (play == 'I') {
				inventory.print();
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

				try {
					//Don't allow character to move through walls
					if ((grid[row - 1][column] == '|') | (grid[row - 1][column] == '_')) {
						grid[row][column] = '@';
						printBoard();
					}

					else if (grid[row - 1][column] == '*') {
						Food food = genFood.generate();
						grid[row - 1][column] = '@';
						grid[row][column] = '.';
						Character.player().eatFood(food);
						printBoard();
					}


					//If character runs into enemy (&), choose to battle or not
					else if ((grid[row - 1][column] == '&')) {
						genEnemy = new EnemyGenerator();
						Enemy enemy = genEnemy.generate();
						boolean battleResult = battle(enemy, player);
						if (battleResult) {
							grid[row][column] = '.';
							grid[row - 1][column] = '@';
							printBoard();
						}
						if(!battleResult) {
							grid[row][column] = '@';
							printBoard();
						}
					}

					else {
						grid[row - 1][column] = '@';
						grid[row][column] = '.';
						printBoard();
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

				try {
					//Don't allow character to move through walls
					if ((grid[row][column - 1] == '|') | (grid[row][column - 1] == '_')) {
						grid[row][column] = '@';
						printBoard();
					}

					else if(grid[row][column - 1] == '*') {
						Food food = genFood.generate();
						grid[row][column - 1] = '@';
						grid [row][column] = '.';
						Character.player().eatFood(food);
						printBoard();
					}

					//If character runs into enemy, choose to battle or not
					else if ((grid[row][column - 1] == '&')) {
						genEnemy = new EnemyGenerator();
						Enemy enemy = genEnemy.generate();
						boolean battleResult = battle(enemy, player);
						if (battleResult) {
							grid[row][column] = '.';
							grid[row][column - 1] = '@';
							printBoard();
						}
						if (!battleResult) {
							grid[row][column] = '@';
							printBoard();
						}

					} else {
						grid[row][column - 1] = '@';
						grid[row][column] = '.';
						printBoard();
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
				try {
					//Don't allow character to move through walls
					if ((grid[row + 1][column] == '|') | (grid[row + 1][column] == '_')) {
						grid[row][column] = '@';
						printBoard();
					}

					else if (grid[row + 1][column] == '*') {
						Food food = genFood.generate();
						grid[row + 1][column] = '@';
						grid[row][column] = '.';
						Character.player().eatFood(food);
						printBoard();
					}

					//If character runs into enemy, choose to battle
					else if ((grid[row + 1][column] == '&')) {
						genEnemy = new EnemyGenerator();
						Enemy enemy = genEnemy.generate();
						boolean battleResult = battle(enemy, player);
						if (battleResult) {
							grid[row][column] = '.';
							grid[row + 1][column] = '@';
							printBoard();
						}
						if (!battleResult) {
							grid[row][column] = '@';
							printBoard();
						}

					} else {
						grid[row + 1][column] = '@';
						grid[row][column] = '.';
						printBoard();
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
				try {
					//Don't allow character to move through walls
					if ((grid[row][column + 1] == '|') | (grid[row][column + 1] == '_')) {
						grid[row][column] = '@';
						printBoard();
					}
					//Eat food
					else if (grid[row][column + 1] == '*') {
						Food food = genFood.generate();
						grid[row][column + 1] = '@';
						grid[row][column] = '.';
						Character.player().eatFood(food);
						printBoard();
					}
					//If character runs into enemy, call Battle method
					else if ((grid[row][column + 1] == '&')) {
						genEnemy = new EnemyGenerator();
						Enemy enemy = genEnemy.generate();
						boolean battleResult = battle(enemy, player);
						if (battleResult) {
							grid[row][column] = '.';
							grid[row][column + 1] = '@';
							printBoard();
						}
						if (!battleResult) {
							grid[row][column] = '@';
							printBoard();
						}

					} else if (grid[row][column + 1] == 'D') {
						room2.printBoard();
						while (input.hasNext()) {
							do {
								choice = input.next().charAt(0);
								play(choice);

							} while ((choice != 'Q') | (choice != 'q'));

						}

					}
					else {
						grid[row][column + 1] = '@';
						grid[row][column] = '.';
						printBoard();
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
			}
		}

	}
