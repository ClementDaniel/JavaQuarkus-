package org.acme.resource;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.acme.model.Student;
import org.acme.service.StudentService;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentService studentService;

    public List<Student> students = new ArrayList<>();

    // Endpoint to get all students
    @GET
    @Path("")
    public Response getAllStudent() throws JSONException, SQLException {
        // Retrieve the last ID or count of students from the database.
        List<Student> stud1 = studentService.getAllStudents();

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
    @Path("")
    public Response createStudent(Student student) throws JSONException, SQLException {
        // Adding student into DB
        students.add(student);
        // Create student using the service
        Student newStudent = studentService.createStudent(student);
        // Build response JSON
        JSONObject response = new JSONObject();
        response.put("message", "Student added successfully");
        response.put("data", newStudent);
        // Return status 201 (Created)
        return Response.status(Response.Status.CREATED).entity(response.toString())
                .build();
    }

    // Endpoint to update an existing student
    @PUT
    @Path("{indexNumber}")  // Capture indexNumber from URL
    public Response updateStudent(@PathParam("indexNumber") String indexNumber, Student updatedStudent) throws JSONException, SQLException {
        try {
            // Now, indexNumber is extracted from the URL, and not the body
            Student student = studentService.updateStudentByIndexNumber(indexNumber, updatedStudent);

            if (student == null) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("message", "Student with indexNumber " + indexNumber + " not found.");
                return Response.status(Response.Status.NOT_FOUND).entity(errorResponse.toString()).build();
            }

            JSONObject response = new JSONObject();
            response.put("message", "Student updated successfully");
            response.put("data", new JSONObject(student));

            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } catch (Exception e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("message", "Error while updating student: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse.toString()).build();
        }
    }

    @DELETE
    @Path("{indexNumber}")
    public String deleteStudent(@PathParam("indexNumber") String indexNumber) throws JSONException, SQLException {
        try {
            // Attempt to delete the student from the database
            boolean removed = Boolean.parseBoolean(studentService.deleteStudent(indexNumber));
            // Create the response message
            JSONObject response = new JSONObject();
            if (removed) {
                response.put("message", "Student with indexNumber " + indexNumber + " deleted successfully.");
                return Response.status(Response.Status.OK).entity(response.toString()).build().toString();
            } else {
                response.put("message", "Student with indexNumber " + indexNumber + " not found.");
                return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build().toString();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}




