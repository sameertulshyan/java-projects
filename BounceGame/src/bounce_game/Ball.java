package bounce_game;
import processing.core.PApplet;
/**
 * This class stores the properties and methods of the Ball object to be used in the Game class
 * @author sameertulshyan
 * @version 0.1
 */
public class Ball extends PApplet {
	private int xPosition = 50; //int to store the ball's position, starts at the top left corner of the screen
	private int yPosition = 50; //int to store vertical position
	private int xSpeed = 5; //default horizontal rate of change of movement for the ball
	private int ySpeed = 5; //default vertical rate of change
	private int color; //int value to store the color of the Board. Can be customized using one of the constructors.
	
	Game parent; //holds a reference to the Game class
	
	/**
	 * Basic constructor sets the Ball color to white. Requires the parent class reference for Processing.
	 * @param parent Reference passed from Game class
	 */
	public Ball(Game parent) {
		this.color = color(255, 255, 255);
		this.parent = parent;
	}
	
	/**
	 * Overloaded constructor sets the Ball color according to the supplied arguments. Requires the parent class reference for Processing.
	 * @param red int value representing red
	 * @param green int value representing green
	 * @param blue int value representing blue
	 * @param parent Reference passed from Game class
	 */
	public Ball(int red, int green, int blue, Game parent) {
		this.color = color(red, green, blue); //set ball color accordingly
		this.parent = parent;
	}
	
	/**
	 * Overloaded constructor sets the speed of the ball
	 * @param speed integer used to indicate the rate of change of the ball's direction
	 * @param parent Reference passed from Game class
	 */
	public Ball(int speed, Game parent) {
		this.color = color(255, 255, 255);
		this.parent = parent;
		this.xSpeed = speed; //update the horizontal speed
		this.ySpeed = speed; //update the vertical speed
	}
	
	/**
	 * Getter method for the longitude
	 * @return int value representing longitude
	 */
	public int getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Getter method for the latitude
	 * @return int value representing the latitude
	 */
	public int getYPosition() {
		return this.yPosition;
	}
	
	/**
	 * Setter method for vertical speed
	 * @param i int supplied from Game class- mainly used when collision occurs
	 */
	public void setYSpeed(int i) {
		this.ySpeed *= i; //changes the speed or reverses the ball's direction as needed
	}
	
	/**
	 * Getter method for the vertical speed
	 * @return int value representing vertical speed
	 */
	public int getYSpeed() {
		return this.ySpeed;
	}
	
	/**
	 * Setter method for the latitude. Called when collision occurs to update direction.
	 */
	public void setYPosition() {
		this.yPosition += this.ySpeed; //move the ball away from the collision
	}
	
	/**
	 * Method to update the Ball's position
	 */
	public void move() {
		if (this.yPosition <= 0) { //check boundary at the top
			this.ySpeed *= -1; //change direction
			this.yPosition += this.ySpeed; //start moving away from boundary
		} else {
			this.yPosition += this.ySpeed; //continue in the same direction
		} //note that we check the bottom boundary in the separate isOver method
		
		if (this.xPosition >= 600) { //check boundary to the right
			this.xSpeed *= -1;
			this.xPosition += this.xSpeed;
		} else if (this.xPosition <= 0) { //check boundary to the left
			this.xSpeed *= -1;
			this.xPosition += this.xSpeed;
		} else { //continue in the same direction
			this.xPosition += this.xSpeed; 
		}
	}
	
	/**
	 * Loops multiple times per second, creating a new ball and displaying it to simulate movement
	 * Overrides PApplet's draw method
	 */
	public void draw() {
		parent.ellipse(this.xPosition, this.yPosition, 25, 25);
		parent.fill(this.color);
	}
	
	/**
	 * Determines if the ball has touched the bottom of the screen. Used by Game class to check if the game should be ended.
	 * @return boolean true if the ball has touched the bottom
	 */
	public boolean isOver() {
		if (this.yPosition >= 400) {
			return true;
		} else {
			return false;
		}
	}
	
}
