package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Lecturer;
import org.acme.model.Student;
import org.acme.service.LecturerService;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/lecturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {

    @Inject
    LecturerService lecturerService;

    public List<Lecturer> Lecturers = new ArrayList<>();
    // Endpoint to get all lecturers

    // Endpoint to get a lecturer by email
    @GET
    @Path("getAllLecturers")
    public Response getAllLecturers() throws JSONException {
        // Retrieve the last ID or count of students from the database.
        List<Lecturer> lect1 = lecturerService.getAllLecturers();

        // Create a JSON object to hold the response.
        JSONObject response = new JSONObject();

        // Check if count is greater than 0, meaning there are students.
        if (!lect1.isEmpty()) {
            // Populate the JSON response with the count of students.
            return Response.status(Response.Status.OK).entity(lect1).build();
        } else {
            // If no students found, return a 404 status with an appropriate message.
            response.put("message", "No lecturer found");
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }

    // Endpoint to create a new lecturer
    @POST
    @Path("")
    public Response createLecturer(Lecturer lecturer) throws JSONException, SQLException {
        Lecturers.add(lecturer);
        Lecturer newlecturer = lecturerService.createLecturer(lecturer);
        // Build response JSON
        JSONObject response = new JSONObject();
        response.put("message", "Lecturer added successfully");
        response.put("data", newlecturer);
        return Response.status(Response.Status.CREATED).entity(lecturer).build();
    }

    // Endpoint to update an existing lecturer
    @PUT
    @Path("")
    public Response updateLecturer(@PathParam("first_name,last_name") String firstName, String lastName , Lecturer updatedLecturer) throws JSONException, SQLException {
        // Assuming you have a lecturerService to handle the updating process
        Lecturer lecturer = lecturerService.updateLecturer(firstName, lastName, updatedLecturer);
        // If the lecturer doesn't exist, return a 404 response
        if (lecturer == null) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("message", "Lecturer not found");
            return Response.status(Response.Status.NOT_FOUND).entity(errorResponse.toString()).build();
        }
        // Build the response
        JSONObject response = new JSONObject();
        response.put("message", "Lecturer updated successfully");
        response.put("data", lecturer);
        // Return a Response with status 200 (OK)
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }


    // Endpoint to delete a lecturer
    @DELETE
    @Path("/{d}")
    public Response deleteLecturer(@PathParam("email") String email) {
        boolean removed = Lecturers.removeIf(lecturer -> lecturer.getfirstName().equals(lecturer.getfirstName()));
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Lecturer not found").build();
        }
        return Response.ok("Lecturer with email " + email + " deleted").build();
    }
}

