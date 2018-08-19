package courseregistration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class encapsulates the various methods that an Administrator can use in the System
 * @author sameertulshyan
 * @version 0.1
 */
public class Admin extends User implements AdminInterface, java.io.Serializable {	
	/**
	 * Method deletes a Course from the provided ArrayList, based on the provided coursename and section number
	 */
	@Override
	public void deleteCourse(ArrayList<Course> courses, String courseName, int sectionNumber) {
		for (Course c : courses) { //search through the ArrayList
			if (c.getCourseName().equals(courseName) && c.getSectionNumber() == sectionNumber) { //find the appropriate course
				courses.remove(c); //call the Course Class' remove method on the course
				System.out.println("The course has been successfully removed"); //let the user know that the operation was successful
				return; //avoid wasting time by searching the rest of the arraylist
			}
		}
		System.out.println("The course you selected could not be found. Please check the courseName and sectionNumber and try again");
	}
	
	/**
	 * Method allows Admin to modify certain Course fields based on provided changes
	 */
	@Override
	public void editCourse(Course c, String courseName, String courseID, String instructorName) {
		c.setCourseName(courseName); //update the name 
		c.setCourseID(courseID); //update the course ID
		c.setInstructorName(instructorName); //update the instructor name
	}
	
	/**
	 * Method allows Admin to view all relevant information for a particular course based on courseID
	 */
	@Override
	public void displayInfo(ArrayList<Course> courses, String courseID) {
		for (Course c : courses) { //search the ArrayList of courses
			if (c.getCourseID().equals(courseID)) { //find the appropriate course by course ID (per requirements Course ID is the only criteria)
				System.out.print("Course name: " + c.getCourseName()); //print out relevant information
				System.out.print(", Course ID: " + c.getCourseID());
				System.out.print(", Section Number: " + c.getSectionNumber());
				System.out.print(", Number of students registered: " + c.getNumOfStudents());
				System.out.print(", Current Students: " + c.getCurrentStudents());
				System.out.print(", Maximum number of students: " + c.getMaxStudents() + "\n");
				return; //stop searching the arraylist
			}
		}
		System.out.println("The selected course could not be found. Please check the course ID");
		
	}
	
	/**
	 * Method allows Admin to add a Student to the studentlist without registering them for a particular course)
	 */
	@Override
	public void registerStudents(Student s, ArrayList<Student> students) {
		students.add(s); //add the Student object to our ArrayList of unassigned students
	}
	
	/**
	 * Method allows Admin to view relevant information for all courses
	 */
	@Override
	public void viewCourses(ArrayList<Course> courses) {
		for (Course c : courses) { //iterate through the ArrayList of courses
			System.out.print("Course name: " + c.getCourseName()); //print out relevant information
			System.out.print(", Course ID: " + c.getCourseID());
			System.out.print(", Section Number: " + c.getSectionNumber());
			System.out.print(", Number of students registered: " + c.getNumOfStudents());
			System.out.print(", Maximum number of students: " + c.getMaxStudents() + "\n");
		}
		
	}
	
	/**
	 * Method allows Admin to view all Courses that are full
	 */
	@Override
	public void viewFullCourses(ArrayList<Course> courses) {
		for (Course c : courses) { //search through the ArrayList
			if (c.isFull()) { //determine if the course is full using the getter method for each Course's isFull property
				System.out.println(c); //display the information by calling the Course Class' overridden toString() method
			}
		}
		
	}
	
	/**
	 * Method allows the Admin to write a list of full courses to a text file
	 */
	@Override
	public void writeFullCourses(ArrayList<Course> courses) {
		try { //try to open a PrintWriter object and write to it
			PrintWriter output = new PrintWriter(new FileWriter("src/CoursesFull.txt"));
			for (Course c: courses) { //search through the arraylist
				if (c.isFull()) { //determine if the course is full
					output.println(c); //write the course to the file
				}
			}
			System.out.println("\nFile has been successfully created as 'CoursesFull.txt' in the src folder"); //let the user know the operation was successful
			output.close();
		} catch (IOException ioe) { //catch a potential exception
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Method allows Admin to view a list of Students in a given Course
	 */
	@Override
	public void viewCourseStudents(Course c) {
		for (Student s : c.getCurrentStudents()) { //get the ArrayList of registered students and iterate through it
			System.out.println(s); //display each Student object
		}
	}
	
	/**
	 * Method allows Admin to view the list of registered Courses for a given Student based on firstname and lastname
	 */
	@Override
	public void viewStudentCourses(ArrayList<Student> students, String firstName, String lastName) {
		for (Student s: students) { //search through the ArrayList of students
			if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) { //look for the appropriate student
				s.viewRegisteredCourses(); //display their courses
				return; //stop searching
			}
		}
		
		System.out.println("The selected student could not be found. Please check the firstName and lastName");
	}
	
	/**
	 * Method allows the Admin to sort the list of courses by number of students and display the sorted list
	 */
	@Override
	public void sortCourses(ArrayList<Course> courses) {
		Collections.sort(courses); //use the overridden compareTo method in the Course class to sort
		viewCourses(courses); //display the sorted list to the admin
	}

}
