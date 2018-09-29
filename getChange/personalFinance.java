import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
      String output = getChange(line); //call our getChange function
      System.out.println(output); //print the output provided by the function
    }
  }
  
  /**
   * Parse the input, compute the necessary trades and output to the user 
   */
  public static void getFinalTrades(String input) {
    String[] portfolio = new String[4]; //initialize arrays for input processing
    String[] benchmark = new String[4];
    
    try {
      String[] firstSplit = input.split(":"); //split the input into portfolio and benchmark
      portfolio = firstSplit[0].split("\\|"); //split the result again by asset using regex and store in array
      benchmark = firstSplit[1].split("\\|");
    } catch (Exception e) {
      System.out.println("INVALID INPUT FORMAT ERROR"); //inform the user that the input is not correctly formatted
    }
    
    //Sort the arrays to obtain aplhabetically ordered assets
    Arrays.sort(portfolio);
    Arrays.sort(benchmark);
    
    //iterate over assets
    for (int i = 0; i < portfolio.length; i++) {
      String[] portfolioAsset = portfolio[i].split(","); //split each asset by its components
      String[] benchmarkAsset = benchmark[i].split(",");
      
      //determine the difference in shares between benchmark and portfolio
      int sharesDifference = Integer.parseInt(benchmarkAsset[2]) - Integer.parseInt(portfolioAsset[2]);
      
      //determine the actions to take based on the difference
      if (sharesDifference == 0) { //amount is the same, no trades necessary for this asset
        continue;
      } else if (sharesDifference > 0) { //need to buy more of this asset
        System.out.println("BUY," + portfolioAsset[0] + "," + sharesDifference);
      } else { //need to sell more of this assrt
        System.out.println("SELL," + portfolioAsset[0] + "," + Math.abs(sharesDifference));
      }
    } 
  }
  
  /**
   * Determine the change to be given and return it as a String
   */
  public static String getChange(String input) {
    double purchasePrice, cashGiven;
    
    try { //ensure our input has been given in the correct format
      String[] parseInput = input.split(";"); //split the input into an array for processing
      purchasePrice = Double.parseDouble(parseInput[0]); //process the information accordingly
      cashGiven = Double.parseDouble(parseInput[1]);
    } catch (Exception e) {
      return "INVALID INPUT FORMAT ERROR"; //inform the user that the input is not correctly formatted
    }
    
    
    if (cashGiven < purchasePrice) { //handle case where customer has not given enough cash
      return "ERROR";
    } else if (cashGiven == purchasePrice) { //handle case where exact amount was paid by customer
      return "ZERO";
    } else {
      double leftOver = cashGiven - purchasePrice; //determine the difference i.e. the total change required
      StringBuilder changeNeeded = new StringBuilder(); //instantiate StringBuilder object to store answer
      
      int leftOverChange = (int)(leftOver * 100); //convert into int to avoid floating point errors

      int hundreds = Math.round((int)leftOverChange/10000); //determine the number of hundreds needed (note that we must divide by 10000 since we multiplied by 100 earlier)
      updateChangeNeeded(changeNeeded, "ONE HUNDRED", hundreds); //call our function to append the result to the StringBuilder object
      leftOverChange %= 10000; //update the remaining change needed
      
      //processing continues similarly for other denominations of currency
      int fifties = Math.round((int)leftOverChange/5000);
      updateChangeNeeded(changeNeeded, "FIFTY", fifties);
      leftOverChange %= 5000;
      
      int twenties = Math.round((int)leftOverChange/2000);
      updateChangeNeeded(changeNeeded, "TWENTY", twenties);
      leftOverChange %= 2000;
      
      int tens = Math.round((int)leftOverChange/1000);
      updateChangeNeeded(changeNeeded, "TEN", tens);
      leftOverChange %= 1000;
      
      int fives = Math.round((int)leftOverChange/500);
      updateChangeNeeded(changeNeeded, "FIVE", fives);
      leftOverChange %= 500;
      
      int twos = Math.round((int)leftOverChange/200);
      updateChangeNeeded(changeNeeded, "TWO", twos);
      leftOverChange %= 200;
      
      int ones = Math.round((int)leftOverChange/100);
      updateChangeNeeded(changeNeeded, "ONE", ones);
      leftOverChange %= 100;
      
      int halfs = Math.round((int)leftOverChange/50);
      updateChangeNeeded(changeNeeded, "HALF DOLLAR", halfs);
      leftOverChange %= 50;
      
      int quarters = Math.round((int)leftOverChange/25);
      updateChangeNeeded(changeNeeded, "QUARTER", quarters);
      leftOverChange %= 25;
      
      int dimes = Math.round((int)leftOverChange/10);
      updateChangeNeeded(changeNeeded, "DIME", dimes);
      leftOverChange %= 10;
      
      int nickels = Math.round((int)leftOverChange/5);
      updateChangeNeeded(changeNeeded, "NICKEL", nickels);
      leftOverChange %= 5;
      
      int pennies = Math.round((int)leftOverChange);
      updateChangeNeeded(changeNeeded, "PENNY", pennies);
      
      changeNeeded.setLength(changeNeeded.length() - 1); //remove the last comma to clean up output
      
      return changeNeeded.toString(); //convert StringBuilder to String and return
    }
  }
  
  /**
   * Update the StringBuilder object with the given denomination by appending it for a specified number of times
   * No return type as it modifies the StringBuilder by reference
   **/
  public static void updateChangeNeeded(StringBuilder changeNeeded, String toAppend, int numTimes) {
    for (int i = 0; i < numTimes; i++) { //simple iteration structure for the specified number of times
      changeNeeded.append(toAppend); //append denomination accordingly
      changeNeeded.append(","); //append comma for formatting
    }
  }

  /**
   * Determine payments to be made and return a String containing the information
   **/
  public static String getPayments(String input) {
    double loanAmount, paymentPeriods, interestRate, downPayment; //declare variables to store input
    
    try { //ensure our input has been given in the correct format
      String[] parseInput = input.split("~"); //split the input into an array for processing
      
      loanAmount = Double.parseDouble(parseInput[0]); //process the information accordingly
      paymentPeriods = Double.parseDouble(parseInput[1]);
      interestRate = Double.parseDouble(parseInput[2]);
      downPayment = Double.parseDouble(parseInput[3]);
      
    } catch (Exception e) {
      return "INVALID INPUT FORMAT ERROR"; //inform the user that the input is not correctly formatted
    }
    
    double monthlyRate = interestRate / 1200; //convert the annual interest rate to monthly. Also divide by 100 to convert percentage to decimal
    double repayableAmount = loanAmount - downPayment; //determine the amount that needs to be repaid, accounting for downpayment
    paymentPeriods *= 12; //since we are using monthly rate in the formula, we need payment period in months, not years
    
    double monthlyPayment = (monthlyRate * repayableAmount) / (1 - Math.pow(1 + monthlyRate, -paymentPeriods)); //apply the given formula to determine monthly payments
    int totalInterestPayment = (int) Math.round(monthlyPayment * paymentPeriods - repayableAmount); //calculate the total interest over the loan period
    
    String output = String.format("$%.2f~$%d", monthlyPayment, totalInterestPayment); //format the output as specified in the problem statement
    return output; //return the formatted output
  }
}
