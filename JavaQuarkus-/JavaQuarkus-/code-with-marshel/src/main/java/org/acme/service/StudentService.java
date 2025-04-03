package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Database;
import org.acme.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class StudentService {


    // Method to get all students from the database
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String indexNumber = resultSet.getString("index_number");
                String email = resultSet.getString("email");
                String dept_id = String.valueOf(resultSet.getInt("dept_id"));
                String program_id = String.valueOf(resultSet.getInt("program_id"));
                Student student = new Student(firstName, lastName, email, indexNumber, dept_id, program_id);
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }


    /// / CRUD methods for Student
    public int insertStudent(Student student) {
        int rowsAffected = 0;
        String query = "INSERT INTO student (first_name, last_name, index_number, email, dept_id, program_id) VALUES (?, ?, ?, ?,?,?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getIndexNumber());
            statement.setString(4, student.getEmail());
            statement.setString(5, String.valueOf(student.getDepartment().getDept_id()));
            statement.setString(6, String.valueOf(student.getProgram().getProgram_id()));
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " student record(s) inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
        return rowsAffected;
    }

    public int updateStudentEmail(String newEmail, String indexNumber) {
        String query = "UPDATE student SET email = ? WHERE index_number = ?";
        int rowsAffected = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newEmail);
            statement.setString(2, String.valueOf(Integer.valueOf(indexNumber)));
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " student record(s) updated.");
        } catch (SQLException e) {
            System.err.println("Error updating student email: " + e.getMessage());
        }
        return rowsAffected;
    }

//    public int deleteStudent() {
//        String query = "DELETE FROM student WHERE index_number = ?";
//        int rowsAffected = 0;
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, indexNumber);
//            rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println(rowsAffected + " student record(s) deleted.");
//            } else {
//                System.out.println("No student found with index number: " + indexNumber);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error deleting student: " + e.getMessage());
//        }
//        return rowsAffected;
//    }

    public Student createStudent(Student student) throws SQLException {
        String query = "INSERT INTO student (first_name, last_name, index_number, email, dept_id, program_id) VALUES (?, ?, ?, ?,?,?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getIndexNumber());
            statement.setString(4, student.getEmail());
            statement.setString(5, String.valueOf(student.getDepartment().getDept_id()));
            statement.setString(6, String.valueOf(student.getProgram().getProgram_id()));
            statement.executeUpdate();
            return student;
        }
    }

    public Student updateStudentByIndexNumber(String index_number, Student updatedStudent) throws SQLException {
        String sql = "UPDATE Student SET first_name = ?, last_name = ?, email = ?, WHERE index_number = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedStudent.getFirstName());
            statement.setString(2, updatedStudent.getLastName());
//            statement.setString(3, UpdateStudent.getIndexNumber());
            statement.setString(3, updatedStudent.getEmail());
//            UpdateStudent.setIndexNumber(index_number);
            statement.executeUpdate();
            return updatedStudent;
        }
    }
    public String deleteStudent(String indexNumber) throws SQLException {
        String sql = "DELETE FROM students WHERE index_number = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, indexNumber);
            // Execute the delete query and check how many rows were affected
            statement.executeUpdate();
            return indexNumber;
        }
    }
}



