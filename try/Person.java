package client;

import java.rmi.RemoteException;

public class Person {

    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getName() throws RemoteException {
        return name;
    }

    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    public int getAge() throws RemoteException {
        return age;
    }

    public void setAge(int age) throws RemoteException {
        this.age = age;
    }
}