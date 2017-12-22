package word.analysis;
import java.util.ArrayList;
/**
 * This class represents words in a language, implementing the SequentiallyOrdered interface and inheriting from the OrderedThing class
 * @author sameertulshyan
 * @version 0.1
 */
public class Word extends OrderedThing implements SequentiallyOrdered {
	private ArrayList<MyCharacter> characters = new ArrayList<>(); //ArrayList to store MyCharacter objects representing each letter of this word
	private int orderedPosition; //int representing the position of this word in the sentence that it was instantiated from (if applicable)
	
	/**
	 * Constructor to convert a String to a Word, and assign its orderedPosition
	 * @param s a String supplied that represents a word
	 * @param orderedPosition an int supplied that represents the index of this word in the sentence it was instantiated from
	 */
	public Word(String s, int orderedPosition) {
		this.setOrderedPosition(orderedPosition); //use setter to set the orderedPosition property
		char[] chars = s.toCharArray(); //split the String representing the word into a char array
		
		for (char c: chars) { //iterate through the char array
			MyCharacter c1 = new MyCharacter(c); //instantiate a new MyCharacter object for each element
			this.characters.add(c1); //add the object to the characters ArrayList
		}
	}
	
	/**
	 * Implements the getFirst() method of the SequentiallyOrdered interface
	 */
	public MyCharacter getFirst() {
		return this.characters.get(0); //return the MyCharacter object at index 0 of the characters ArrayList
	}
	
	/**
	 * Implements the getLast() method of the SequentiallyOrdered interface
	 */
	public MyCharacter getLast() {
		return this.characters.get(this.characters.size() - 1); //return the MyCharacter object at the last index of the characters ArrayList
	}
	
	/**
	 * Implements the getSequence() method of the SequentiallyOrdered interface
	 */
	public ArrayList<OrderedThing> getSequence() {
		ArrayList<OrderedThing> objects = new ArrayList<>(); //ArrayList of OrderedThings, needed as the method must return an ArrayList of this type
		
		for (MyCharacter c : this.characters) { //iterate through the characters ArrayList
			objects.add(c); //add each MyCharacter object to the objects ArrayList, implicitly casting them as OrderedThing objects
		}
		return objects; //return the objects ArrayList
	}
	
	/**
	 * Getter for the orderedPosition property
	 * @return the orderedPosition of this object
	 */
	public int getPosition() {
		return this.orderedPosition;
	}
	
	/**
	 * Setter for the orderedPosition property
	 * @param orderedPosition
	 */
	public void setOrderedPosition(int orderedPosition) {
		this.orderedPosition = orderedPosition;
	}
	
	/**
	 * Overrides Object class' toString() method to provide a useful representation of this Word object
	 */
	public String toString() {
		String word = ""; //create a String to store the word
		
		for (MyCharacter c : this.characters) { //iterate through the characters ArrayList
			word += c.getCharacter(); //use the getCharacter() method to get the char representation of this object and add it to the String
		}
		
		return word; //return the String
	}
 }
