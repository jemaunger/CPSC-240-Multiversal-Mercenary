//This class stores additional rooms. When the player defeats all enemies in the previous room, a new Room object will be generated.

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Room extends Board{
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

	public void play(char play) {
		while (input.hasNext()) {
                        char play;
                        do {
                                play = input.next().charAt(0);
                                super.play(play);

                        } while ((play != 'Q') | (play != 'q'));
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

}
