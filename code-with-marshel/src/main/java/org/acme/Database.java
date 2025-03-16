package org.acme;


import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Database {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/universitydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234567890-=";

    // Helper method to get a database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }


    public static void main(String[] args) {
        // Query the student table
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to the database.");
//            String query = "select * from student order by dept_id desc limit 1";
            String query = "select * from student";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                int dept_id = resultSet.getInt("dept_id");
//                String lastName = resultSet.getString("last_name");
                System.out.println("dept_id: " + dept_id);
            }

            resultSet.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public int getLastId(String table, String columnName) {
//        int id = 0;
//        // Query the student table
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()) {
//
//            System.out.println("Connected to the database.");
//            String query = "select * from " + table + " order by " + columnName + " desc limit 1";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Process the result set
//            while (resultSet.next()) {
//                id = resultSet.getInt(columnName);

    /// /                String lastName = resultSet.getString("last_name");
//                System.out.println(columnName + ":" + id);
//            }
//
//            resultSet.close();
//            System.out.println("Database connection closed.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return id;
//    }
    public List<Student> getAllStudent() {
        int id = 0;
        List<Student> students = new ArrayList<>();
        // Query the student table
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to the database.");
            String query = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String indexNumber = resultSet.getString("index_number");
                String email = resultSet.getString("email");
                String deptId = resultSet.getString("dept_id");
                String programId = resultSet.getString("program_id");
//                String studentId = resultSet.getString("student_id");d
                Student stud1 = new Student(firstName, lastName, email, indexNumber, (Department) null, (Program) null);
                students.add(stud1);

            }
            resultSet.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Lecturer> getAllLecturers() {
        int id = 0;
        List<Student> students = new ArrayList<>();
        // Query the lecturer table
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to the database.");
            String query = "SELECT * FROM lecturer";
            ResultSet resultSet = statement.executeQuery(query);
            // Process the result set
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Lecturer lect1 = new Lecturer(firstName, lastName);
                Lecturer.add(lect1);
            }
            resultSet.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getAllLecturers();
    }

    // todo fetch program by id
    public List<Program> getAllProgram() {
        int id = 0;
        List<Program> programs = new ArrayList<>();
        // Query the program table
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to the database.");
            String query = "SELECT * FROM program";
            ResultSet resultSet = statement.executeQuery(query);
            // Process the result set
            while (resultSet.next()) {
                String program_name = resultSet.getString("program_name");
                String programId = resultSet.getString("program_id");
                Program prog1 = new Program(Integer.parseInt(programId), program_name);
                programs.add(prog1);

            }
            resultSet.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return programs;
    }
    // todo fetch department by id
    public List<Department> getAllDepartment() {
            int id = 0;
            List<Department> department = new ArrayList<>();
            // Query the department table
            try (Connection connection = getConnection();
                 Statement statement = connection.createStatement()) {

                System.out.println("Connected to the database.");
                String query = "SELECT * FROM department";
                ResultSet resultSet = statement.executeQuery(query);
                // Process the result set
                while (resultSet.next()) {
                    String dept_name = resultSet.getString("dept_name");
                    String dept_id = resultSet.getString("dept_id");
                    Department dep1 = new Department(Integer.parseInt(dept_id), dept_name);
                    Department.add(dep1);

                }
                resultSet.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return department;
        }
    }




//                // CRUD methods for Student
//                public List<Student> insertStudent (Student Student){
//                    int rowsAffected = 0;
//                    String query = "INSERT INTO student (first_name, last_name, index_number, email, dept_id, program_id) VALUES (?, ?, ?, ?, ?, ?)";
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, students.getfirstName());
//                        statement.setString(2, students.getlastName());
//                        statement.setString(3, students.getIndexNumber());
//                        statement.setString(4, students.getEmail());
//                        statement.setString(5, String.valueOf(students.getDepartment().getdept_id()));
//                        statement.setString(6, String.valueOf(students.getProgram().getProgram_id()));
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " student record(s) inserted.");
//                    } catch (SQLException e) {
//                        System.err.println("Error inserting student: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public List<Student> updateStudentEmail (String newEmail, String indexNumber){
//                    String query = "UPDATE student SET email = ? WHERE index_number = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, newEmail);
//                        statement.setString(2, String.valueOf(Integer.valueOf(indexNumber)));
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " student record(s) updated.");
//                    } catch (SQLException e) {
//                        System.err.println("Error updating student email: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int deleteStudent (String indexNumber){
//                    String query = "DELETE FROM student WHERE index_number = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, indexNumber);
//                        rowsAffected = statement.executeUpdate();
//                        if (rowsAffected > 0) {
//                            System.out.println(rowsAffected + " student record(s) deleted.");
//                        } else {
//                            System.out.println("No student found with index number: " + indexNumber);
//                        }
//                    } catch (SQLException e) {
//                        System.err.println("Error deleting student: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }


