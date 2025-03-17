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

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    Database database;

    public List<Department> departments = new ArrayList<>();

    // Endpoint to get all students
    @GET
    @Path("getAllDepartment")
    public Response getAllDepartments() throws JSONException, SQLException {
        // Retrieve students department from the database.
        List<Department> dep1 = database.getAllDepartment();

        // Create a JSON object to hold the response.
        JSONObject response = new JSONObject();

        // Check if count is greater than 0, meaning there are students.
        if (dep1.size() > 0) {
            // Populate the JSON response with the count of students.
            return Response.status(Response.Status.OK).entity(dep1).build();
        } else {
            // If no students found, return a 404 status with an appropriate message.
            response.put("message", "No students  department found");
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }

    // Endpoint to create a new student
    @POST
    public Response createDepartment(Department department) throws JSONException {
        // Add the student to the list
        departments.add(department);
        JSONObject response = new JSONObject();
        response.put("message", "department not added");
        return Response.status(Response.Status.CREATED).entity(department).build();
    }

    // Endpoint to update an existing departmetn
//    @PUT
//    @Path("/{indexNumber}")


    // Endpoint to delete a student
    @DELETE
    @Path("delete}")
    public Response deleteStudent(@PathParam("dept_name") String dept_name) {
        boolean removed = departments.removeIf(student -> student.getClass().equals(departments));
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student department not found").build();
        }
        return Response.ok("Student with index number " + dept_name + " deleted").build();
    }
}
