package edu.nyu.cs.st2851.assignment7;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class tests the various methods used by the Sentence class, and by extension, the Word and MyCharacter classes and the SequentiallyOrdered interface
 * @author sameertulshyan
 * @version 0.1
 */
public class TestSequence {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in); //instantiate a Scanner object to get user input
		
		System.out.print("Enter a sentence: "); //prompt user for a sentence
		String s = keyboard.nextLine();
		
		Sentence sentence = new Sentence(s); //instantiate a sentence object with the user's sentence
		
		//demonstrate use of Sentence class methods
		System.out.println("The first word of your sentence is: " + sentence.getFirst()); //show use of getFirst() method
		//note that the overriden toString() method defined in sentence class has been automatically called in the previous and next line
		System.out.println("The last word of your sentence is: " + sentence.getLast()); //show use of getLast() method
		
		Random myRandom = new Random(); //instantiate a Random object to select a random word
		System.out.println("\nChoosing a random word from your sentence...\n");
		ArrayList<OrderedThing> words = sentence.getSequence(); //use the getSequence method to get an ArrayList of OrderedThings representing the words in the sentence
		Word randomWord = (Word)words.get(myRandom.nextInt(words.size())); //Choose a random word from the ArrayList and explicitly cast it into a Word object
		
		System.out.println("The randomly chosen word is: " + randomWord); //display the word to the user
		
		//demonstrate use of Word class methods, starting with the getPosition() method
		System.out.println("The position of " + randomWord + " in your sentence is: " + (randomWord.getPosition())); //note that position refers to index, e.g. 0 for 1st word
		System.out.println("The first letter of " + randomWord + " is: " + randomWord.getFirst()); //show use of getFirst() method
		System.out.println("The last letter of " + randomWord + " is: " + randomWord.getLast()); //show use of getLast() method
		
		System.out.print("\nEnter a number to find the letter at that position (e.g. 0 for 1st letter): "); //prompt the user for an int representing position
		int position = keyboard.nextInt();
		
		ArrayList<OrderedThing> characters = randomWord.getSequence(); //use the getSequence method to get an ArrayList of OrderedThings representing the characters in the word
		
		System.out.printf("The letter at position %d in %s is: %s", position, randomWord, characters.get(position)); //note that position refers to index, e.g. 0 for 1st letter
		
		keyboard.close(); //close the Scanner
	}
}
