# Course Registration System

This project began as a result of frustrating experiences with NYU's system for registering courses- namely, a slow, buggy interface and issues with changes not being saved. 
I implemented this basic system that allows the user to interact through a CLI and browse, register for and drop courses as needed. There is also an admin account that has additional CRUD functionality for courses. 

The 'database' for this project is a simple ArrayList, where the state of the database is preserved through serialization. There are databases for courses and users, so returning users will have their registered courses preserved.
I have provided a list of courses for the initial run in a .csv file.

## Usage

1.	Ensure the MyUniversityCourses.csv file is placed in the src folder
2.	Run the Program.java file (contains main method)
3.	Enter the username and password as one of the following:

...*	“Admin” and “Admin001”
...*	“Student” and “Student001” (Note that all Students will use this username and password. They will be remembered/differentiated by their first name and last name)

4.	For students, enter your name when the program prompts you
5.	Choose from menu options to use desired functions
6.	Exit the menu when finished by choosing the appropriate exit option. When the last menu is exited, the program will terminate
