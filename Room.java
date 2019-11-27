//This class stores additional rooms. When the player defeats all enemies in the previous room, a new Room object will be generated.

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class Room extends Board {
	private FileInputStream fileIn;
	private Scanner scnr;
	private String[] lines = new String[32];
	private char[][] grid = new char[32][32];

	public Room(String file) {
		try{
			fileIn = new FileInputStream(file);
			scnr = new Scanner(fileIn);

		}
		catch(FileNotFoundException e){
			System.out.println("Room text file was not found");
			System.exit(2);
		}
		for (int i = 0; i < 32; i++){
			lines[i] = scnr.nextLine();
		}
		for (int x = 0; x < 32; x++){
			char[] holdLine = lines[x].toCharArray();
			for ( int y = 0; y < 32; y++){
				grid[x][y] = holdLine[y];
			}
		}
	}
	public void printBoard() {
		for (char[] space : grid) {
			for (char j : space) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}


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
				//If player runs into food (*), eat the food and increase plaer's health
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
				else if (grid[row-1][column] == 'D') {
                                        room2.printBoard();
                                        //while (input.hasNext()) {
                                        //      do {
                                        //              choice = input.next().charAt(0);
                                        room2.play(choice);

                                        //      } while ((choice != 'Q') | (choice != 'q'));

                                        //                                      }

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
				else if (grid[row][column - 1] == 'D') {
                                        room2.printBoard();
                                        //while (input.hasNext()) {
                                        //      do {
                                        //              choice = input.next().charAt(0);
                                        room2.play(choice);

                                        //      } while ((choice != 'Q') | (choice != 'q'));

                                        //                                      }

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
				else if (grid[row+1][column] == 'D') {
                                        room2.printBoard();
                                        //while (input.hasNext()) {
                                        //      do {
                                        //              choice = input.next().charAt(0);
                                        room2.play(choice);

                                        //      } while ((choice != 'Q') | (choice != 'q'));

                                        //                                      }

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
					//while (input.hasNext()) {
					//	do {
					//		choice = input.next().charAt(0);
					room2.play(choice);

					//	} while ((choice != 'Q') | (choice != 'q'));

					//					}

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
