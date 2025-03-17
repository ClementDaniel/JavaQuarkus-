package org.acme;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    Database database;

    public List<Student> students = new ArrayList<>();

    // Endpoint to get all students
    @GET
    @Path("students")
    public Response getAllStudent() throws JSONException, SQLException {
        // Retrieve the last ID or count of students from the database.
        List<Student> stud1 = database.getAllStudent();

        // Create a JSON object to hold the response.
        JSONObject response = new JSONObject();

        // Check if count is greater than 0, meaning there are students.
        if (stud1.size() > 0) {
            // Populate the JSON response with the count of students.
            return Response.status(Response.Status.OK).entity(stud1).build();
        } else {
            // If no students found, return a 404 status with an appropriate message.
            response.put("message", "No students id found");
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }

    // Endpoint to create a new student
    @POST
    public Response createStudent(Student student) throws JSONException {
        // Add the student to the list
        students.add(student);
        JSONObject response = new JSONObject();
        response.put("message", "Student not added");
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    // Endpoint to update an existing student
    @PUT
    @Path("/{indexNumber}")
    public Response updateStudent(@PathParam("indexNumber") String indexNumber, Student updatedStudent) {
        Student student = students.stream()
                .filter(s -> s.getIndexNumber().equals(indexNumber))
                .findFirst()
                .orElse(null);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student with indexNumber not found").build();
        }
        student.setfirstName(updatedStudent.getfirstName());
        student.setlastName(updatedStudent.getlastName());
        student.setEmail(updatedStudent.setEmail(updatedStudent.getEmail()));
        student.setDepartment(updatedStudent.getDepartment());
        student.setProgram(updatedStudent.getProgram());
        return Response.ok(student).build();
    }

    // Endpoint to delete a student
    @DELETE
    @Path("/{indexNumber/d}")
    public Response deleteStudent(@PathParam("indexNumber") String indexNumber) {
        boolean removed = students.removeIf(student -> student.getIndexNumber().equals(indexNumber));
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student indexNumber not found").build();
        }
        return Response.ok("Student with index number " + indexNumber + " deleted").build();
    }
}
