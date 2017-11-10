package moped;
/**
 * A class to encapsulate all of the necessary data fields and functions for a Moped object
 * @author sameertulshyan
 * @version 0.1
 */
public class Moped {
	//set default values for data fields
	private int fuelLevel = 100; //initialize Moped with full gas tank
	private int currentAve = 5; //default location
	private int currentStreet = 10;
	private String direction = "South"; //default direction
	private boolean facingForward = true; //default orientation
	private final int MAX_AVE = 10, MIN_AVE = 1, MAX_STREET = 200, MIN_STREET = 1; //constants for grid limits
	
	/**
	 * Method to determine whether the Moped needs to print an advertising message
	 */
	public void checkAdvertising() {
		//determine if Moped is at any of the advertiser locations, and display message accordingly
		if (this.currentAve == 8 && this.currentStreet == 79) {
			System.out.println("\nWelcome to the American Museum of Natural History!\n");
		} else if (this.currentAve == 1 && this.currentStreet == 74) {
			System.out.println("\nWelcome to Memorial Sloan Kettering!\n");
		} else if (this.currentAve == 4 && this.currentStreet == 12) {
			System.out.println("\nWelcome to The Strand book shop!\n");
		} else if (this.currentAve == 6 && this.currentStreet == 3) {
			System.out.println("\nWelcome to Fayda Coffee Tea Cookies Cake!\n");
		}
	}
	
	/**
	 * Method to ensure the Moped is not driven off the grid or has not run out of fuel
	 */
	public void checkLimits() {
		//compare Moped location to grid limits and exit if necessary 
		if (this.currentAve > MAX_AVE || this.currentAve < MIN_AVE || this.currentStreet < MIN_STREET || this.currentStreet > MAX_STREET) {
			System.out.println("You have driven the Moped off the grid! The program will now exit...");
			System.exit(1);
		} 
		
		if (this.fuelLevel == 0) { //check fuel level and exit if necessary
			System.out.println("You have run out of fuel! The Moped no longer drives, and the program will now exit...");
			System.exit(1);
		}
	}
	
	/**
	 * Method to perform the necessary critical checks every time the Moped is ordered to move, and output the location after the move
	 */
	public void checkMove() {
		this.fuelLevel -= 5; //each move burns 1/20th of the fuel
		checkLimits(); //call the method to check location and fuel limits
		System.out.printf("Now at %d St. and %d Ave. heading %s...\n", this.currentStreet, this.currentAve, this.direction); //display location for user
		checkAdvertising(); //call the method to check if the Moped has reached an advertiser location
	} 
	
	/**
	 * Method to fill the gas tank upon user request
	 */
	public void refuel() {
		this.fuelLevel = 100; //set the fuel level back to 100
		System.out.println("Tank has been filled. Current fuel level at 100%"); //let the user know that the tank has been refilled
	}
	
	/**
	 * Method to display the current fuel level upon user request
	 */
	public void getFuelLevel() {
		System.out.printf("The gas tank is currently %d%% full\n", this.fuelLevel); //display the fuel level as a percentage
	}
	
	/**
	 * Method to display a list of commands upon user request (excluding the help command since it has to be entered to call this method)
	 */
	public void getHelp() {
		System.out.println("\nList of directional commands:\n"); //sort commands into directional
		System.out.println("'go left', 'go right', 'straight on', 'back up', 'go to Petite Abeille'\n");
		System.out.println("List of other commands:\n"); //and other commands
		System.out.println("'Park' - park your Moped on the sidewalk and exit the program");
		System.out.println("'How we doin'?'- check how much fuel the Moped has left");
		System.out.println("'Fill 'er up' - refill the gas tank\n");
	}
	
	/**
	 * Method to park the Moped and exit program upon user request
	 */
	public void park() {
		System.out.println("\nYour Moped has been safely parked on the sidewalk, and the program will now exit...");
		System.exit(0);
	}
	
