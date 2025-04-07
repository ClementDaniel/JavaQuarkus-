package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Lecturer;
import org.acme.model.Student;
import org.acme.service.LecturerService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.acme.model.Lecturer.lastName;

@Path("/lecturers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LecturerResource {

    @Inject
    LecturerService lecturerService;

    public List<Lecturer> Lecturers = new ArrayList<>();

    @GET
    @Path("getAllLecturers")
    public Response getAllLecturers() throws JSONException {
        // Retrieve the last ID or count of students from the database.
        List<Lecturer> lect1 = lecturerService.getAllLecturers();
        // Create a JSON object to hold the response.
        JSONObject response = new JSONObject();
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
    public Response createLecturer(@RequestBody Lecturer lecturer) throws JSONException, SQLException {
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
    public Response updateLecturer(@RequestBody Lecturer updatedLecturer) throws JSONException, SQLException {
        // Assuming you have a lecturerService to handle the updating process
        Lecturer lecturer = lecturerService.updateLecturer(updatedLecturer);
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

    @DELETE
    @Path("{firstName}")
    public Response deleteLecturer(@PathParam("firstName") String firstName) throws JSONException {
        boolean removed = Lecturers.removeIf(lecturer -> lecturer.getfirstName().equalsIgnoreCase(firstName));
        JSONObject response = new JSONObject();
        if (!removed) {
            response.put("message", "Lecturer named '" + firstName + "' not found.");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(response.toString())
                    .build();
        }
        response.put("message", "Lecturer '" + firstName + "' deleted successfully.");
        return Response.ok(response.toString()).build();
    }
}




