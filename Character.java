import java.util.Scanner;
/**
 * Represents the character the player assumes the position of when they start the game, type in their players name and choose the {@link CharacterRace}.
 * @author Ethan Pearson, Jema Unger
 */
public class Character extends Being{

	//Instance variables
	static Scanner input = new Scanner(System.in);
	private static String name;
	private static CharacterRace charClass;
	private static Character character;
	private static int health = 100;
	private static int armor = 0;
	private static int damage = 1;

	/**
	 * Synchronized constructor that makes Character a singleton, to ensure there can only be one player on the board at a time.
	 * In addition it prompts the player at the beginning of the game to choose their {@link CharacterRace}
	 * @return the character instance after instantiating a new character to ensure there will only be one.
	 */
	public static synchronized Character player() {
		if (character == null) {
			System.out.print("What is your character's name? ");
			String tempName = input.nextLine();
			System.out.println();
			CharacterRace tempClass = null;
			while (tempClass == null) {
				System.out.println("What is your class?");
				System.out.println("1. Viking");
				System.out.println("2. Ninja");
				System.out.println("3. Samurai");
				System.out.println("4. Jedi");
				System.out.println("5. Sith");
				System.out.println("6. Superhero");
				int choice = input.nextInt();
				if (choice == 1)
					tempClass = CharacterRace.Viking;
				else if (choice == 2)
					tempClass = CharacterRace.Ninja;
				else if (choice == 3)
					tempClass = CharacterRace.Samurai;
				else if (choice == 4)
					tempClass = CharacterRace.Jedi;
				else if (choice == 5)
					tempClass = CharacterRace.Sith;
				else if (choice == 6)
					tempClass = CharacterRace.Superhero;
				else
					System.out.println("That's not a class! Please pick again.");
			}
			System.out.println();
			character = new Character(tempName);
		}
		return character;
	}
	/**
	 * A restore constructor which is used to restore the state of the character after the game has been saved from the Game.sav file.
	 * @param s scanner used to restore the characters attributes from a save file.
	 */
	public Character(Scanner s){
		s = new Scanner("Game.sav");
		this.name = s.next();
		this.charClass = null;
		s.skip("null");
		this.health = s.nextInt();
		this.armor = s.nextInt();
		this.damage = s.nextInt();
		s.nextLine();
		this.charClass = null;
	}
	/**
	 * Constructs the name given to the character by the player as well as the {@link CharacterRace} chosen by the player.
	 * @param name to retrieve the players name of their character at the beginning of the game.
	 */
	public Character(String name) {
		this.name = name;
		this.charClass = charClass;
	}
	/**
	 * Used when the player comes in contact with food represented by "*" on the board and adds the health value of the food to the players health.
	 * @param food allows the method to access the food available to the player and allows them to interact with it.
	 */
	public void eatFood(Food food) {
		System.out.printf("You've found %s and it boosts your health by %d %n", food.getName(), food.getHealth());
		//Increase health
		//Character.player().increaseHealth(Character.player().getHealth(), food.getHealth());
		//Set player health to new health
		setHealth(Character.player().getHealth()+food.getHealth());

		System.out.printf("Your new health is %s. %n", Character.player().getHealth());
	}
	/**
	 * When the player chooses to eat food this method is called to add the amount of health the player currently has to the amount that will be restored by the food.
	 * @param currentHealth represents the amount of health the player currently has before adding the food.
	 * @param restored the amount of health that was restored to the player from the food.
	 */
	public static void increaseHealth(int currentHealth, int restored) {
		int newHealth = currentHealth + restored;
		health = newHealth;
	}
	/**
	 * Used to set the amount of health that will be added to the players at the beginning of the battle based on their choice of armor.
	 * @param item allows the method to access the items in the players inventory.
	 */
	public static void setFullArmor(Item item) {
		if(item == null) {
			armor = 0;
			return;
		}
		armor = item.getStrength();
	}
	/**
	 * Used to set the amount of damage the players choice of weapon will do when they choose to battle an enemy.
	 * @param item allows the method to access the items in the players inventory to be equipped as weapons.
	 */
	public static void setDamage(Item item) {
		if(item == null) {
			damage = 1;
			return;
		}
		damage = item.getStrength();
	}
	/**
	 * Used to retrieve the name of the character which is constructed above when the player entered their desired name. 
	 * @return the name of the player which they entered at the beginning of the game when prompted.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the amount of health the player is given, which starts at 100 and can be increased with no limit, it retrieves this information from the constructor.
	 * @return the amount of health the player has throughout the game such as when the character battles an enemy.
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * When the player comes in contact with an enemy and chooses to battle, they are prompted to choose which armor they would like to use. 
	 * This method retrieves that information from the constructor.
	 * @return the armor the player has chosen to equip at the start of a battle.
	 */
	public int getArmor() {
		return armor;
	}
	/**
	 * When the player comes in contact with an enemy and chooses to battle, this method is used to get the amount of damage the item will do from the constructor.
	 * @return the amount of damage the player is given when they choose a weapon to battle with.
	 */
	public int getDamage() {
		return damage;
	}
	/**
	 * Sets the players health at the beginning of the game, during battle with enemies and when the player chooses to eat a piece of food.
	 * @param health is used to get the health that the player currently has and set it in the parameters.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Method used to construct a string to be printed out so the player can know their name, race, health, armor, and damage. 
	 * @return the players name, race, health, armor, and damage as a string.
	 */
	public String toString() {
		return name + " " + charClass + " " + health + " " + armor + " " + damage;
	}
}
