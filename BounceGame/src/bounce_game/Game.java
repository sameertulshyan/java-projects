package bounce_game;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * This class contains the main method to start the game. The game requires the user to use a mouse-tracking board 
 * to prevent the ball from touching the bottom of the screen. The game keeps track of a score that increases the longer the user plays.
 * @author sameertulshyan
 * @version 0.1
 */
public class Game extends PApplet {
	
	private int score = 0; //variable to keep track of user score
	private Board board = new Board(255, 0, 255, this); //create a Board object, supplying integers to set color and a class reference
	private Ball ball = new Ball(0, 0, 255, this); //similar process for a Ball object
	
	/**
	 * Creates a window for the game to run in (overrides PApplet's default)
	 */
	public void settings() {
		this.size(600, 400);
	}
	
	/**
	 * Initial set up method which creates a black background (overrides PApplet's default)
	 */
	public void setup() {
		this.background(0, 0, 0); //create a black background for the game	
	}
	
	/**
	 * Draw method loops multiple times per second, responsible for moving, displaying and checking the position of the Objects (overrides PApplet's default)
	 */
	public void draw() {
		this.background(0, 0, 0); //reset the screen with every loop to a plain black background
		
		if (this.ball.isOver()) { //use a method from the Ball class to see if the Ball has touched the bottom of the screen
			gameOver(); //call the method to display the end screen to the user
		}
		
		this.ball.move(); //update the ball's location
		this.ball.draw(); //draw the ball
		
		this.board.move(); //update the board's location
		this.board.draw(); //draw the board
		
		checkCollision(); //call method to check if ball has touched board
		this.score += 1; //increment the score with every loop
		
	}
	
	/**
	 * Method to determine if the Ball and Board objects have collided, and alter the Ball's trajectory accordingly
	 */
	public void checkCollision() {
		if (this.ball.getYPosition() >= this.board.getYPosition() && this.ball.getXPosition() >= this.board.getXPosition() && this.ball.getXPosition() 
				<= this.board.getXPosition() + this.board.getRectWidth()) { //condition to determine if objects are in the same location
			this.ball.setYSpeed(-1); //reverse the Ball's direction
			this.ball.setYPosition(); //update the Ball's position
		}
	}
	
	/**
	 * Method to show the Game Over screen, with necessary text information and author image
	 */
	public void gameOver() {
		noLoop(); //stops the draw method's loop to ensure the score stops increasing and the objects do not move
		fill(255, 255, 255); //sets the fill color to white to display the end message in white text
		PImage img = loadImage("src/author.jpg"); //loads an image of the author
		image(img, 200, 100); //displays the author's image
		
		//displays text informing the user of their score, and the author's information
		text("Game over, thanks for playing. Your score was: " + this.score + "\nThis game was made by Sameer Tulshyan.", 150, 50);
	}
	
	public static void main(String[] args) {
		PApplet.main("bounce_game.Game"); //main method from the PApplet class to start the program
	}
}
