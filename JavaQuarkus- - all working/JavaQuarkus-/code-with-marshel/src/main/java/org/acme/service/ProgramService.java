package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Database;
import org.acme.model.Program;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


@ApplicationScoped
public class ProgramService {

    // todo fetch program by id
    // Fetch all programs from the database
    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String query = "SELECT * FROM program";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int programId = resultSet.getInt("program_id");
                String programName = resultSet.getString("program_name");
                programs.add(new Program(programId, programName));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching programs: " + e.getMessage());
        }
        return programs;
    }

    // CRUD methods for Program
//    public Integer insertProgram(Program program) {
//        String query = "INSERT INTO program (program_name) VALUES (?)";
//        int rowsAffected = 0;
//        int Program_id = 0;
//
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, program.getProgram_name());
//            rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected + " program inserted.");
//

    /// /        Program_id = getLastId("program","program_id");
//
//        } catch (SQLException e) {
//            System.err.println("Error inserting program: " + e.getMessage());
//        }
//
//        return Program_id;
//    }

//    public int updateProgram_Name(String program_name, String program_id) {
//        String query = "UPDATE program SET name = ? WHERE program_id = ?";
//        int rowsAffected = 0;
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, program_name);
//            statement.setString(2, program_id);
//            rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected + " program(s) updated.");
//        } catch (SQLException e) {
//            System.err.println("Error updating program: " + e.getMessage());
//        }
//        return rowsAffected;
//    }
//    public static int deleteProgram(String program_id) {
//        String query = "DELETE FROM program WHERE program_id = ?";
//        int rowsAffected = 0;
//        try (Connection connection = Database.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, program_id);
//            rowsAffected = statement.executeUpdate();
//            System.out.println(rowsAffected + " program(s) deleted.");
//        } catch (SQLException e) {
//            System.err.println("Error deleting program: " + e.getMessage());
//        }
//        return rowsAffected;
//    }
    public Program createProgram(Program program) throws SQLException {
        // SQL query to insert a new program
        String query = "INSERT INTO program (program_id, program_name) VALUES (?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            statement.setInt(1, program.getProgram_id());
            statement.setString(2, program.getProgram_name());

            // Execute the update to insert the new program
            statement.executeUpdate();
        }

        // Return the program object after insertion
        return program;
    }

    public Program updateProgram(String program_name, Program updatedProgram) throws SQLException {
        // Define the SQL update query
        String sql = "UPDATE Program SET program_name = ? WHERE program_id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the parameters for the query
            statement.setString(1, updatedProgram.getProgram_name());
            statement.setInt(2, updatedProgram.getProgram_id());
            statement.executeUpdate();
            return updatedProgram;
        }
    }

    public int deleteProgramId(int programId) throws SQLException {
        String sql = "DELETE FROM program WHERE program_id = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, programId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted;
        } catch (SQLException e) {
            throw new SQLException("Error while deleting program: " + e.getMessage(), e);
        }
    }
}


