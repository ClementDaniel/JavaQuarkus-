package org.acme;

public class Student{
    public String firstName;
    public String lastName;
    public String email;
    public String indexNumber;
    public Department department;
    public Program program;

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

    // Getters and Setters
    public String getfirstName(){
        return firstName;
    }

    public String getlastName(){
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getIndexNumber(){
        return indexNumber;
    }

    public void setfirstName(String firstName){
        this.firstName = firstName;
    }

    public void setlastName(String lastName){
        this.lastName = lastName;
    }

    public void setIndexNumber(String indexNumber){
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

    public Student(String firstName, String lastName, String email, String indexNumber, Department department, Program program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.indexNumber = indexNumber;
        this.department = department;
        this.program = program;
    }

    // Simple method to print student details
    public void printStudentDetails() {

        System.out.println("Department: " + department);
        System.out.println("Program: " + program);
        System.out.println("Student(" + firstName + " " + lastName + "," + indexNumber +")");
    }

}


