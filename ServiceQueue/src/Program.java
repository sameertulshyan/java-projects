
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Main class that reads the input files, instantiates the Queue, simulates the service and produces the desired output
 * @author sameertulshyan
 * @version 0.1
 */
public class Program {
	public static void main(String[] args) {
		CustomersQueue customerQueue = new CustomersQueue(); //Queue will be used to model movement of Customers through the day
		ArrayList<Customer> customerList = new ArrayList<Customer>(); //List will be used to store and preserve Customer data after they leave
		int nextAvailableTime = 0; //int representing the nextAvailableTime that a Customer can be served
		int servingTime = parseCustomers(customerList, customerQueue, args[0]); //int representing the amount of time in seconds per service
		int longestQueueSize = 0; //int representing the maximum size of the queue for the day

		int nextCustomer = customerQueue.getLast().getID() + 1; //Get the ID of the Customer at the back of the Queue and add 1 to get the next Customer
		int longestBreak = 0; //int representing the longest continuous break for the employee between service
		int totalBreakTime = 0; //int representing the total break time for the employee
		int currentBreak = 0; //int representing the currentBreak time. Will be reset whenever a Customer is served.
		int customersServed = 0; //int representing the total number of Customers who received service
		
		//Model the passage of time using a loop, where each iteration represents a second between 9AM and 5PM
		for (int i = 0; i <= 28800; i++) {
			//check if the next customer in the list has an arrival time of i
			if (nextCustomer < customerList.size() && customerList.get(nextCustomer).getArrivalTime() == i) { 
				customerQueue.enqueue(customerList.get(nextCustomer)); //add them to the customer queue
				if (customerQueue.size() > longestQueueSize) { //check to see if the queue is at its longest
					longestQueueSize = customerQueue.size(); //update longestQueueSize accordingly
				}
				nextCustomer++; //increment the index of the next customer
			}
			
			//now we need to check if the employee is free to serve a customer at this time
			if (nextAvailableTime <= i) {
				if (customerQueue.isEmpty()) { //if there are no customers waiting
					totalBreakTime++; //the employee has a break
					currentBreak++;
				} else { //else we need to serve the next customer
					if (currentBreak > longestBreak) { //since the break is over, check its duration against the longest break thus far
						longestBreak = currentBreak; //update longestBreak accordingly
					}
					currentBreak = 0; //since we are serving a customer, this break is over
					Customer currentCustomer = customerQueue.dequeue(); //get the customer from the queue
					if (currentCustomer.getArrivalTime() < 0) { //if they arrived before 9AM
						currentCustomer.setWaitingTime(Math.abs(currentCustomer.getArrivalTime()) + i); //set the waiting time accordingly 
					} else { //otherwise they arrived after 9AM, so set the waiting time to i - arrivalTime
						currentCustomer.setWaitingTime(i - currentCustomer.getArrivalTime());
					}
					customersServed++; //increment the total number of customers served
					nextAvailableTime = i + servingTime; //the employee will be busy serving this customer for the next (servingTime) seconds
				}
			}
		}
		
		//Now that service is concluded, remove any customers that arrived before 5PM but did not get served
		while (!customerQueue.isEmpty()) {
			Customer c = customerQueue.dequeue();
			c.setWaitingTime(28800 - c.getArrivalTime()); //set their waiting time to 5PM - arrivalTime
		}
		
		//Additionally, there may be customers who arrived after 5PM
		while (nextCustomer < customerList.size()) { //they will be in the list but not in the queue
			Customer c = customerList.get(nextCustomer);
			c.setWaitingTime(0); //set their waiting time to 0
			nextCustomer++;
		}
		
		
		ArrayList<String> queries = parseQueries(args[1]); //now we need to get the queries from the queriesfile
		
		//loop through the list of queries, and determine their output accordingly
		for (String q : queries) {
			if (q.contains("WAITING-TIME-OF")) { //if the query asks for a particular customers waiting time
				int index = Character.getNumericValue(q.charAt(q.length() - 1)); //get the customerID from the query
				System.out.println(q + ":" + customerList.get(index).getWaitingTime()); //the customer will be present at the corresponding index in the List
			} else if (q.contains("NUMBER-OF-CUSTOMERS-SERVED")) { //output the other metrics as necessary
				System.out.println(q + ":" + customersServed);
			} else if (q.contains("LONGEST-BREAK-LENGTH")) {
				System.out.println(q + ":" + longestBreak);
			} else if (q.contains("TOTAL-IDLE-TIME")) {
				System.out.println(q + ":" + totalBreakTime);
			} else if (q.contains("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME")) {
				System.out.println(q + ":" + longestQueueSize);
			} else { //let the user know if the file contains an unrecognized command, but proceed through the rest of the file normally
				System.out.println("Error. The following command is not supported: " + q);
			}
		}
	}
	
