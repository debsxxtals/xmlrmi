



import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentInterface extends Remote {
    public String getStudentID() throws RemoteException;

    public void setStudentID(String studentID) throws RemoteException;

    public String getAddress() throws RemoteException;

    public void setAddress(String address) throws RemoteException;

    public String getContactNumber() throws RemoteException;

    public void setContact_number(String contactNumber) throws RemoteException;

    public String getName() throws RemoteException;

    public void setName(String name) throws RemoteException;

    public int getAge() throws RemoteException;

    public void setAge(int age) throws RemoteException;
}
