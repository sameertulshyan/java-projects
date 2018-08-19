package courseregistration;

/**
 * Parent class for users of the course registration system. Will act as abstract superclass for Admin and Student.
 * @author sameertulshyan
 * @version 0.1
 */
public abstract class User implements java.io.Serializable {
	private String username; //all users will share these data fields
	private String password;
	private String firstName;
	private String lastName;
	
	/**
	 * Getter method for username property
	 * @return String representing the username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Setter method for username property
	 * @param username to be set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Getter method for password property
	 * @return String representing password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Setter method for password property
	 * @param password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Getter method for firstname property
	 * @return String representing firstname
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Setter method for firstname property
	 * @param firstName to be set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Getter method for lastname property
	 * @return String representing lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Setter method for lastname property
	 * @param lastName to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
