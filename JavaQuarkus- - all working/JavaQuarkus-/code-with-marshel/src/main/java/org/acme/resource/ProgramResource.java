package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Program;
import org.acme.service.ProgramService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/programs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgramResource {

    @Inject
    ProgramService programService;

    public List<Program> program = new ArrayList<>();

    // Endpoint to get a program by ID
    @GET
    @Path("getAllPrograms")
    public Response getAllPrograms() throws JSONException {
        // Retrieve the last ID or count of students from the database.
        List<Program> prog1 = programService.getAllPrograms();

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
    @Path("")
    public Response createProgram(@RequestBody Program program) throws JSONException, SQLException {
        // Assuming you have a programService to handle creating a program
        Program newProgram = programService.createProgram(program);

        // Build the response
        JSONObject response = new JSONObject();
        response.put("message", "Program added successfully");
        response.put("data", newProgram);

        // Return a Response with status 201 (Created)
        return Response.status(Response.Status.CREATED).entity(newProgram).build();
    }

    // Endpoint to update an existing program
    @PUT
    @Path("")
    public Response updateProgram(@PathParam("program_name") String program_name, Program updatedProgram) throws JSONException, SQLException {
        Program program = programService.updateProgram(program_name, updatedProgram);
        if (program == null) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("message", "Program not found");
            return Response.status(Response.Status.NOT_FOUND).entity(errorResponse.toString()).build();
        }
        // Build the response
        JSONObject response = new JSONObject();
        response.put("message", "Program updated successfully");
        response.put("data", program);
        return Response.status(Response.Status.OK).entity(response.toString()).build();
    }

    @DELETE
    @Path("{programId}")
    public Response deleteProgramById(@PathParam("programId") int programId) throws JSONException {
        try {
            int deletedRows = programService.deleteProgramId(programId);
            if (deletedRows == 0) {
                JSONObject response = new JSONObject();
                response.put("message", "Program with ID " + programId + " not found.");
                return Response.status(Response.Status.NOT_FOUND).entity(response.toString()).build();
            }
            JSONObject response = new JSONObject();
            response.put("message", "Program with ID " + programId + " deleted successfully.");
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } catch (Exception e) {
            JSONObject response = new JSONObject();
            response.put("message", "Error while deleting program: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response.toString()).build();
        }
    }
}



