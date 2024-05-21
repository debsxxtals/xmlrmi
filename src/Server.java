

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.rmi.RemoteException;
import java.sql.Statement;

public class Server {
    public static void main(String[] args) {
    	String url = "jdbc:postgresql://localhost:5432/rmi";

		String username = "postgres";
		String password = "wadakasikandan";
    
    	
    	
        try {
            // Initialize the RMI registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
            System.out.println("RMI Registry started...");
            

			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected  to Postgresql server");
            
			
			
			// Lookup student objects from the RMI registry
			StudentInterface s1 = (StudentInterface) registry.lookup("221-10001");
			StudentInterface s2 = (StudentInterface) registry.lookup("221-10002");
			StudentInterface s3 = (StudentInterface) registry.lookup("221-10003");
			StudentInterface s4 = (StudentInterface) registry.lookup("221-10004");
			StudentInterface s5 = (StudentInterface) registry.lookup("221-10005");
			

			// Lookup course objects from the RMI registry
			CourseInterface c1 = (CourseInterface) registry.lookup("CSC - 101");
			CourseInterface c2 = (CourseInterface) registry.lookup("ITE - 102");
			CourseInterface c3 = (CourseInterface) registry.lookup("ITE - 101");
			CourseInterface c4 = (CourseInterface) registry.lookup("ITE - 111");
			CourseInterface c5 = (CourseInterface) registry.lookup("IT - 112");

			
			//Insert student data into the database
            insertStudent(connection, s1);
            insertStudent(connection, s2);
            insertStudent(connection, s3);
            insertStudent(connection, s4);
            insertStudent(connection, s5);
            
            

            // Insert course data into the database
            insertCourse(connection, c1);
            insertCourse(connection, c2);
            insertCourse(connection, c3);
            insertCourse(connection, c4);
            insertCourse(connection, c5);
            

            // Close the database connection
            connection.close();

            System.out.println("Data migration and storage completed.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server error: " + e.getMessage());
        }

    }

    // Method to insert student data into the database
    private static void insertStudent(Connection connection, StudentInterface student) throws RemoteException {
        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (studentID, name_, age, address, contactNumber) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getContactNumber());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error inserting student data: " + e.getMessage());
        }
    }

    // Method to insert course data into the database
    private static void insertCourse(Connection connection, CourseInterface course) throws RemoteException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO courses (courseID, studentID, courseTitle, courseDescription) VALUES (?, ?, ?, ?)");
            statement.setString(1, course.getCourseID());
            statement.setString(2, course.getStudentID());
            statement.setString(3, course.getCourseTitle());
            statement.setString(4, course.getCourseDescription());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Error inserting course data: " + e.getMessage());
        }
    }
}
