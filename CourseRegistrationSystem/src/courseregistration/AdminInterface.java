package courseregistration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains method signatures that will be implemented by Admin Class
 * @author sameertulshyan
 * @version 0.1
 */
public interface AdminInterface {
	public abstract void deleteCourse(ArrayList<Course> courses, String courseName, int sectionNumber);
	public abstract void editCourse(Course c, String courseName, String courseID, String instructorName);
	public abstract void displayInfo(ArrayList<Course> courses, String courseID);
	public abstract void registerStudents(Student s, ArrayList<Student> students);
	public abstract void viewCourses(ArrayList<Course> courses);
	public abstract void viewFullCourses(ArrayList<Course> courses);
	public abstract void writeFullCourses(ArrayList<Course> courses) throws IOException;
	public abstract void viewCourseStudents(Course c);
	public abstract void viewStudentCourses(ArrayList<Student> students, String firstName, String lastName);
	public abstract void sortCourses(ArrayList<Course> courses);
}
