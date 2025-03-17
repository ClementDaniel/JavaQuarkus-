package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    Database database;

    public List<Lecturer> Lecturers = new ArrayList<>();
    // Endpoint to get all lecturers

    // Endpoint to get a lecturer by email
    @GET
    @Path("getAllLecturers")
    public Response getAllLecturers() throws JSONException {
        // Retrieve the last ID or count of students from the database.
        List<Lecturer> lect1 = database.getAllLecturers();

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
    public Response createLecturer(Lecturer lecturer) {
        Lecturers.add(lecturer);
        return Response.status(Response.Status.CREATED).entity(lecturer).build();
    }

    // Endpoint to update an existing lecturer
//    @PUT
//    @Path("/{email}")
//    public Response updateLecturer(@PathParam("email") String email, Lecturer updatedLecturer) {
//        Lecturer lecturer = Lecturers.stream()
//                .filter(l -> l.getEmail().equals(email))
//                .findFirst()
//                .orElse(null);
//        if (lecturer == null) {
//            return Response.status(Response.Status.NOT_FOUND).entity("Lecturer not ").build();
//        }
//        Lecturer.firstName = updatedLecturer.getFirstName();
//        Lecturer.lastName = updatedLecturer.getLastName();
//        Lecturer.course = updatedLecturer.getCourse();
//        Lecturer.email = updatedLecturer.getEmail();
//        return Response.ok(lecturer).build();
//    }

    // Endpoint to delete a lecturer
    @DELETE
    @Path("/{email}")
    public Response deleteLecturer(@PathParam("email") String email) {
        boolean removed = Lecturers.removeIf(lecturer -> lecturer.getEmail().equals(email));
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Lecturer not found").build();
        }
        return Response.ok("Lecturer with email " + email + " deleted").build();
    }
}

