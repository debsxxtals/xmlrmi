package client;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.w3c.dom.*;

import server.CourseInterface;
import server.StudentInterface;

import javax.xml.parsers.*;
import java.io.*;
public class Client {
    public static void main(String[] args) {
        try {
            // Load and parse the XML file
            File file1 = new File("students.xml");
            File file2 = new File("courses.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc1 = dBuilder.parse(file1);
            Document doc2 = dBuilder.parse(file2);

            // Get a list of product elements
            NodeList studentList = doc1.getElementsByTagName("student");
            // Get a list of product elements
            NodeList courseList = doc2.getElementsByTagName("course");

            // Initialize the RMI registry
            Registry registry = LocateRegistry.createRegistry(9100);
            System.out.println("RMI Registry started...");

            // Iterate over each product element
            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);

                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) studentNode;

                    // Extract product details from XML
                    String studentID = studentElement.getElementsByTagName("studentID").item(0).getTextContent();
                    String name = studentElement.getElementsByTagName("name").item(0).getTextContent();
                    int age = Integer
                            .parseInt(studentElement.getElementsByTagName("age").item(0).getTextContent());

                    String address = studentElement.getElementsByTagName("address").item(0).getTextContent();
                    String contactNumber = studentElement.getElementsByTagName("contactNumber").item(0)
                            .getTextContent();

                    // Create Product object
                    Student student = new Student(studentID, name, age, address, contactNumber);

                    // Export Product object as remote object
                    StudentInterface stub = (StudentInterface) UnicastRemoteObject.exportObject(student, 0);

                    // Bind remote object to the registry
                    registry.rebind(studentID, stub);
                    System.out.println("Student " + studentID + " bound to registry.");
                }
            }

            // Iterate over each product element
            for (int i = 0; i < courseList.getLength(); i++) {
                Node courseNode = courseList.item(i);

                if (courseNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element courseElement = (Element) courseNode;

                    // Extract product details from XML
                    String courseID = courseElement.getElementsByTagName("courseID").item(0).getTextContent();
                    String studentID = courseElement.getElementsByTagName("studentID").item(0).getTextContent();
                    String courseTitle = courseElement.getElementsByTagName("courseTitle").item(0).getTextContent();
                    String courseDescription = courseElement.getElementsByTagName("courseDescription").item(0)
                            .getTextContent();

                    // Create Product object
                    Course course = new Course(courseID, studentID, courseTitle, courseDescription);

                    // Export Product object as remote object
                    CourseInterface stub = (CourseInterface) UnicastRemoteObject.exportObject(course, 0);

                    // Bind remote object to the registry
                    registry.rebind(studentID, stub);
                    System.out.println("Course " + courseID + " bound to registry.");
                }
            }

            // // Create and export Cart object
            // Cart cart = new Cart(10);
            // CourseInterface cartStub = (CourseInterface)
            // UnicastRemoteObject.exportObject(cart, 0);
            // registry.rebind("function_cart", cartStub);

            System.out.println("All objects have been exported and bound.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
