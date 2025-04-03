package org.acme.model;

public class Lecturer {
    public static String course;
    public static String firstName;
    public static String lastName;
    public static String email;

    // Constructor
    public Lecturer(String firstName, String lastName) {
        Lecturer.firstName = firstName;
        Lecturer.lastName = lastName;
    }
    // Default constructor required by JSON-B
    public Lecturer() {
    }

    public static void main (String[]args){
        Lecturer lecturer1 = new Lecturer("Dr. Mark Doe", "English");
        Lecturer lecturer2 = new Lecturer("Prof. Johnson Smith", "Mathematics");
        Lecturer lecturer3 = new Lecturer("Dr. Brown White", "Physics");

    }

    public String getCourse () {
            return course;
        }

        public void setCourse (String course){
            this.course = course;
        }

        public String getfirstName () {
            return firstName;
        }

        public void setfirstName (String firstName){
            this.firstName = firstName;
        }

        public String getlastName () {
            return lastName;
        }

        public void setlastName (String lastName){
            this.lastName = lastName;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail (String email){
            this.email = email;
        }

    // Simple method to print lecturer details
    public void printLecturerDetails () {
        System.out.println("Lecturer(" + firstName + " " + lastName + "," + email + ", " + course + ")");
    }
}







