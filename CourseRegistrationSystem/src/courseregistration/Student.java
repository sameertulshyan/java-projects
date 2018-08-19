package courseregistration;

import java.util.ArrayList;

/**
 * This class encapsulates the various methods that a Student can use in the System
 * @author sameertulshyan
 * @version 0.1
 */
public class Student extends User implements StudentInterface, java.io.Serializable {
	private ArrayList<Course> myCourses = new ArrayList<>(); //ArrayList of Courses this Student is registered in
	
	/**
	 * Default constructor assigns the student a generic firstName and lastName
	 */
	public Student() {
		this("Firstname", "Lastname"); //call the general constructor
	}
	
	/**
	 * General constructor assigns the student a firstName and lastName based on arguments provided
	 * @param firstName to be set
	 * @param lastName to be set
	 */
	public Student(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	/**
	 * Getter method for myCourses property
	 * @return myCourses ArrayList of courses
	 */
	public ArrayList<Course> getMyCourses() {
		return myCourses;
	}

	/**
	 * Setter method for myCourse property
	 * @param myCourses to be set
	 */
	public void setMyCourses(ArrayList<Course> myCourses) {
		this.myCourses = myCourses;
	}

	/**
	 * Method allows Student to view a list of available courses offered by the university
	 */
	@Override
	public void viewCourses(ArrayList<Course> courses) {
		for (Course c : courses) { //iterate through the provided list of courses
			System.out.println(c.getCourseName() + ", Section: " + c.getSectionNumber()); //student only needs limited information, so we don't use the toString method
		}
		
	}

	/**
	 * Method allows Student to view a list of available courses that are not full
	 */
	@Override
	public void viewCoursesWithSpace(ArrayList<Course> courses) {
		for (Course c : courses) { //iterate through the provided list of courses
			if (!c.isFull()) { //call each course's isFull method to look for courses with space
				System.out.println(c.getCourseName() + ", Section: " + c.getSectionNumber()); //display the information for these courses
			}
		}
	}

	/**
	 * Method allows Student to register for a Course, based on a courseName and sectionNumber provided as arguments
	 */
	@Override
	public void register(ArrayList<Course> courses, String courseName, int sectionNumber) {
		for (Course c : courses) { //iterate through the provided list of courses
			if (courseName.equals(c.getCourseName()) && (sectionNumber == c.getSectionNumber()) && (!c.isFull())) { //make sure the course is correct and not full
				c.addStudent(this); //pass in a reference to this student to the Course's addStudent method
				this.myCourses.add(c); //add a reference to the Course to this student's myCourses ArrayList
				System.out.println("\nRegistration Successful!"); //let the student know that the registration was successful
				return; //avoid wasting time by continuing to search
			}
		}
		System.out.println("The course you selected could not be found. Please check the courseName and sectionNumber and try again"); //let the user know registration was unsuccessful
		
	}

	/**
	 * Method allows Student to withdraw from a Course, based on a courseName and sectionNumber provided as arguments
	 */
	@Override
	public void withdraw(ArrayList<Course> courses, String courseName, int sectionNumber) {
		for (Course c : courses) { //iterate through the provided list of courses
			if (courseName.equals(c.getCourseName()) && (sectionNumber == c.getSectionNumber())) { //make sure the course is correct
				c.removeStudent(this); //pass in a reference to this student to the Course's removeStudent method
				this.myCourses.remove(c); //remove a reference to the Course from this student's myCourses ArrayList
				System.out.println("\nWithdrawal Successful!"); //let the student know that the withdrawal was successful
				return; //avoid wasting time by continuing to search
			}
		}
		System.out.println("The course you selected could not be found. Please check the courseName and sectionNumber and try again"); //let the user know withdrawal was unsuccessful
		
	}

	/**
	 * Method allows Student to view a list of courses for which they are registered
	 */
	@Override
	public void viewRegisteredCourses() {
		for (Course c : this.myCourses) { //iterate over this student's list of courses
			System.out.println(c.getCourseName() + ", Section: " + c.getSectionNumber()); //display the relevant information about each course
		}
		
	}
	
	/**
	 * Method compares two Student objects to see if they are the same
	 * @param s a reference to a Student object
	 * @return true if equal, false if not
	 */
	public boolean equals(Student s) {
		//two Student objects are equal if they have the same firstName and lastName
		return (this.getFirstName().equals(s.getFirstName()) && this.getLastName().equals(s.getLastName()));
	}
	
	/**
	 * Method overrides the Object class' toString method to provide a useful description of this Student object
	 */
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

}
