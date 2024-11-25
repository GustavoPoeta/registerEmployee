package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Employee {
    String name;
    String role;
    String dept;
    int salary; 


    protected static Connection connectDB () {
        Connection conn = null;
        int connTries = 0;

        while (conn == null && connTries < 5)  {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/registeremp", "root", "1234");
                if (conn != null) {
                    System.out.println("connection stablished");
                }
            } catch (SQLException e) {
                connTries++;
                conn = null;   
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    ie.printStackTrace();
                }
            }
        }

        if (connTries == 5 && conn == null) {
            System.out.println("Error connecting to the database after 5 attempts.");
        }

        return conn;

    }

    static void registerEmp (Employee newEmployee) {

        Connection conn = connectDB();

        if (conn == null) {
            return;
        }

        String insertQuery = "insert into employee (name, role, dept, salary) values (?, ?, ?, ?)";

        try (PreparedStatement prepareStatement = conn.prepareStatement(insertQuery)) {
            prepareStatement.setString(1, newEmployee.name);
            prepareStatement.setString(2, newEmployee.role);
            prepareStatement.setString(3, newEmployee.dept);
            prepareStatement.setInt(4, newEmployee.salary);

            int rowsAffected = prepareStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee registered.");
            }
        } catch (SQLException e ) {
            System.err.println("Server wasn't able to make the registration.");
        }
    }
}
