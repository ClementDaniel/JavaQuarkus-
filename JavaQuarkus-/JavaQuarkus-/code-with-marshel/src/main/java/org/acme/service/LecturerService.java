package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Database;
import org.acme.model.Lecturer;
import org.acme.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


@ApplicationScoped
public class LecturerService {

    // Fetch all lecturers
    public List<Lecturer> getAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String query = "SELECT * FROM lecturer";
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Lecturer lecturer = new Lecturer(firstName, lastName);
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching lecturers: " + e.getMessage());
        }
        return lecturers;
    }

    // CRUD methods for Lecturer
    public int insertlecturer(Lecturer lecturer) {
        String query = "INSERT INTO lecturer (name, course) VALUES (?, ?)";
        int rowsAffected = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, lecturer.getfirstName());
            statement.setString(2, lecturer.getCourse());
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " lecturer inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting lecturer: " + e.getMessage());
        }
        return rowsAffected;
    }


//    public int updatelecturerName(String newName, String lecturerId) {
//        String query = "UPDATE lecturer SET name = ? WHERE lecturer_id = ?";
//        int rowsAffected = 0;
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, newName);
//            statement.setString(2, lecturerId);
//            rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected + " lecturer name updated.");
//        } catch (SQLException e) {
//            System.err.println("Error updating lecturer name: " + e.getMessage());
//        }
//        return rowsAffected;
//    }

//public static int updatelecturerCourse(String newCourse, String lecturerId) {
//    String query = "UPDATE lecturer SET course = ? WHERE lecturer_id = ?";
//    int rowsAffected = 0;
//    try (Connection connection = Database.getConnection();
//         PreparedStatement statement = connection.prepareStatement(query)) {
//
//        statement.setString(1, newCourse);
//        statement.setString(2, lecturerId);
//        rowsAffected = statement.executeUpdate();
//        System.out.println(rowsAffected + " lecturer course updated.");
//    } catch (SQLException e) {
//        System.err.println("Error updating lecturer course: " + e.getMessage());
//    }
//    return rowsAffected;
//}

//    public static int deletelecturer(String lecturerId) {
//        String query = "DELETE FROM lecturer WHERE lecturer_id = ?";
//        int rowsAffected = 0;
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, lecturerId);
//            rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println(rowsAffected + " lecturer(s) deleted.");
//            } else {
//                System.out.println("No lecturer found with ID: " + lecturerId);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error deleting lecturer: " + e.getMessage());
//        }
//        return rowsAffected;
//    }
//}


//    public void main(String[] args) {
//        // Query the student table
//        try (Connection connection = Database.getConnection();
//             Statement statement = connection.createStatement()) {
//
//            System.out.println("Connected to the database.");

    /// /            String query = "select * from student order by dept_id desc limit 1";
//            String query = "select * from student";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Process the result set
//            while (resultSet.next()) {
//                int dept_id = resultSet.getInt("dept_id");
//                String lastName = resultSet.getString("last_name");
//                System.out.println("dept_id: " + dept_id);
//            }
//
//            resultSet.close();
//            System.out.println("Database connection closed.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public Lecturer createLecturer(Lecturer lecturer) throws SQLException {
        String query = "INSERT INTO lecturer (first_name, last_name) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lecturer.getfirstName());
            statement.setString(2, lecturer.getlastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting lecturer: " + e.getMessage());
            throw new SQLException("Failed to create lecturer", e);
        }
        return lecturer;
    }
    public Lecturer updateLecturer(String firstName, String lastName, Lecturer updatedLecturer) throws SQLException {
        // Define the SQL update query
        String sql = "UPDATE Lecturer SET first_name = ?, last_name = ? ";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updatedLecturer.getfirstName());
            statement.setString(2, updatedLecturer.getlastName());
//            statement.setString(3, updatedLecturer.getCourse());
//            stmt.setString(4, email);
            // Execute the update query
            statement.executeUpdate();
            return updatedLecturer;
        }
    }
}







