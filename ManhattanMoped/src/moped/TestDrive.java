package moped;
/**
 * This program tests the Moped Class by creating a Moped object and allowing the user to enter commands to call various methods
 * @author sameertulshyan
 * @version 0.1
 */
import java.util.Scanner;

public class TestDrive {
	public static void main(String[] args) { 
		
		Moped moped1 = new Moped(); //create a Moped object for the user to drive
		System.out.println("Welcome to the moped. Current location is Dr. Rossinsky DDS' office at 10th St and 5th Ave."); //display a welcome message
		
		//create a loop to repeatedly get user input until one of the moped methods exits the program
		while (true) { //note that the loop is infinite because there are break conditions built into the object methods
			
			Scanner keyboard = new Scanner(System.in); //create a Scanner object to get user input 
			System.out.print("Enter a command, or 'help' for assistance: "); //prompt the user for input
			String command = keyboard.nextLine().toLowerCase(); //get the input, ignoring the case
			keyboard.close();
			//validate the input and call the appropriate method
			if (command.equals("go left")) {
				moped1.goLeft();
			} else if (command.equals("go right")) {
				moped1.goRight();
			} else if (command.equals("straight on")) {
				moped1.goStraight();
			} else if (command.equals("back up")) {
				moped1.goBack();
			} else if (command.equals("how we doin'?")) {
				moped1.getFuelLevel();
			} else if (command.equals("fill 'er up")) {
				moped1.refuel();
			} else if (command.equals("park")) {
				moped1.park();
			} else if (command.equals("go to petite abeille")) {
				moped1.goHome();
			} else if (command.equals("help")) {
				moped1.getHelp();
			} else {
				System.out.println("That is not a valid command!");
			}
		}		
		
	}
}
