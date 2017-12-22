package edu.nyu.cs.st2851.assignment7;
import java.util.ArrayList;
/**
 * This class represents sentences in a language. Implements the SequentiallyOrdered interface and inherits from the OrderedThing class.
 * @author sameertulshyan
 * @version 0.1
 */
public class Sentence extends OrderedThing implements SequentiallyOrdered {
	private ArrayList<Word> words = new ArrayList<>(); //ArrayList to store Word objects representing each Word in this Sentence
	
	/**
	 * Constructor to convert a String to individual words, which are then stored in the words ArrayList
	 * @param s a String supplied to the constructor representing a Sentence
	 */
	public Sentence(String s) {
		String[] spl = s.split("\\s+"); //split the String into a String Array by whitespace
		
		for (int i = 0; i < spl.length; i++) { //iterate through the spl array
			Word w1 = new Word(spl[i], i); //instantiate a new Word object for each element
			this.words.add(w1); //add the Word object to the words ArrayList
		}
	}
	
	/**
	 * Implements the getFirst() method of the SequentiallyOrdered interface
	 */
	public Word getFirst() {
		return this.words.get(0); //return the Word object at index 0 of the words ArrayList
	}
	
	/**
	 * Implements the getLast() method of the SequentiallyOrdered interface
	 */
	public Word getLast() {
		return this.words.get(this.words.size() - 1); //return the Word object at last index of the words ArrayList
	}
	
	/**
	 * Implements the getSequence() method of the SequentiallyOrdered interface.
	 */
	public ArrayList<OrderedThing> getSequence() {
		ArrayList<OrderedThing> objects = new ArrayList<>(); //ArrayList of OrderedThings, needed as the method must return an ArrayList of this type
		
		for (Word w : this.words) { //iterate through the words ArrayList
			objects.add(w); //add each Word object to the objects ArrayList, using implicit casting to convert them to OrderedThings
		}
		
		return objects; //return the objects ArrayList
	}
}
