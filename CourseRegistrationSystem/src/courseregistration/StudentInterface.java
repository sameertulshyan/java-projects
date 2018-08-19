package courseregistration;

import java.util.ArrayList;

/**
 * Contains method signatures that will be implemented by Student Class
 * @author sameertulshyan
 * @version 0.1
 */
public interface StudentInterface {
	public abstract void viewCourses(ArrayList<Course> c);
	public abstract void viewCoursesWithSpace(ArrayList<Course> c);
	public abstract void register(ArrayList<Course> c, String courseName, int sectionNumber);
	public abstract void withdraw(ArrayList<Course> c, String courseName, int sectionNumber);
	public abstract void viewRegisteredCourses();
}
