package bounce_game;
import processing.core.PApplet;
/**
 * This class contains the methods and properties for the Board object to be used by the Game class
 * @author sameertulshyan
 *
 */
public class Board extends PApplet {
	private int xPosition; //int to store the longitude of the Board- will track the user's mouse movement
	private int yPosition = 375; //int to store the latitude of the Board- this will not change, as only horizontal movement is permitted
	private int color; //int value to store the color of the Board. Can be customized using one of the constructors.
	private int rectWidth = 64; //int value to store the width of the board
	private int rectLength = 16; //int value to store the length of the board
	
	Game parent; //holds a reference to the Game class
	
	/**
	 * Basic constructor sets the Board color to white. Requires the parent class reference for Processing.
	 * @param parent Reference passed from Game class
	 */
	public Board(Game parent) {
		this.color = color(255, 255, 255); //set board color to white
		this.parent = parent;
	}
	
	/**
	 * Overloaded constructor sets the Board color according to the supplied arguments. Requires the parent class reference for Processing.
	 * @param red int value representing red
	 * @param green int value representing green
	 * @param blue int value representing blue
	 * @param parent Reference passed from Game class
	 */
	public Board(int red, int green, int blue, Game parent) {
		this.color = color(red, green, blue); //set board color accordingly
		this.parent = parent;
	}
	
	/**
	 * Getter method for the latitude
	 * @return int value representing the latitude
	 */
	public int getYPosition() {
		return this.yPosition;
	}
	
	/**
	 * Getter method for the longitude
	 * @return int value representing longitude
	 */
	public int getXPosition() {
		return this.xPosition;
	}
	
	/**
	 * Getter method for the width
	 * @return int value representing width
	 */
	public int getRectWidth() {
		return this.rectWidth;
	}
	
	
	/**
	 * Loops multiple times per second, creating a new rectangle and displaying it to simulate movement
	 * Overrides PApplet's draw method
	 */
	public void draw() {
		parent.rect(this.xPosition, 375, this.rectWidth, this.rectLength);
		parent.fill(this.color);
	}
	
	/**
	 * Updates the latitude of the Board, based on the position of the User's mouse
	 */
	public void move() {
		this.xPosition = parent.mouseX;
	}
}
