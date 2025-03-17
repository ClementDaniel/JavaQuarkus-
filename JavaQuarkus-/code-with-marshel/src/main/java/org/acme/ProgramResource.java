package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Path("/programs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgramResource {

    @Inject
    Database database;

    public List<Program> program = new ArrayList<>();

    // Endpoint to get a program by ID
    @GET
    @Path("getAllPrograms")
    public Response getAllPrograms() throws JSONException {
        // Retrieve the last ID or count of students from the database.
        List<Program> prog1 = database.getAllProgram();

        // Create a JSON object to hold the response.
        JSONObject response = new JSONObject();

        // Check if count is greater than 0, meaning there are students.
        if (prog1.size() > 0) {
            // Populate the JSON response with the count of students.
            return Response.status(Response.Status.OK).entity(prog1).build();
        } else {
            // If no students found, return a 404 status with an appropriate message.
            response.put("message", "No students programs found");
            return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
        }
    }


    // Endpoint to create a new program
    @POST
    public Response createProgram(Program program) {
        program.wait(program);
        return Response.status(Response.Status.CREATED).entity(program).build();
    }

    // Endpoint to update an existing program
    @PUT
    @Path("/{programId}")
    public Response updateProgram(@PathParam("programId") int programId, Program updatedProgram) {
        // Assume programs is a collection of Program objects
        List<Program> programs = program; // Replace with your actual data source

        Program program = programs.stream()
                .filter(p -> p.getProgram_id() == programId) // Corrected to use getProgram_id()
                .findFirst()
                .orElse(null);

        if (program == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Program id  not found or not defined").build();
        }

        program.setProgram_id(updatedProgram.getProgram_id());
        program.setProgram_name(updatedProgram.getProgram_name());
        return Response.ok(program).build();
    }

    // Endpoint to delete a program
    @DELETE
    @Path("/{programId}")
    public Response deleteProgram(@PathParam("programId") int programId) {
        boolean removed = program.removeIf(program -> program.getProgram_id() == programId);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).entity("Program not found").build();
        }
        return Response.ok("Program with ID " + programId + " deleted").build();
    }
}