	/**
	 * Method to move the Moped left. Note that the movement depends on the direction the Moped is facing, as well as whether it is in reverse or forward gear.
	 */
	public void goLeft() {
		
		if (this.direction.equals("South")) { //determine the Moped's direction
			this.currentAve--; //update the location accordingly
			if (this.facingForward) { //determine the Moped's orientation
				this.direction = "East"; //update the direction accordingly
			} else {
				this.direction = "West";
			}
		} else if (this.direction.equals("North")) { //do the same for each possible direction
			this.currentAve++;
			if (this.facingForward) {
				this.direction = "West";
			} else {
				this.direction = "East";
			}
		} else if (this.direction.equals("East")) {
			this.currentStreet++;
			if (this.facingForward) {
				this.direction = "North";
			} else {
				this.direction = "South";
			}
		} else if (this.direction.equals("West")) {
			this.currentStreet--;
			if (this.facingForward) {
				this.direction = "South";
			} else {
				this.direction = "North";
			}
		}
		
		checkMove(); //call the method to update fuel, output location, check limits and advertising
	}
	
	/**
	 * Method to move the Moped right. Note that the movement depends on the direction the Moped is facing, as well as whether it is in reverse or forward gear.
	 */
	public void goRight() {
		//similar structure to goLeft method
		if (this.direction.equals("South")) {
			this.currentAve++;
			if (this.facingForward) {
				this.direction = "West";
			} else {
				this.direction = "East";
			}
		} else if (this.direction.equals("North")) {
			this.currentAve--;
			if (this.facingForward) {
				this.direction = "East";
			} else {
				this.direction = "West";
			}
		} else if (this.direction.equals("East")) {
			this.currentStreet--;
			if (this.facingForward) {
				this.direction = "South";
			} else {
				this.direction = "North";
			}
		} else if (this.direction.equals("West")) {
			this.currentStreet++;
			if (this.facingForward) {
				this.direction = "North";
			} else {
				this.direction = "South";
			}
		}
		
		checkMove(); 
	}
	
	/**
	 * Method to move the Moped forward in whatever direction it is facing (without changing the direction). This method also puts the Moped in forward gear.
	 */
	public void goStraight() {
		this.facingForward = true; //update orientation to forward 
		
		if (this.direction.equals("South")) { //determine the Moped's direction
			this.currentStreet--; //update the location accordingly
		} else if (this.direction.equals("North")) {
			this.currentStreet++;
		} else if (this.direction.equals("East")) {
			this.currentAve--;
		} else if (this.direction.equals("West")) {
			this.currentAve++;
		}
		
		checkMove(); //call the method to update fuel, output location, check limits and advertising
	}
	
	/**
	 * Method to move the Moped backwards in whatever direction it is facing (without changing the direction). This method also puts the Moped in reverse gear.
	 */
	public void goBack() {
		this.facingForward = false; //update orientation to reverse
		//similar structure to goStraight method
		if (this.direction.equals("South")) {
			this.currentStreet++;
		} else if (this.direction.equals("North")) {
			this.currentStreet--;
		} else if (this.direction.equals("East")) {
			this.currentAve++;
		} else if (this.direction.equals("West")) {
			this.currentAve--;
		}
		
		checkMove();
	}
	
	/**
	 * This method automatically drives the Moped from its current location to the Petite Abeille on 17th St. and 6th Ave. one block at a time 
	 */
	public void goHome() {
		this.facingForward = true; //face the Moped forward
		
		while (this.currentAve != 6) { //drive the Moped until it reaches 6th Ave
			this.fuelLevel = 100; //avoid running out of fuel during the auto drive
			
			if (this.currentAve < 6) {
				this.direction = "West"; //update the direction
				this.currentAve++; //move the Moped accordingly
				checkMove(); //display the location
				
			} else if (this.currentAve > 6) {
				this.direction = "East";
				this.currentAve--;
				checkMove();
			}
		}
		
		while (this.currentStreet != 17) { //drive the Moped until it reaches 17th St
			this.fuelLevel = 100; //avoid running out of fuel during the auto drive
			
			if (this.currentStreet < 17) {
				this.direction = "North";
				this.currentStreet++;
				checkMove();
				
			} else if (this.currentStreet > 17) {
				this.direction = "South";
				this.currentStreet--;
				checkMove();
			}
		}
		
		System.out.println("\nWelcome to the Petite Abeille!\n"); //let the user know that the Moped has arrived
	}
	
	
}
