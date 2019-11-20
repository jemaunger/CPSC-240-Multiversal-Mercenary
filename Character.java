
import java.util.Scanner;

//This class creates the character that the player will be using. It is singleton as there will only ever be one character at a time.
public class Character {
	static Scanner input = new Scanner(System.in);
	private static String name;
	private static CharacterRace charClass;
	private static Character character;
	private static int health = 100;
	private static int armor = 0;
	private static int damage = 1;

	//This initializes the Character as a singleton.
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
			character = new Character(tempName, tempClass);
		}
		return character;
	}

	//Character initialization only requires a name and class.
	private Character(String name, CharacterRace charClass) {
		this.name = name;
		this.charClass = charClass;
	}

	//This sets the players armor based on their equipped armor within their inventory. Armor acts as a barrier before the players health. During battle, the enemy must reduce this armor stat to 0 before they can decrease the player's health.
	public static void setFullArmor(Item item) {
		if(item == null) {
			armor = 0;
			return;
		}
		armor = item.getStrength();
	}

	//This sets the player's damage based upon their equipped weapon. During battle, the strength of the player's attack is determined by this stat. If the player has no weapon equipped, the default damage is 1.
	public static void setDamage(Item item) {
		if(item == null) {
			damage = 1;
			return;
		}
		damage = item.getStrength();
	}

	//This method increases the character's health, but not above maximum.
	public static void increaseHealth(int currentHealth, int restored) {
		int newHealth = currentHealth + restored;
		health = newHealth;
	}

	//This method decreases the character's health.
	/* We never used this method, but didn't want to delete it just in case
	public static void decreaseHealth(int currentHealth, int damageTaken) {
		int newHealth = currentHealth - damageTaken;
		if (newHealth < 0)
			newHealth = 0;
		health = newHealth;
	}
	*/

	public String getName() {
		return name;
	}
	
	/* Same here
	public CharacterRace getRace() {
		return charClass;
	}
	*/

	public int getHealth() {
		return health;
	}

	public int getArmor() {
		return armor;
	}

	public int getDamage() {
		return damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}

