package courseregistration;

import java.util.ArrayList;
/**
 * Class representing a Course, implements Serializable and Comparable for storing data and sorting respectively
 * @author sameertulshyan
 * @version 0.1
 */
public class Course implements java.io.Serializable, Comparable<Course> {
	private String courseName;
	private String courseID;
	private int maxStudents;
	private int numOfStudents;

	private ArrayList<Student> currentStudents; //list of Students registered in this course
	private String instructorName;
	private int sectionNumber;
	private String courseLocation;
	
	/**
	 * Default constructor instantiates a Course object with a placeholder name
	 */
	public Course() {
		this.setCourseName("Generic Course");
	}
	
	/**
	 * Constructor instantiates a Course object with all relevant fields
	 * @param courseName
	 * @param courseID
	 * @param maxStudents
	 * @param numOfStudents
	 * @param students
	 * @param instructorName
	 * @param sectionNumber
	 * @param courseLocation
	 */
	public Course(String courseName, String courseID, int maxStudents, int numOfStudents,
			String students, String instructorName, int sectionNumber, String courseLocation) {
		this.setCourseName(courseName); //set the fields based on the arguments accordingly
		this.setCourseID(courseID);
		this.setMaxStudents(maxStudents);
		this.setNumOfStudents(numOfStudents);
		this.setInstructorName(instructorName);
		this.setSectionNumber(sectionNumber);
		this.setCourseLocation(courseLocation);
		this.setCurrentStudents(new ArrayList<Student>()); //no argument for Student list, so initialize an empty list and update if applicable
		
		if (!students.equals("NULL")) { //check to see if the String of Student names is "NULL" 
			String[] studentlist = students.split(","); //otherwise process the String into individual names
			for (String s : studentlist) {
				String[] names = s.split(" ");
				this.currentStudents.add(new Student(names[0], names[names.length - 1])); //instantiate them as Student objects and add them to the ArrayList
			}
		}
	}
	
	/**
	 * Method adds a Student object to the list of registered students for this course
	 * @param student reference to Student object
	 */
	public void addStudent(Student student) {
		this.getCurrentStudents().add(student); //add the Student object to the list of registered students
		this.numOfStudents++; //update the number of registered students
	}
	
	/**
	 * Method removes a Student object from the list of registered students for this course
	 * @param student reference to Student object
	 */
	public void removeStudent(Student student) {
		for (Student s: this.currentStudents) { //search through the list of students
			if (s.equals(student)) { //find the appropriate student
				this.currentStudents.remove(s); //remove them
				this.numOfStudents--; //update the number of registered students
				return; //stop searching
			}
		}
		System.out.println("The selected student was not found in this course. Please check the courseName and sectionNumber and try again");
	}
	
	/**
	 * Getter method for the maxStudents property
	 * @return int representing maxStudents
	 */
	public int getMaxStudents() {
		return this.maxStudents;
	}

	/**
	 * Setter method for the maxStudents property
	 * @param maxStudents to be set
	 */
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	/**
	 * Getter method for the numOfStudents property
	 * @return int representing numOfStudents
	 */
	public int getNumOfStudents() {
		return this.numOfStudents;
	}

	/**
	 * Setter method for the numOfStudents property
	 * @param numOfStudents to be set
	 */
	public void setNumOfStudents(int numOfStudents) {
		this.numOfStudents = numOfStudents;
	}
	
	/**
	 * Getter method for the courseName property
	 * @return String representing courseName
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * Setter method for the courseName property
	 * @param courseName to be set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Getter method for the sectionNumber property
	 * @return int representing sectionNumber
	 */
	public int getSectionNumber() {
		return this.sectionNumber;
	}

	/**
	 * Setter method for the sectionNumber property
	 * @param sectionNumber to be set
	 */
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	/**
	 * Getter method for the courseID property
	 * @return String representing courseID
	 */
	public String getCourseID() {
		return this.courseID;
	}

	/**
	 * Setter method for the courseID property
	 * @param courseID to be set
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * Getter method for the instructorName property
	 * @return String representing instructorName
	 */
	public String getInstructorName() {
		return this.instructorName;
	}

	/**
	 * Setter method for the instructorName property
	 * @param instructorName to be set
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * Getter method for the currentStudents property
	 * @return ArrayList of Student objects representing currentStudents
	 */
	public ArrayList<Student> getCurrentStudents() {
		return this.currentStudents;
	}

	/**
	 * Setter method for the currentStudents property
	 * @param currentStudents to be set
	 */
	public void setCurrentStudents(ArrayList<Student> currentStudents) {
		this.currentStudents = currentStudents;
	}

	/**
	 * Getter method for the courseLocation property
	 * @return String representing courseLocation 
	 */
	public String getCourseLocation() {
		return this.courseLocation;
	}

	/**
	 * Setter method for the courseLocation property
	 * @param courseLocation to be set
	 */
	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	/**
	 * Method determines whether this course is full
	 * @return boolean true if full, false if not
	 */
	public boolean isFull() {
		if (this.getNumOfStudents() >= this.getMaxStudents()) { //check the numOfStudents property for this course against the maxStudents property
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method overrides the Object class' toString method to provide a useful description of this Course object
	 */
	public String toString() {
		String course = "" + this.courseName + ", " + this.courseID + ", " + this.maxStudents + ", " + this.numOfStudents + ", " + this.currentStudents + ", "
				+ this.instructorName + ", " + this.sectionNumber + ", " + this.courseLocation;
		return course;
	}
	
	/**
	 * Method compares two Course objects to determine if they are the same
	 * @param c a reference to a Course object
	 * @return true if equal, false if not
	 */
	public boolean equals(Course c) {
		//two courses are equal if they have the same courseName and sectionNumber
		return (this.courseName.equals(c.getCourseName())) && (this.sectionNumber == c.getSectionNumber());
	}
	
	/**
	 * Method overrides and implements the compareTo method from the Comparable Interface to allow the use of Collections.sort on an ArrayList of Course objects
	 */
	public int compareTo(Course c) {
		return c.getNumOfStudents() - this.getNumOfStudents(); //the sort will be implemented from most students to least students
	}

	
}
