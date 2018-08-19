package courseregistration;
/**
 * Main class in which implementation takes place
 * @author sameertulshyan
 * @version 0.1
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Program {
	public static Scanner keyboard = new Scanner(System.in); //instantiate a Scanner object to get user input across various methods
	
	public static void main(String[] args) {
		ArrayList<Course> courses; //ArrayList of courses offered by this university, which will be serialized at the end of every session
		ArrayList<Student> students = streamStudents(); //ArrayList of students registered, which will be serialized at the end of every session
		
		try { //look for a serialized version of the courses ArrayList
			System.out.println("Getting courselist...\n");
			courses = streamCourses();
		} catch (Exception e) { //or use the original csv file to get course information instead
			System.out.println("Using csv file to populate courselist...\n");
			courses = populateCourses();
		}
		
		
		User u1 = login(students); //call the login method to get a Student or Admin object and store it as a User
		
		if (u1 instanceof Admin) { //check if the User object is an Admin
			Admin a1 = (Admin)u1; //cast it into an Admin object
			adminMenu(a1, courses, students); //call the method to display the first menu
		} else { //if not Admin, the User must be a student
			Student s1 = (Student)u1; //cast it into a Student object
			studentMenu(s1, courses); //call the method to display the Student menu
		}
		
		serializeCourses(courses); //call the method to serialize the ArrayList of courses to save the updated state
		serializeStudents(students); //call the method to serialize the ArrayList of students to save the updated state
		
	}
	
	/**
	 * Method attempts to get the list of Courses from a serialized file, which should have the most updated information
	 * @return ArrayList of Course objects that has been deserialized
	 * @throws FileNotFoundException if the .ser file is not present, return control back to main method
	 */
	public static ArrayList<Course> streamCourses() throws FileNotFoundException { 
		ArrayList<Course> courses = new ArrayList<Course>(); //instantiate a new ArrayList of courses
		try {
			FileInputStream fis = new FileInputStream("src/MyUniversityCourses.ser"); //open the .ser file
			ObjectInputStream ois = new ObjectInputStream(fis); //pass in the fis reference to stream the file as an object
			courses = (ArrayList<Course>) ois.readObject(); //cast this object into an ArrayList of Course objects and update the courses reference
			ois.close(); //close the input stream objects
			fis.close();
		} catch (FileNotFoundException fnfe) { //if the .ser file cannot be found
			throw new FileNotFoundException(); //throw the FNFE to return control to the main method
		} catch (IOException ioe) { //look for other possible exceptions
			ioe.printStackTrace(); //these represent critical errors that will affect the program's function
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return courses; //return the ArrayList of courses
		
	}
	
	/**
	 * Method attempts to get the list of Students from a serialized file, which should have the most updated information
	 * @return ArrayList of Student objects that has been deserialized, or empty ArrayList of Student type
	 */
	public static ArrayList<Student> streamStudents() {
		ArrayList<Student> students = new ArrayList<Student>(); //instantiate a new ArrayList of courses
		System.out.println("Getting studentlist...\n");
		try {
			FileInputStream fis = new FileInputStream("src/MyUniversityStudents.ser"); //open the .ser file
			ObjectInputStream ois = new ObjectInputStream(fis); //pass in the fis reference to stream the file as an object
			students = (ArrayList<Student>) ois.readObject(); //cast this object into an ArrayList of Student objects and update the students reference
			ois.close(); //close the input stream objects
			fis.close();
		} catch (FileNotFoundException fnfe) { //if the serialized file could not be found
			System.out.println("The list of Students could not be found. Creating a new, empty list...\n"); //let the user know
		} catch (IOException ioe) { //look for other possible exceptions
			ioe.printStackTrace(); //these represent critical errors that will affect the program's function
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return students; //return either the updated ArrayList of Students, or a new empty list
	}
	
	/**
	 * Method attempts to get the list of Courses from the csv file, which will not contain updated information
	 * @return ArrayList of Course objects that has been populated from the csv file
	 */
	public static ArrayList<Course> populateCourses() {
		ArrayList<Course> courses = new ArrayList<Course>(); //instantiate a new ArrayList of courses
		try { //try to open the csv file and get the data
			Scanner input = new Scanner(new File("src/MyUniversityCourses.csv")); //instantiate a Scanner object to read from the file
			input.nextLine(); //skip the first line as it contains the headings
			
			while (input.hasNextLine()) { //loop runs until all the data has been captured
				String[] data = input.nextLine().split(","); //split each line into a String array by commas
				courses.add(new Course(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4], data[5], 
						Integer.parseInt(data[6]), data[7])); //use the elements of the String array to create a new Course object and add it to the list
			}
			
			input.close(); //close the Scanner
			
			
		} catch (FileNotFoundException fnfe) { //if the csv file cannot be found
			System.out.println("Critical error: could not find file for courses. Please place the 'MyUniversityCourses.csv' file in the src folder and restart"
					+ " the program."); //let the user know that this will not allow them to access the default courses
			System.exit(1); //exit the program
		}
		
		return courses; //return the ArrayList of courses
	}
	
	/**
	 * Method to serialize the courses ArrayList into an ser file for faster data retrieval upon subsequent usage
	 */
	public static void serializeCourses(ArrayList<Course> courses) {
		try { //try to write the update course list into a serialized file
			FileOutputStream fos = new FileOutputStream("src/MyUniversityCourses.ser"); //create a new .ser file
			ObjectOutputStream oos = new ObjectOutputStream(fos); //open the Object Output Stream with fos as a reference
			
			oos.writeObject(courses); //write the ArrayList of courses into an Object, which will be written into the .ser file
			
			oos.close(); //close the output streams
			fos.close();
			System.out.println("\nThe updated courselist has been serialized."); //let the user know the serialization was successful
		} catch (IOException ioe) { //possible exception when attempting to serialize
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Method to serialize the students ArrayList into an ser file to save the object state for subsequent usage
	 * @param students ArrayList of students
	 */
	public static void serializeStudents(ArrayList<Student> students) {
		try { //try to write the update student list into a serialized file
			FileOutputStream fos = new FileOutputStream("src/MyUniversityStudents.ser"); //create a new .ser file
			ObjectOutputStream oos = new ObjectOutputStream(fos); //open the Object Output Stream with fos as a reference
			
			oos.writeObject(students); //write the ArrayList of students into an Object, which will be written into the .ser file
			
			oos.close(); //close the output streams
			fos.close();
			System.out.println("\nThe updated student list has been serialized."); //let the user know the serialization was successful
		} catch (IOException ioe) { //possible exception when attempting to serialize
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Method gets information from user and returns either a Student or Admin object, casted as a User object
	 * @param courses 
	 * @return User object
	 */
	public static User login(ArrayList<Student> students) {
		System.out.print("Please enter your username: "); //prompt the user for input
		String username = keyboard.nextLine(); //get the input
		System.out.print("Please enter your password: ");
		String password = keyboard.nextLine();
		boolean validLogin = false; //flag to indicate whether the username and password should be accepted
		
		while (!validLogin) {
			if (username.equals("Admin") && password.equals("Admin001")) { //if the correct information for an Admin is entered
				System.out.println("\nWelcome, administrator\n"); //display welcome message
				validLogin = true; //update the flag
				
			} else if (username.equals("Student") && password.equals("Student001")) { //if the correct information for a Student is entered
				System.out.println("\nWelcome, Student\n"); //display welcome message
				validLogin = true; //update the flag
				
			} else { //login information is incorrect
				System.out.println("Please enter a valid username and password. New students may enter 'Student' as their username "
						+ "and 'Student001' as their password"); //for ease of use, reveal the login information for a student
				System.out.print("Please enter your username: ");
				username = keyboard.nextLine(); //get the input and check again
				System.out.print("Please enter your password: ");
				password = keyboard.nextLine();
			}
			
		}
		
		if (username.equals("Admin")) { //check to see if the user is an Admin
			return new Admin(); //instantiate a new Admin object and return it
		} else { //otherwise the user must be a Student
			System.out.print("Please enter your first name: "); //prompt the student for more information
			String firstName = keyboard.nextLine(); //get the input
			System.out.print("Please enter your last name: ");
			String lastName = keyboard.nextLine(); 
			
			for (Student s : students) { //search through the ArrayList of students
				if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) { //see if our student already exists
						System.out.println("\nWelcome back, " + firstName + " " + lastName);
						return s; //return the reference to this student
				}
			}
			
			Student newStudent = new Student(firstName, lastName); //if the student cannot be found, instantiate a new student object
			students.add(newStudent); //add the new Student to the students list
			return newStudent;
		}
	}
	
	/**
	 * Method displays the first Admin menu to the user, with options to call other menus or exit the program
	 * @param a1 reference to Admin object
	 * @param courses reference to ArrayList of courses
	 * @param students reference to ArrayList of students
	 */
	public static void adminMenu(Admin a1, ArrayList<Course> courses, ArrayList<Student> students) {
		System.out.println("\nWelcome to the Admin Menu. Please choose from the following options: "); //display options
		System.out.println("1: Course Management");
		System.out.println("2: Reports");
		System.out.println("3: Exit");
		System.out.print("Please enter the number of your option: "); //prompt user for input
		int option = Integer.parseInt(keyboard.nextLine()); //get the input
		
		//keep asking the user until they select exit
		while (option != 3) {
			if (option == 1) {
				courseManagement(a1, courses, students); //call the function to display the course management menu
			} else if (option == 2) { 
				reports(a1, courses, students); //call the function to display the reports menu
			} else {
				System.out.println("That is not a valid option"); //input validation
			}
			
			System.out.println("\n1: Course Management"); //display the options again for convenience
			System.out.println("2: Reports");
			System.out.println("3: Exit");
			System.out.print("Please enter another option: ");
			option = Integer.parseInt(keyboard.nextLine());
		}
		
		System.out.println("Exiting the Admin Menu and program now"); //let the user know that the program will exit
	}
	
	/**
	 * Method displays the course management menu for an Admin user, with various options that call the required functions as necessary
	 * @param a1 reference to Admin object
	 * @param courses reference to ArrayList of courses
	 * @param students reference to ArrayList of students
	 */
	public static void courseManagement(Admin a1, ArrayList<Course> courses, ArrayList<Student> students) {
		System.out.println("\nWelcome to the Course Management Menu. Please choose from the following options: "); //display options
		System.out.println("1: Create a new course");
		System.out.println("2: Delete a course");
		System.out.println("3: Edit a course");
		System.out.println("4: Display information for a given course");
		System.out.println("5: Register a student");
		System.out.println("6: Exit");
		System.out.print("Please enter the number of your option: "); //prompt the user for input
		int option = Integer.parseInt(keyboard.nextLine()); //get input
		
		//keep asking the user until they select exit
		while (option != 6) {
			if (option == 1) { //creates a new Course
				System.out.print("Please enter a course name: "); //prompt the user for course details
				String courseName = keyboard.nextLine(); //get input
				System.out.print("Please enter a courseID: ");
				String courseID = keyboard.nextLine();
				System.out.print("Please enter the maximum number of students: ");
				int maxStudents = Integer.parseInt(keyboard.nextLine());
				System.out.print("Please enter the current number of students registered: ");
				int numOfStudents = Integer.parseInt(keyboard.nextLine());
				
				String studentNames; 
				if (numOfStudents > 0) { //this is only applicable if there are students currently registered
					System.out.print("Please enter the names of these students, separated by a comma e.g. (firstname1 lastname1,firstname2 lastname2): ");
					studentNames = keyboard.nextLine();
				} else { //otherwise set the variable to "NULL" per convention used in the csv file
					studentNames = "NULL";
				}
				
				System.out.print("Please enter the name of the instructor: ");
				String instructorName = keyboard.nextLine();
				System.out.print("Please enter the section number: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				System.out.print("Please enter the course location: ");
				String courseLocation = keyboard.nextLine();
				
				//instantiate a new Course object based on the input data
				Course newCourse = new Course(courseName, courseID, maxStudents, numOfStudents, 
						studentNames, instructorName, sectionNumber, courseLocation); //constructor call
				courses.add(newCourse); //add the newly instantiated course to the ArrayList of courses
				
				if (!studentNames.equals("NULL")) { 
					String[] studentArray = studentNames.split(","); //need to register these Students
					for (String s: studentArray) {
						String[] thisStudent = s.split(" "); //get their firstname and lastname
						Student newStudent = new Student(thisStudent[0], thisStudent[1]); //instantiate a new Student object
						newStudent.getMyCourses().add(newCourse); //add this new Course to its list of registered courses
						students.add(newStudent); //add it to the list of registered students in the school
					}
				}
				
			} else if (option == 2) { //deletes a course
				System.out.println("Please enter the name of the course you want to delete: "); //prompt user for input
				String courseName = keyboard.nextLine(); //get input
				System.out.println("Please enter the section number: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				a1.deleteCourse(courses, courseName, sectionNumber); //call the Admin's deleteCourse method
			} else if (option == 3) { //edits a course
				System.out.println("Please enter the current name of the course you want to edit: "); //prompt the user for course to be modified
				String currentName = keyboard.nextLine(); //get input
				System.out.println("Please enter the section number: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				Course c = new Course(); //instantiate a course object
				for (Course course : courses) { //iterate over list of courses
					if (course.getCourseName().equals(currentName) && course.getSectionNumber() == sectionNumber) { //look for the course that should be modified
						c = course; //update c's reference to the course that needs to be modified
						break; //stop searching
					}
				}
				
				System.out.print("Please enter the new name for this course: "); //prompt the user for modification details
				String courseName = keyboard.nextLine();
				System.out.print("Please enter the new courseID: ");
				String courseID = keyboard.nextLine();
				System.out.print("Please enter the new instructor's name: ");
				String instructorName = keyboard.nextLine();
				a1.editCourse(c, courseName, courseID, instructorName); //call the Admin's editCourse method
			} else if (option == 4) { //displays detailed information for a particular course
				System.out.print("Please enter the courseID of the course you want to display: "); //prompt the user for courseID
				String courseID = keyboard.nextLine(); //get input
				a1.displayInfo(courses, courseID); //call the Admin's displayInfo method
			} else if (option == 5) { //registers a student without assigning them a course
				System.out.print("Please enter the first name of the student you want to register: "); //prompt user for student details
				String firstName = keyboard.nextLine();
				System.out.print("Please enter the last name of the student you want to register: ");
				String lastName = keyboard.nextLine();
				Student newStudent = new Student(firstName, lastName); //instantiate a new Student object
				a1.registerStudents(newStudent, students); //call the Admin's registerStudents method
			} else { //input validation
				System.out.println("That is not a valid option.");
			}
			
			System.out.println("\n1: Create a new course"); //display options again for convenience
			System.out.println("2: Delete a course");
			System.out.println("3: Edit a course");
			System.out.println("4: Display information for a given course");
			System.out.println("5: Register a student");
			System.out.println("6: Exit");
			System.out.print("Please enter another option: ");
			option = Integer.parseInt(keyboard.nextLine());
		}
		
		System.out.println("Exiting the Course Management Menu now\n"); //let the user know that they have exited this menu
	}
	
	/**
	 * Method displays the reports menu for an Admin user, with various options that call the required functions as necessary
	 * @param a1 reference to Admin object
	 * @param courses reference to ArrayList of courses
	 * @param students reference to ArrayList of students
	 */
	public static void reports(Admin a1, ArrayList<Course> courses, ArrayList<Student> students) {
		System.out.println("\nWelcome to the Reports Menu. Please choose from the following options: "); //display options
		System.out.println("1: View all courses");
		System.out.println("2: View all courses that are full");
		System.out.println("3: Write a file containing a list of full courses");
		System.out.println("4: Display the names of students in a particular course");
		System.out.println("5: Display the courses for a particular student");
		System.out.println("6: Sort the list of courses by number of students currently registered");
		System.out.println("7: Exit");
		System.out.print("Please enter the number of your option: "); //prompt user for input
		int option = Integer.parseInt(keyboard.nextLine()); //get input
		
		//keep asking the user until they select exit
		while (option != 7) {
			if (option == 1) { //displays all courses
				a1.viewCourses(courses); //call the Admin's viewCourses method
			} else if (option == 2) { //displays courses that are full
				a1.viewFullCourses(courses); //call the Admin's viewFullCourses method
			} else if (option == 3) { //writes a list of full courses to a text file
				a1.writeFullCourses(courses); //call the Admin's writeFullCourses method
			} else if (option == 4) { //displays a list of students in a given course
				System.out.println("Please enter the name of the course you want to view the roster for: "); //prompt the user for course details
				String currentName = keyboard.nextLine();
				System.out.println("Please enter the section number: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				Course c = new Course(); //instantiate a new Course object
				for (Course course : courses) {
					if (course.getCourseName().equals(currentName) && course.getSectionNumber() == sectionNumber) { //look for the desired course
						c = course; //update c's reference to this course
						break; //stop searching
					}
				}
				a1.viewCourseStudents(c); //call the Admin's viewCourseStudents method
			} else if (option == 5) { //displays a list of courses for a given student
				System.out.print("Please enter the first name of the student: "); //prompt the user for student details
				String firstName = keyboard.nextLine();
				System.out.print("Please enter the last name of the student: ");
				String lastName = keyboard.nextLine();
				a1.viewStudentCourses(students, firstName, lastName); //call the Admin's viewStudentCourses method
			} else if (option == 6) { //sorts and displays the list of courses by number of students (most to least)
				System.out.println("Sorting the list by number of students...\n");
				a1.sortCourses(courses);
			} else { //input validation
				System.out.println("That is not a valid option");
			}
			
			System.out.println("\n1: View all courses"); //display the menu again for convenience
			System.out.println("2: View all courses that are full");
			System.out.println("3: Write a file containing a list of full courses");
			System.out.println("4: Display the names of students in a particular course");
			System.out.println("5: Display the courses for a particular student");
			System.out.println("6: Sort the list of courses by number of students currently registered");
			System.out.println("7: Exit");
			System.out.print("Please enter another option: ");
			option = Integer.parseInt(keyboard.nextLine());
		}
		
		System.out.println("Exiting the Reports Menu now\n"); //let the user know that they have exited this menu
	}
	
	/**
	 * Method displays the reports menu for a Student user, with various options that call the required functions as necessary
	 * @param s1 reference to Student object
	 * @param courses reference to ArrayList of courses
	 */
	public static void studentMenu(Student s1, ArrayList<Course> courses) {
		System.out.println("\nWelcome to the Student Menu. Please choose from the following options: "); //display options
		System.out.println("1: View available courses");
		System.out.println("2: View courses that are not full");
		System.out.println("3: Register for a course");
		System.out.println("4: Withdraw from a course");
		System.out.println("5: View the courses you are currently registered in");
		System.out.println("6: Exit");
		
		System.out.print("Please enter the number of your option: "); //prompt user for input
		int option = Integer.parseInt(keyboard.nextLine()); //get input
		
		//keep asking the user until they select exit
		while (option != 6) { 
			if (option == 1) { //views all courses offered
				s1.viewCourses(courses); //call the Student's viewCourses method
			} else if (option == 2) { //views all courses with space
				s1.viewCoursesWithSpace(courses); //call the Student's viewCoursesWithSpace method
			} else if (option == 3) { //registers the student for a given course
				System.out.print("Enter the name of the course you want to register for: "); //prompt the user for course details
				String courseName = keyboard.nextLine();
				System.out.print("Enter the course section: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				s1.register(courses, courseName, sectionNumber); //call the Student's register method
			} else if (option == 4) { //withdraws the student from a given course
				System.out.print("Enter the name of the course you want to withdraw from: "); //prompt the user for course details
				String courseName = keyboard.nextLine();
				System.out.print("Enter the course section: ");
				int sectionNumber = Integer.parseInt(keyboard.nextLine());
				s1.withdraw(courses, courseName, sectionNumber); //call the Student's withdraw method
			} else if (option == 5) { //displays the list of courses in which the student is registered
				s1.viewRegisteredCourses(); //call the Student's viewRegisteredCourses method
			} else { //input validation
				System.out.println("That is not a valid option");
			}
			
			System.out.println("\n1: View available courses"); //display the options again for convenience
			System.out.println("2: View courses that are not full");
			System.out.println("3: Register for a course");
			System.out.println("4: Withdraw from a course");
			System.out.println("5: View the courses you are currently registered in");
			System.out.println("6: Exit");
			System.out.print("Please enter another option: ");
			option = Integer.parseInt(keyboard.nextLine());
		}
		System.out.println("Exiting the Student Menu and Program now\n"); //let the user know that the program will exit
		
	}
}
