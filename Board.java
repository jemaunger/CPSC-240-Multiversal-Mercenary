import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Board  {
	//Instance variables
	private Scanner input = new Scanner(System.in);
	private Random rng = new Random();
	private char choice;

	private static int badGuyIDCounter = 12;
	static Board instance = new Board();
	private Character player;

	private EnemyGenerator genEnemy = new EnemyGenerator();
	private ItemGenerator genItem = new ItemGenerator();
	private FoodGenerator genFood = new FoodGenerator();
	
	//Instance variables to be saved.
	private char characterLocation;
	private int characterHealth;
	private Inventory characterItems;
	private String TOP_DELIM = "|.";
	private String BOT_DELIM = ".|";
	private Inventory inventory = new Inventory(100); //max weight can be whatever
	private char[][] grid = {{'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
		{'|', '&', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '@', '*', '*', '.', '.', '.', '.', '.', '_', '_', '_', '_', '_', '_', '_', '.', '.', '|'},
		{'|', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '|', '.', '.', '.', '.', '.', '|', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', 'O', '.', '|', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '|', 'O', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '.', '|', '&', '.', '.', '.', '.', '|', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '_', '_', '.', '_', '_', '|', '.', '.', '|'},
		{'|', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '_', '_', '_', '_', '_', '_', '_', '_', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '|'},
		{'|', '.', '*', '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '|', '.', 'O', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '|', '_', '_', '_', '_', '_', '_', '|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '_', '_', '_', '.', '_', '_', '_', '_', 'O', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '&', '.', '.', '.', '.', '.', '.', '.', '|', '&', '.', '.', 'O', '.', '.', '|', '.', '.', '&', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', 'O', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'O', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '.', '.', '.', '.', '.', '|', '_', '_', '_', '_', '_', '_', '|', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'|', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '|'},
		{'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'}
	};

	private Board() {
	}

	//Singleton
	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	//restores the state of the saved game, including the characters location health, items, also all of the placements of enemies and items across the board.
	/*public Board(Scanner s){
		String firstDelim = "";
		String lastDelim = "";
		if (choice.equals('R')){
			TOP_DELIM = firstDelim;
			s.nextLine();
			BOT_DELIM = lastDelim;
			s.nextLine();
		}
	}*/

	/*
	//Trying to read in from a file
	public char[][] testBoard() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("Board1.txt"));
		int height = scan.nextInt();
		scan.nextLine();
		int width = scan.nextInt();
		scan.nextLine();
		char[][] newBoard = new char[height][width];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				scan.nextLine();
			}
		}
		return newBoard;
	}
*/

	//Display the game board to the screen 
	public void printBoard() {
		for (char[] space : grid) {
			for (char j : space) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}

	//Print menu of options
	public void printMenu() {
		this.player = Character.player();

		System.out.println("Actions: ");
		System.out.println("     D = Drop an item in inventory");
		System.out.println("     I = Print item inventory");
		System.out.println("     H = Print current health status");
		System.out.println("     M = Print menu");
		System.out.println("     Q = Quit game");
		System.out.println("	 S = Save game");
		System.out.println("	 R = Restore saved game"); 
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
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
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
					try {

					Thread.sleep(2000);
					//input.nextLine();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				} else {
					playerHealth = playerHealth - enemyDamage;
					player.setHealth(playerHealth);
					System.out.printf("You attacked %s for %d damage. %n", enemyName, playerDamage);
					System.out.printf("%s attacked you for %d damage. %n", enemyName, enemyDamage);
					System.out.printf("Enemy health total is at %d. %n", enemyHealth);
					System.out.printf("Your health total is at %d. %n", playerHealth);
					try {

                                        
					Thread.sleep(2000);
                                       // input.nextLine();
					} catch (InterruptedException e) {
                                               Thread.currentThread().interrupt();
                                        }
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
				System.out.println("Game Over");
				System.exit(0);
				return false;
			} else if (badGuyIDCounter == 0) {
				System.out.println("You defeated all of the enemies! You win");
				System.exit(0);
				return false;
			} else {
				System.out.printf("%d more enemies remain... %n", badGuyIDCounter);
				System.out.println();
				return true;

			}
		}
		return false;
	}

	//Eat food and boost health
	public void eatFood(Food food) {
		System.out.printf("You've found %s and it boosts your health by %d %n", food.getName(), food.getHealth());
		//Increase health
		Character.player().increaseHealth(Character.player().getHealth(), food.getHealth());
		//Set player health to new health
		player.setHealth(Character.player().getHealth()+food.getHealth());

		System.out.printf("Your new health is %s. %n", Character.player().getHealth());
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
		}

		//Print inventory
		if (play == 'I') {
			inventory.print();
		}

		//Print menu again
		if (play == 'M') {
			printMenu();
		}

		//Exit game
		if (play == 'Q') {
			System.out.println("Thank you for playing!");
			System.exit(0);
		}

		//Movements
		//Move up
		if (play == 'w') {
			//If character runs into an item (O), choose to pick it up or not
			if (grid[row - 1][column] == 'O') {
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
					eatFood(food);
					printBoard();
				}


				//If character runs into enemy (&), choose to battle or not
				else if ((grid[row - 1][column] == '&')) {
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
				//
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
					eatFood(food);
					printBoard();
				}

				//If character runs into enemy, choose to battle or not
				else if ((grid[row][column - 1] == '&')) {
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
					eatFood(food);
					printBoard();
				}

				//If character runs into enemy, choose to battle
				else if ((grid[row + 1][column] == '&')) {
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
					eatFood(food);
					printBoard();
				}
				//If character runs into enemy, call Battle method
				else if ((grid[row][column + 1] == '&')) {
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

				} else {
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
