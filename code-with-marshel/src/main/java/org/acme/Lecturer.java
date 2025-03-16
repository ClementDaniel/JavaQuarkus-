package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    public static String course;
    public static String firstName;
    public static String lastName;
    public static String email;

    // Constructor
    public Lecturer(String name, String course) {
        this.course = course;
    }


    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }

    public String getCourse() {
        return course;
    }
    public static void add(Lecturer lect1) {
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