//    // helper method for executing update
//    private void executeUpdate(String query, String message) {
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()) {
//            int rowsAffected = statement.executeUpdate(query);
//            System.out.println(rowsAffected + " " + message);
//        } catch (SQLException e) {
//            System.err.println("Database error: " + e.getMessage());
//        }
//    }
//
//                // CRUD methods for Program
//                public Integer insertProgram (Program program){
//                    String query = "INSERT INTO program (program_name) VALUES (?)";
//                    int rowsAffected = 0;
//                    int Program_id = 0;
//
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//                        statement.setString(1, program.getProgram_name());
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " program inserted.");
//
//                        Program_id = getLastId("program", "program_id");
//
//                    } catch (SQLException e) {
//                        System.err.println("Error inserting program: " + e.getMessage());
//                    }
//
//                    return Program_id;
//                }
//
//                public int updateProgram_Name (String program_name, String program_id){
//                    String query = "UPDATE program SET name = ? WHERE program_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, program_name);
//                        statement.setString(2, program_id);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " program(s) updated.");
//                    } catch (SQLException e) {
//                        System.err.println("Error updating program: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int deleteProgram (String program_id){
//                    String query = "DELETE FROM program WHERE program_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, program_id);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " program(s) deleted.");
//                    } catch (SQLException e) {
//                        System.err.println("Error deleting program: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                // CRUD methods for Department
//                public Integer insertDepartment (Department department){
//                    String query = "INSERT INTO department ( dept_name) VALUES ( ?)";
//                    int rowsAffected = 0;
//                    Integer deptId = null;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
////            statement.setInt(1, department.getdept_id());
//                        statement.setString(1, department.getDept_name());
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " department inserted.");
//                        deptId = getLastId("department", "dept_id");
//                    } catch (SQLException e) {
//                        System.err.println("Error inserting department: " + e.getMessage());
//                    }
//                    return deptId;
//                }
//
//                public int updateDepartmentCourses (String newCourses, String departmentId){
//                    String query = "UPDATE department SET courses = ? WHERE department_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, newCourses);
//                        statement.setString(2, departmentId);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " department(s) updated.");
//                    } catch (SQLException e) {
//                        System.err.println("Error updating department: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int deleteDepartment (String departmentId){
//                    String query = "DELETE FROM department WHERE department_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, departmentId);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " department(s) deleted.");
//                    } catch (SQLException e) {
//                        System.err.println("Error deleting department: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                // CRUD methods for Lecturer
//                public static int insertlecturer (Lecturer lecturer){
//                    String query = "INSERT INTO lecturer (name, course) VALUES (?, ?)";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, lecturer.getFirstName());
//                        statement.setString(2, lecturer.getCourse());
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " lecturer inserted.");
//                    } catch (SQLException e) {
//                        System.err.println("Error inserting lecturer: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int updatelecturerName (String newName, String lecturerId){
//                    String query = "UPDATE lecturer SET name = ? WHERE lecturer_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, newName);
//                        statement.setString(2, lecturerId);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " lecturer name updated.");
//                    } catch (SQLException e) {
//                        System.err.println("Error updating lecturer name: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int updatelecturerCourse (String newCourse, String lecturerId){
//                    String query = "UPDATE lecturer SET course = ? WHERE lecturer_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, newCourse);
//                        statement.setString(2, lecturerId);
//                        rowsAffected = statement.executeUpdate();
//                        System.out.println(rowsAffected + " lecturer course updated.");
//                    } catch (SQLException e) {
//                        System.err.println("Error updating lecturer course: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//
//                public int deletelecturer (String lecturerId){
//                    String query = "DELETE FROM lecturer WHERE lecturer_id = ?";
//                    int rowsAffected = 0;
//                    try (Connection connection = getConnection();
//                         PreparedStatement statement = connection.prepareStatement(query)) {
//
//                        statement.setString(1, lecturerId);
//                        rowsAffected = statement.executeUpdate();
//                        if (rowsAffected > 0) {
//                            System.out.println(rowsAffected + " lecturer(s) deleted.");
//                        } else {
//                            System.out.println("No lecturer found with ID: " + lecturerId);
//                        }
//                    } catch (SQLException e) {
//                        System.err.println("Error deleting lecturer: " + e.getMessage());
//                    }
//                    return rowsAffected;
//                }
//            }
//        }
//    }
//    }
////



