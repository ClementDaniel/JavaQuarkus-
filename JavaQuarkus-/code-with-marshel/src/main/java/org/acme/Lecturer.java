package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    public String course;
    public String firstName;
    public String lastName;
    public String email;

    // Constructor
public Lecturer(String name, String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

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

    // Simple method to print lecturer details
    public void printLecturerDetails() {
        System.out.println("Lecturer(" + firstName + " " + lastName + ","+ email+", " + course + ")");
    }

    public static void main(String[] args) {
        Lecturer lecturer1 = new Lecturer("Dr. Mark Doe", "English");
        Lecturer lecturer2 = new Lecturer("Prof. Johnson Smith", "Mathematics");
        Lecturer lecturer3 = new Lecturer("Dr. Brown White", "Physics");

    }
}


