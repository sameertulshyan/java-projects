
/**
 * This class implements a doubly LinkedList of Nodes containing Customer objects
 * @author sameertulshyan
 *
 */
public class CustomersLinkedList {
	/**
	 * Nested Node class that stores a Customer object as its data
	 * @author sameertulshyan
	 * @version 0.1
	 */
	private static class Node {
		private Customer customer;
		private Node next;
		
		/**
		 * Constructor sets the Customer and next Node references
		 * @param c
		 * @param n
		 */
		public Node(Customer c, Node n) {
			this.customer = c;
			this.next = n;
		}
		
		public Customer getCustomer() {
			return this.customer;
		}
		
		public Node getNext() {
			return this.next;
		}
		
		public void setNext(Node n) {
			this.next = n;
		}
	}
	
	private Node head; //reference to the front of the list
	private Node tail; //reference to the rear
	private int size = 0; //size of the list
	
	/**
	 * Default list constructor
	 */
	public CustomersLinkedList() {
		
	}
	
	/**
	 * Getter method for the size of the list
	 * @return
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Checks to see if the list is empty
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * Method returns the Customer stored at the head Node, or null if the list is empty
	 * @return
	 */
	public Customer getFirst() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.head.getCustomer();
		}
	}
	
	/**
	 * Method returns the Customer stored at the tail Node, or null if the list is empty
	 * @return
	 */
	public Customer getLast() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.tail.getCustomer();
		}
	}
	
	/**
	 * Method adds a Customer to the back of the list
	 * @param c
	 */
	public void addLast(Customer c) {
		Node newest = new Node(c, null); //instantiate a new Node to store the Customer object's reference
		if (this.isEmpty()) { //if the list is empty
			this.head = newest; //this Node will become both head and tail
		} else { //if the list is not empty
			this.tail.setNext(newest); //set the current tail's next reference to our new Node
		}
		this.tail = newest; //update the tail reference to point to our new Node
		this.size++; //increment the list size
	}
	
	/**
	 * Method removes the Customer stored at the head of the list and returns it
	 * @return
	 */
	public Customer removeFirst() {
		if (this.isEmpty()) { //if the list is empty
			return null; //return null
		}
		
		Customer c = this.head.getCustomer(); //else get the Customer stored in the head Node and store its reference in another variable
		this.head = this.head.getNext(); //update the head reference to the head's next Node
		this.size--; //decrement the list size
		if (this.size == 0) { //if the list is now Empty
			this.tail = null; //set the tail reference to null
		}
		return c; //return the Customer from the Node we just removed
	}
	
}
