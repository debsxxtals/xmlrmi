package client;

import java.rmi.RemoteException;

import server.StudentInterface;

public class Student extends Person implements StudentInterface {
    private String studentID;
    private String address;
    private String contactNumber;

    // Constructor
    public Student(String studentID, String name, int age, String address, String contactNumber) {
        super(name, age); // Call superclass constructor
        this.studentID = studentID;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    // Getter and setter specific to Student
    public String getStudentID() throws RemoteException {
        return studentID;
    }

    public void setStudentID(String studentID) throws RemoteException {
        this.studentID = studentID;
    }

    public String getAddress() throws RemoteException {
        return address;
    }

    public void setAddress(String address) throws RemoteException {
        this.address = address;
    }

    public String getContactNumber() throws RemoteException {
        return contactNumber;
    }

    public void setContact_number(String contactNumber) throws RemoteException {
        this.contactNumber = contactNumber;
    }
}
