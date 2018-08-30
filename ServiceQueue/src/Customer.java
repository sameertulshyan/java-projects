
/**
 * Class designed to model a Customer at some service store, identified by an ID and arrival time
 * @author sameertulshyan
 * @version 0.1
 */
public class Customer {
	private int ID;
	private int arrivalTime;
	private int waitingTime;
	
	/**
	 * Constructor sets the ID and arrivalTime
	 * @param ID
	 * @param arrivalTime
	 */
	public Customer(int ID, int arrivalTime) {
		this.setID(ID);
		this.setArrivalTime(arrivalTime);
	}
	
	/**
	 * Getter method for the ID property
	 * @return
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * Setter method for the ID property
	 * @param iD
	 */
	public void setID(int iD) {
		this.ID = iD;
	}
	
	/**
	 * Getter method for the arrivalTime property
	 * @return
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}

	/**
	 * Setter method for the arrivalTime property
	 * @param arrivalTime
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Getter method for the waitingTime property
	 * @return
	 */
	public int getWaitingTime() {
		return this.waitingTime;
	}

	/**
	 * Setter method for the waitingTime property
	 * @param waitingTime
	 */
	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}
	
	/**
	 * Method provides a useful String representation of a Customer object.
	 */
	public String toString() {
		return this.getID() + " " + this.getArrivalTime() + " " + this.getWaitingTime(); 
	}
}