	/**
	 * Method designed to get and parse the relevant information from the customersfile. This method will instantiate new Customer objects, and 
	 * add them to the ArrayList of Customers. If necessary, some Customers will also be added to the Queue prior to the start of service.
	 * @param customerList
	 * @param customerQueue
	 * @param filename
	 * @return an int representing the amount of time in seconds for each Customer to be served
	 */
	public static int parseCustomers(ArrayList<Customer> customerList, CustomersQueue customerQueue, String filename) {
		int servingTime = 0;
		try {
			Scanner input = new Scanner(new File(filename)); //instantiate a Scanner object to browse the file
			servingTime = Integer.parseInt(input.nextLine()); //the first line of the file will be the serving time for each Customer
			
			//We need to treat the first customer separately, as their ID will be used to populate the Customer List
			input.nextLine();
			
			String[] IDString = input.nextLine().split(":");
			int ID = Integer.parseInt(IDString[1].trim()); //once we have the first customer's ID
			
			for (int i = 0; i < ID; i++) { //loop through the ArrayList of Customers
				customerList.add(i, null); //set all previous ID numbers to null to avoid errors
			}
			
			String[] arrivalString = input.nextLine().split(":"); //continue processing the first customer's details
			int arrivalTime = convertTime(arrivalString);
			Customer newCustomer = new Customer(ID, arrivalTime); //instantiate them as a Customer object
			
			if (newCustomer.getArrivalTime() < 0) {
				customerQueue.enqueue(newCustomer); 
			}
			
			customerList.add(ID, newCustomer);
			
			while (input.hasNextLine()) { //now we can proceed through the rest of the input file
				input.nextLine();
				
				IDString = input.nextLine().split(":");
				ID = Integer.parseInt(IDString[1].trim()); //get each ID
				
				arrivalString = input.nextLine().split(":"); //get each Arrival Time
				arrivalTime = convertTime(arrivalString); //call the method to convert the arrival time to number of seconds after 9AM
				newCustomer = new Customer(ID, arrivalTime); //instantiate a new Customer object
				
				if (newCustomer.getArrivalTime() < 0) { //if they arrived before 9AM
					customerQueue.enqueue(newCustomer); //they should already be in the Queue before the start of service
				}
				
				customerList.add(ID, newCustomer); //since we filled previous indices with null, we can map Customer ID's to the ArrayList index for easy access
			}
			
			input.close(); //close the File Scanner
			
		} catch (FileNotFoundException e) { //if the filepath supplied as an argument to the program cannot be found
			System.err.println("Error. The customersfile could not be found. Please verify the path/filename and run the program again");
			System.exit(1); //the program cannot be used
		}
		
		return servingTime;
	}
	
	/**
	 * Method converts the provided String into an int representing the number of seconds since 9AM. Negative values represent arrival times before 9AM.
	 * @param arrivalString
	 * @return
	 */
	public static int convertTime(String[] arrivalString) {
		int hours = Integer.parseInt(arrivalString[1].trim());
		int minutes = Integer.parseInt(arrivalString[2]);
		int seconds = Integer.parseInt(arrivalString[3]);
		int arrivalTime = 0;
		
		if (hours < 9 && hours > 5) {
			arrivalTime = - ((8 - hours)*3600 + (60 - minutes)*60 - seconds); //arrival time before 9 will be represented as negative 
		} else if (hours < 9) {
			arrivalTime = 3*3600 + hours*3600 + minutes*60 + seconds; //arrival times in the afternoon
		} else {
			arrivalTime = (hours - 9)*3600 + minutes*60 + seconds; //arrival times in the morning
		}
		return arrivalTime;
	}
	
	/**
	 * Method designed to get and parse queries from the queriesfile. Separates each query and adds them to an ArrayList of Strings.
	 * @param filename
	 * @return ArrayList<String> where each String is a query
	 */
	public static ArrayList<String> parseQueries(String filename) {
		ArrayList<String> queries = new ArrayList<String>(); //instantiate the ArrayList
		try {
			Scanner input = new Scanner(new File(filename)); //instantiate the Scanner
			
			while (input.hasNextLine()) { //add each query
				queries.add(input.nextLine()); //to the ArrayList
			}
			input.close(); //close the Scanner
			
		} catch (FileNotFoundException e) { //if the filepath supplied to the program cannot be found
			System.err.println("Error. The queriesfile could not be found. Please verify the path/filename and run the program again");
			System.exit(1); //no queries can be answered, and thus the program cannot be used
		}
		return queries;
	}
}
