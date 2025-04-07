package org.acme.model;

public class Student {
    public String firstName;
    public String lastName;
    public String email;
    public String indexNumber;
    public Department department;
    public Program program;


    public Student() {
    }

    // Constructor with parameters for manual creation of Student object
    public Student(String firstName, String lastName, String email, String indexNumber, Department dept_id, Program program_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.indexNumber = indexNumber;
        this.department = new Department(String.valueOf(Integer.parseInt(String.valueOf(dept_id))));
        this.program = new Program(String.valueOf(Integer.parseInt(String.valueOf(program_id))));
    }

//    public Student(String firstName, String lastName, String email, String indexNumber, String deptId, String programId) {
//    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", department=" + department +
                ", program=" + program +
                '}';
    }

    // Getters and Setters for the fields
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    // Method to print student details
    public void printStudentDetails() {
        System.out.println("Department: " + department);
        System.out.println("Program: " + program);
        System.out.println("Student(" + firstName + " " + lastName + "," + indexNumber + ")");
    }
}
