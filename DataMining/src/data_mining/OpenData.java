package data_mining;
/**
 * This program allows a user to obtain a list of Women's Organizations in NYC. The user is able to mine the data by borough.
 * The data has been taken from https://data.cityofnewyork.us/Social-Services/NYC-Women-s-Resource-Network-Database/pqg4-dm6b/data
 * The columns used are OrganisationName, Brooklyn, Bronx, Manhattan, Queens, Staten Island, Phone, URL 
 * @author sameertulshyan
 * @version 0.1
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class OpenData {	
	static Scanner keyboard = new Scanner(System.in); //create a Scanner object to get user input across methods
	/**
	 * This method gets the data for a particular borough and supplies it to the getArray method. 
	 * @param borough The name of the borough that the method needs to get data for
	 * @return a String array containing the data for that particular borough
	 * @throws FileNotFoundException
	 */
	public static String[] getData(String borough) throws FileNotFoundException {
		Scanner input = new Scanner(new File("src/nyc_data.txt")); //create a Scanner object to get input from the file
		String inputData = ""; //create a String to store the data
		
		while (input.hasNextLine()) {
			inputData += input.nextLine(); //get the data
		}
		
		int arraySize = 0; //create a variable to store the size of the array required
		String[] temp = inputData.split("\\t" + borough); //create a temporary string array to parse the data
		arraySize = temp.length - 1;  //note that the last element of temp is not wanted (contains data for all other boroughs)
		

		String[] boroughData = new String[arraySize]; //move the data from temp into boroughData
		for (int i = 0; i < temp.length - 1; i++) {
			boroughData[i] = temp[i];
		}

		Arrays.sort(boroughData); //sort the data alphabetically for user readability
		input.close();
		
		return boroughData;
	}
	/**
	 * This method creates an array to store the data and populates it from the text file. The data varies with each borough, and is obtained from the getData method.
	 * @return a 2D String array containing the program's data
	 * @throws FileNotFoundException
	 */
	public static String[][] getArray() throws FileNotFoundException {
		String[][] data = new String[5][]; //initialize and allocate a 2D String array for the data
		data[0] = getData("Brooklyn"); //create each 'column' as an array from the getData method
		data[1] = getData("Bronx");
		data[2] = getData("Manhattan");
		data[3] = getData("Queens");
		data[4] = getData("Staten Island");
		return data;
	}
	
	/**
	 * This method prints the data requested by the user, with proper formatting
	 * @param data String array supplied by main method containing the data for the desired borough
	 * @param boroughName String supplied by main method containing the name of the desired borough
	 */
	public static void printData(String[] data, String boroughName) {
		System.out.printf("\nFound %d women's organizations in %s:\n", data.length, boroughName); //overview of results for user
		System.out.printf("\n%-80s %-20s %-20s\n\n", "Organization Name", "Phone Number", "Website"); //header for data
		for (int i = 0; i < data.length; i++) {
			String[] line = data[i].split("\\t"); //split each line into name, phone number, website
			System.out.printf("%-80s %-20s %-20s\n", line[0], line[1], line[2]); //formatted output
			if ((i + 1) % 10 == 0) { //get user input after every 10 to continue
				System.out.println("\nPress enter to continue or enter q to quit: ");
				if (keyboard.nextLine().toLowerCase().equals("q")) {
					System.exit(0); //exit program if user wants to quit
				}
			}
		}
	}
	
	/**
	 * This method prints the introduction to the program, with an overview of function and the source of the data 
	 */
	public static void printWelcome() {
		System.out.println("Welcome to the Women's Resource Finder App."); //program overview
		System.out.println("This app mines data from https://data.cityofnewyork.us/Social-Services/NYC-Women-s-Resource-Network-Database/pqg4-dm6b/data");
		System.out.println("We show you Women's Organizations for any borough in NYC, including their phone number and website\n");
	}
	
	public static void main(String[] args) throws FileNotFoundException {

		String[][] data = getArray(); //get the data
		
		printWelcome(); //print out the introduction
		
		String borough = ""; //create a String for the user's response
		
		//prompt the user to enter a borough name, and validate the input
		while (!borough.equals("Brooklyn") && !borough.equals("Bronx") && !borough.equals("Manhattan") && 
				!borough.equals("Queens") && !borough.equals("Staten Island")) { 
			System.out.print("Enter a borough in NYC e.g. Brooklyn (remember to capitalize the first letter of each word): ");
			borough = keyboard.nextLine();
		}
		
		//call the printData method based on the user's input
		if (borough.equals("Brooklyn")) {
			printData(data[0], "Brooklyn");
		} else if (borough.equals("Bronx")) {
			printData(data[1], "Bronx");
		} else if (borough.equals("Manhattan")) {
			printData(data[2], "Manhattan");
		} else if (borough.equals("Queens")) {
			printData(data[3], "Queens");
		} else if (borough.equals("Staten Island")) {
			printData(data[4], "Staten Island");
		}
		
		
	}
}
