
/**
 * Class models a Queue of Customers using a LinkedList
 * @author sameertulshyan
 * @version 0.1
 */
public class CustomersQueue {
	private CustomersLinkedList list = new CustomersLinkedList(); //instantiate an object of our custom LinkedList class
	
	/**
	 * Default constructor
	 */
	public CustomersQueue() {
		
	}
	
	/**
	 * Method returns the size of the Queue by calling the list's getSize() method
	 * @return
	 */
	public int size() {
		return list.getSize();
	}
	
	/**
	 * Method checks if the Queue is empty by calling the list's isEmpty() method
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Method adds a Customer to the Queue. Since Queue is a FIFO structure, we call the list's addLast method to add it to the back of the Queue
	 * @param c
	 */
	public void enqueue(Customer c) {
		list.addLast(c);
	}
	
	/**
	 * Method returns a reference to the Customer at the front of the Queue by calling the list's getFirst() method
	 * @return
	 */
	public Customer getFirst() {
		return list.getFirst();
	}
	
	/**
	 * Method returns a reference to the Customer at the back of the Queue by calling the list's getLast() method
	 * @return
	 */
	public Customer getLast() {
		return list.getLast();
	}
	
	/**
	 * Method removes a Customer from the Queue. Since Queue is a FIFO structure, we call the list's removeFirst method to remove it from the front of the Queue
	 * @return a reference to the Customer at the front of the Queue
	 */
	public Customer dequeue() {
		return list.removeFirst();
	}
}
