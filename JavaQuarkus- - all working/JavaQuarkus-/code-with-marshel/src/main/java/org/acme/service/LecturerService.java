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
                String lecturerId = resultSet.getString("lecturer_id");
                Lecturer lecturer = new Lecturer(firstName, lastName, lecturerId);
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturers;
    }

    public Lecturer createLecturer(Lecturer lecturer) throws SQLException {
        String query = "INSERT INTO lecturer (first_name, last_name) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lecturer.getFirstName());
            statement.setString(2, lecturer.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting lecturer: " + e.getMessage());
            throw new SQLException("Failed to create lecturer", e);
        }
        return lecturer;
    }

    public Lecturer updateLecturer(Lecturer updatedLecturer) throws SQLException {
        // Define the SQL update query
        String sql = "UPDATE Lecturer SET first_name = ?, last_name = ? ";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updatedLecturer.getFirstName());
            statement.setString(2, updatedLecturer.getLastName());
//            statement.setString(3, updatedLecturer.getCourse());
//            stmt.setString(4, email);
            // Execute the update query
            statement.executeUpdate();
            return updatedLecturer;
        }
    }

    public String deleteLecturer() throws SQLException {
        return deleteLecturerId(null);
    }
    public String deleteLecturerId(String lecturerId) throws SQLException {
        String sql = "DELETE FROM Lecturer WHERE lecturer_id = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, lecturerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error while deleting lecturer: " + e.getMessage(), e);
        }
        return lecturerId;
    }
}













