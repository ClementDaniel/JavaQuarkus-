package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Database;
import org.acme.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

@ApplicationScoped
public class DepartmentService {


    // todo fetch department by id
    // Fetch all departments from the database
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM department";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String deptId = String.valueOf(String.valueOf(resultSet.getInt("dept_id")));
                String deptName = resultSet.getString("dept_name");
                departments.add(new Department(deptId, deptName));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching departments: " + e.getMessage());
        }
        return departments;
    }

    // CRUD methods for Department
    public static int insertDepartment(Department department) {
        String query = "INSERT INTO department ( dept_name) VALUES ( ?)";
        int rowsAffected = 0;
        Integer deptId = null;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, department.getdept_id());
            statement.setString(1, department.getDept_name());
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " department inserted.");
            //    deptId = getLastId("department","dept_id");
        } catch (SQLException e) {
            System.err.println("Error inserting department: " + e.getMessage());
        }
        return deptId;
    }


    public int updateDepartment(Department department) {
        String query = "UPDATE department SET dept_name = ? WHERE dept_id = ?";
        int rowsAffected = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, department.getDept_name());
            statement.setString(2, department.getDept_id());
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " department(s) updated.");
        } catch (SQLException e) {
            System.err.println("Error updating department: " + e.getMessage());
        }
        return rowsAffected;
    }

    public static int deleteDepartment(String departmentId) {
        String query = "DELETE FROM department WHERE dept_id = ?";
        int rowsAffected = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departmentId);
            rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " department(s) deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting department: " + e.getMessage());
        }
        return rowsAffected;
    }

    public static Department createDepartment(Department department) throws SQLException {
        String query = "INSERT INTO department (dept_id, dept_name) VALUES (?, ?)";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the parameters for the prepared statement
            statement.setString(1, department.getDept_id());
            statement.setString(2, department.getDept_name());
            statement.executeUpdate();
        }
        return department;
    }

    public Department updateDepartment(String dept_name, Department updatedDepartment) throws SQLException {
        // Define the SQL update query
        String sql = "UPDATE Department SET dept_name = ?";
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedDepartment.getDept_name());
            statement.executeUpdate();
        }
            return updatedDepartment;
        }
    }

