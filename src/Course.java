
import java.rmi.RemoteException;



class Course implements CourseInterface {
    private String courseID;
    private String courseTitle;
    private String courseDescription;
    private String studentID;

    // Constructor
    public Course(String courseID, String studentID, String courseTitle, String courseDescription) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
    }

    // Getter and setter specific to Course
    public String getCourseID() throws RemoteException {
        return courseID;
    }

    public void setCourseID(String courseID) throws RemoteException {
        this.courseID = courseID;
    }

    // Getter and setter specific to Course
    public String getCourseTitle() throws RemoteException {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) throws RemoteException {
        this.courseTitle = courseTitle;
    }

    // Getter and setter specific to Course
    public String getCourseDescription() throws RemoteException {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) throws RemoteException {
        this.courseDescription = courseDescription;
    }

    public String getStudentID() throws RemoteException {
        return studentID;
    }

    public void setStudentID(String studentID) throws RemoteException {
        this.studentID = studentID;
    }
}