//This class stores additional rooms. When the player defeats all enemies in the previous room, a new Room object will be generated.
public class Room extends Board {

	public Room() {
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
}
