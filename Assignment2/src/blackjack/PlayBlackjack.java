package blackjack;
/**
 * This program allows a single user to play the game blackjack against a simulated dealer
 * The player with the highest total wins, as long as that total does not exceed 21
 * This is a simplified version, with all cards acting as integer values from 2 to 11
 * @author Sameer Tulshyan
 * @version 0.1
 */
import java.util.Random;
import java.util.Scanner;

public class PlayBlackjack {
	static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * A simple method to simulate the drawing of a random card between 2-11
	 * @return the value of the card as an integer
	 */
	public static int DrawCard() {
		Random myRandom = new Random(); //create a random object to generate the values
		int card = myRandom.nextInt(10) + 2; //generate a value between 2-11 and store the value in an int variable
		return card;
	}
	
	/**
	 * A simple method to compare user and dealer totals, display the winner of the game and exit the program
	 * @param userTotal an integer parameter used to keep track of the user's score
	 * @param dealerTotal an integer parameter used to keep track of the dealer's score
	 */
	public static void Endgame(int userTotal, int dealerTotal) {
		System.out.println("Your total: " + userTotal); //display both totals for the user
		System.out.println("Dealer's total: " + dealerTotal);
				
		if ((dealerTotal > userTotal || userTotal > 21) && (dealerTotal <= 21)) { //select an output based on totals
			System.out.println("The dealer wins!");
		} else if (userTotal > dealerTotal || dealerTotal > 21) {
			System.out.println("Congratulations! You win!");
		} else if (userTotal == dealerTotal) {
			System.out.println("We have a tie!");
		}
		keyboard.close();
		System.exit(0); //end the program
	}
	
	/**
	 * A simple method to simulate the dealer's playing of the game
	 * @param userTotal an integer parameter used to supply the user's score to the endgame function if necessary
	 * @param dealerTotal an integer parameter used to keep track of the dealer's score
	 * @return the updated value of the dealer's total
	 */
	public static int DealerPlays(int userTotal, int dealerTotal) {
		while (dealerTotal <= 16) { //the dealer will always hit as long it has a total <= 16
			System.out.println("The dealer chooses to hit"); //let the player know what the dealer has chosen to do
			dealerTotal += DrawCard(); //add another card to the dealer's total
			
			if (dealerTotal > 21) { //check for bust and end the program if it occurs
				Endgame(userTotal, dealerTotal);
			}
		} 
		System.out.println("The dealer chooses to stay"); //let the player know what the dealer has chosen to do
		return dealerTotal;
	}
	
	public static void main(String[] args) {
		int user1 = DrawCard(); //generate first card for user		
		int user2 = DrawCard(); //generate second card for user
		int userTotal = user1 + user2; //track the user's total
		System.out.println("Your hand consists of a " + user1 + " and a " + user2); //display the user's hand
		
		int dealerTotal = DrawCard() + DrawCard(); //generate a hand for the dealer and track its total 
		
		//Scanner keyboard = new Scanner(System.in); //create a Scanner object to get input from the user
		String userResponse = ""; //create a variable to track the user's response
		
		do { //create a loop to keep asking the user for a decision
			System.out.print("Enter (s)tay, (h)it or (p)ass: "); //prompt the user for a decision
			userResponse = keyboard.nextLine();
			if (userResponse.equals("h")) {
				int newCard = DrawCard(); //generate a new card for the user
				System.out.println("Your new card is a " + newCard); //display the new card to the user
				userTotal += newCard; //add the new card to the user's total
				
				if (userTotal > 21) { //check for bust and end the program if it occurs
					Endgame(userTotal, dealerTotal);
				}
			}
			
			else if (userResponse.equals("p")) { //if the user chooses to pass, the dealer plays
				dealerTotal = DealerPlays(userTotal, dealerTotal);
			}
		} while (!userResponse.equals("s"));
		
		if (userResponse.equals("s")) { //if the user chooses to stay, the dealer plays
			dealerTotal = DealerPlays(userTotal, dealerTotal);
		}
		
		Endgame(userTotal, dealerTotal); //end the game
	}

}
