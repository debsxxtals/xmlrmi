

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CourseInterface extends Remote {
    public String getCourseID() throws RemoteException;

    public void setCourseID(String courseID) throws RemoteException;

    public String getCourseTitle() throws RemoteException;

    public void setCourseTitle(String courseTitle) throws RemoteException;

    public String getCourseDescription() throws RemoteException;

    public void setCourseDescription(String courseDescription) throws RemoteException;

    public String getStudentID() throws RemoteException;

    public void setStudentID(String studentID) throws RemoteException;
}
