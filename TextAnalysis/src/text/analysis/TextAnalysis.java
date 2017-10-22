package text.analysis;
/**
 * This program opens a text file and analyzes the text for occurrences of 'verbal tics' based on user input.
 * The user will provide the file name, and the list of 'tics' to be analyzed. The program will display the results of the analysis.
 * @author sameertulshyan
 * @version 0.2
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TextAnalysis {
	static Scanner keyboard = new Scanner(System.in); //create a Scanner object to get user input
	static double totalOccurrences = 0; //declare a static variable to track total occurrences across multiple methods
	
	/**
	 * A simple method to request a filename from user, and store the contents of the file in a String
	 * @return a String containing the text from the file
	 * @throws FileNotFoundException
	 */
	public static String getText() throws FileNotFoundException {

		System.out.print("Which file would you like to open? Remember to place the file in the src folder: "); //prompt the user to enter a file name
		String fileName = keyboard.nextLine(); //store the filename in a String variable

		Scanner input = new Scanner(new File("src/" + fileName)); //create a File object

		String text = " ";
		
		while (input.hasNextLine()) { 
			text += input.nextLine() + " "; //store the text from the file in a string variable
		}
		
		input.close(); //close the File object
		return text.toLowerCase(); //ensure the analysis does not ignore tics that occur at the beginning of a sentence
	}

	/**
	 * A simple method that gets a list of words to be analyzed for from the user and stores them in an array
	 * @return a String Array containing the words that need to be analyzed
	 */
	public static String[] getWords() {
		System.out.print("Which words would you like to search for? Enter multiple words separated by a comma: "); //prompt the user to enter their tics
		String userWords = keyboard.nextLine(); 
		String[] wordsArray = userWords.split(", "); //store the list of tics entered by the user in an array

		keyboard.close();
		return wordsArray; //return the array for use in the analysis
	}
	
	/**
	 * This method finds the number of occurrences of each specified word in the body of text
	 * The method also finds the total occurrences and stores them in the static variable totalOccurrences
	 * @param text String containing text from the text file
	 * @param wordsArray the Array containing each word that the user wishes to analyze
	 * @return an integer array of the corresponding number of occurrences of each word
	 */
	public static int[] getOccurrences(String text, String[] wordsArray) {
		int[] occurrences = new int[wordsArray.length]; //create an array to store the occurrences 
		
		for (int i = 0; i < wordsArray.length; i++) { // loop through each tic 
			int count = 0; //create a variable local to the loop to track occurrences of each tic
			
			String[] textArray = text.split(" " + wordsArray[i]); //use a space to ensure that tics are not counted when they are suffixes of larger words
			count = textArray.length - 1; //account for the fact that the split method will return an extra count because we are splitting using the tics
			
			occurrences[i] = count; //store the occurrences in the corresponding index value of the occurrences Array 
			totalOccurrences += count; //add the occurrences for each tic to the total number of occurrences
		}
		
		return occurrences; //return the array in order to display it from the main method
	}
	
	/**
	 * A simple method to determine the percentage that each word constitutes versus the total number of occurrences
	 * @param totalOccurrences The total number of occurrences determined from the getOccurrences method
	 * @param getOccurrences The array of occurrences for each word determined from the getOccurrences method
	 * @return a double array of the corresponding percentage of each word versus the total number of occurrences
	 */
	public static double[] getPercentages(double totalOccurrences, int[] getOccurrences) {
		double[] percentages = new double[getOccurrences.length]; //create an array to store the percentage occurrences
		
		for (int i = 0; i < getOccurrences.length; i++) { //loop through each tic
			
			if (totalOccurrences == 0) { //prevent a divide by zero error in case no occurrences at all
				percentages [i] = 0;
			} else {
				double percentageValue = (getOccurrences[i] / totalOccurrences) * 100; //calculate the percentage using the stored values in the getOccurrences array
				percentages[i] = percentageValue; //store the percentage value in the corresponding index value of the percentages array
			}
		}
		return percentages; //return the array to display it from the main method
	}
		

	public static void main(String[] args) {
		
		String text = ""; //create a variable to store the text from the user's file
		try {
			text = getText(); //attempt to obtain the text from the user and store it in the variable
		} catch (Exception e) { 
			System.out.println("That file could not be found. Make sure the file is placed in the src folder and run the program again.");
			System.exit(1); //exit the program if the user's file cannot be found
		}
		
		String[] wordsArray = getWords(); //obtain a list of tics from the user and store it in an array
		int[] occurrences = getOccurrences(text, wordsArray); //pass the variables into the getOccurrences method and obtain an integer array
		
		System.out.println("\n\tAnalyzing text..."); //display text to let the user know what is happening
		System.out.printf("%nTotal number of occurrences: %.0f%n" , totalOccurrences); //display the total number of 'tics' or occurrences 
		
		//calculate the density by storing each word from the text in an array, and dividing totalOccurences by the length of that array
		System.out.printf("Density of Occurrences: %.2f%n", (totalOccurrences / text.split(" ").length)); //Display the density to the user
		
		double[] percentages = getPercentages(totalOccurrences, occurrences); //pass the variables into the getPercentages method and obtain a double array
		
		System.out.println("\n\tText breakdown...\n"); //display text to let the user know the summary is ready
		
		for (int i = 0; i < wordsArray.length; i++) { //loop through each tic and display the analysis to the user
			System.out.printf("%-20s / %10d occurrences \t/ %10.1f%% of all occurrences%n", wordsArray[i], occurrences[i], percentages[i]); //format neatly
			//note that the percentages are formatted to 1 decimal place for accuracy (avoids totals of > 100%)
		}
		
	}
}