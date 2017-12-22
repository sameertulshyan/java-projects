package edu.nyu.cs.st2851.assignment7;
/**
 * This class represents a single character
 * @author sameertulshyan
 * @version 0.1
 */
public class MyCharacter extends OrderedThing {
	private char character; //char representation of a single character
	
	/**
	 * Constructor that sets the character property based on a char supplied
	 * @param character char value of a single character 
	 */
	public MyCharacter(char character) {
		this.setCharacter(character); //use the setter for the character property
	}
	
	/**
	 * Setter for the character property
	 * @param character char value to set the relevant instance field accordingly
	 */
	public void setCharacter(char character) {
		this.character = character;
	}
	
	/**
	 * Getter for the character property
	 * @return the char character for this object
	 */
	public char getCharacter() {
		return this.character;
	}
	
	/**
	 * Overrides Object class' toString() method to provide a useful representation of this MyCharacter object
	 */
	public String toString() {
		return "" + this.getCharacter(); //use getter to obtain char, convert to String and return
	}
}
