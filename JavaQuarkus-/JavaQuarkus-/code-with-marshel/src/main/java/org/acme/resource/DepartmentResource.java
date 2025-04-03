package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.acme.model.Database;
import org.acme.model.Department;
import org.acme.model.Student;
import org.acme.service.DepartmentService;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    DepartmentService departmentService;

    public List<Department> departments = new ArrayList<>();

    // Endpoint to get all students
    @GET
    @Path("getAllDepartment")
    public Response getAllDepartments() throws JSONException, SQLException {
        // Retrieve students department from the database.
        List<Department> dep1 = departmentService.getAllDepartments();

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
    @Path("")

    public Response createDepartment(Department department) throws JSONException, SQLException {
        // Add the student to the list
        departments.add(department);
        Department newDepartment = DepartmentService.createDepartment(department);
        JSONObject response = new JSONObject();
        response.put("message", "department not added");
        response.put("data", "newDepartment");
        return Response.status(Response.Status.CREATED).entity(department).build();
    }


    @PUT
    @Path("")
    public Response updateDepartment(@PathParam("dept_name") String dept_name, Department updatedDepartment) throws JSONException {
        try {
            Department department = departmentService.updateDepartment(dept_name, updatedDepartment);
            // If the department doesn't exist, return a 404 response
            if (department == null) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("message", "Department not found");
                return Response.status(Response.Status.NOT_FOUND).entity(errorResponse.toString()).build();
            }
            // Build the response
            JSONObject response = new JSONObject();
            response.put("message", "Department updated successfully");
            response.put("data", department);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } catch (Exception e) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("message", "Error while updating department: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse.toString()).build();
        }
    }

    // Endpoint to delete a student
    @DELETE
    @Path("")
    public Response deleteDepartment(@PathParam("deptId") String deptId) throws JSONException {
        try {
            int rowsAffected = DepartmentService.deleteDepartment(deptId);  // Calls the DB delete method
            if (rowsAffected > 0) {
                return Response.status(Response.Status.OK).entity("Department deleted successfully").build();
            } else {
                JSONObject response = new JSONObject();
                response.put("message", "Department not found for deletion");
                return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
            }
        } catch (JSONException e) {
            JSONObject response = new JSONObject();
            response.put("message", "Error while deleting department: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response.toString()).build();
        }
    }
}
