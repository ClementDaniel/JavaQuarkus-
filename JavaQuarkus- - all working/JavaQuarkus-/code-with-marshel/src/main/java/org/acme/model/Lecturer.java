package org.acme.model;

public class Lecturer {
    private  String course;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String lecturerId;

    // Constructor
    public Lecturer(String firstName, String lastName ,String lecturerId ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lecturerId = lecturerId;
    }
    // Default constructor required by JSON-B
    public Lecturer() {

    }

    public String getCourse () {
            return course;
        }

        public void setCourse (String course){
            this.course = course;
        }

        public String getFirstName () {
            return firstName;
        }

    public String getLecturerId () {
        return lecturerId;
    }

        public void setFirstName (String firstName){
            this.firstName = firstName;
        }

        public String getLastName () {
            return lastName;
        }

        public void setLastName (String lastName){
            this.lastName = lastName;
        }

        public String getEmail () {
            return email;
        }

        public void setLecturerId (String lecturerId){
            this.lecturerId = lecturerId;
        }
        public void setEmail (String email){
            this.email = email;
        }

    // Simple method to print lecturer details
    public void printLecturerDetails () {
        System.out.println("Lecturer(" + firstName + " " + lastName + "," + email + ", " + course + ")");
    }
}







