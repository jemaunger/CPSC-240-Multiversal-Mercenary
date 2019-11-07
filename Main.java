import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Explain setting and plot here
        System.out.println("All events you see in this game are real. They simply take place a long time ago, in a " +
                "galaxy far, far away...");
        System.out.println();
        System.out.println("The multidimensional barrier has been torn open, throwing a multitude of beings and items into our world.");
        System.out.println("Unfortunately, through a sheer statistical anomaly, all but one of these beings are evil!");
        System.out.println("Our hero, torn from their own world and/or time, now fights to rid our world of evil before ");
        System.out.println("returning to their own world (which will happen offscreen).");
        System.out.println("Can you help guide them toward where they need to be?");

        System.out.println();

        System.out.println("Your goal: Defeat all of the enemies on the board (denoted by &).");
        System.out.println("At the beginning of each battle, you may choose which armor and weapon to use during the fight.");
        System.out.println();

        //Generate a new board and print it to the screen

        System.out.printf("Welcome, %s! %n", Character.player().getName());

        System.out.println("Below is list of symbols and actions that you can make with your character.");
        System.out.println("To begin, please choose an action from the menu: ");
        System.out.println("(You must press enter between every action)");
        Board.instance.printMenu();
        Board.instance.printBoard();
        System.out.print(": ");

        while (input.hasNext()) {
            char play;
            do {
                play = input.next().charAt(0);
                Board.instance.play(play);

             } while (play != 'Q');
        }
    }
}
