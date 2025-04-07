package org.acme.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ProgramClient {

    public static final String BASE_URL = "http://localhost:8080/programs/getAllPrograms";

    public static void main(String[] args) {
        try {
            // 1. POST Request: Create a new program
            String programJson = """
                {
                    "program_id": 101,
                    "program_name": "Computer Science"
                }
            """;
            String postResponse = sendPostRequest(BASE_URL + "/create", programJson);
            System.out.println("POST Response: " + postResponse);

            // 2. PUT Request: Update an existing program by programId
            String updateJson = """
                {
                    "program_id": 101,
                    "program_name": "Software Engineering"
                }
            """;
            String putResponse = sendPutRequest(BASE_URL + "/101", updateJson);
            System.out.println("PUT Response: " + putResponse);

            // 3. DELETE Request: Delete a program by programId
            String deleteResponse = sendDeleteRequest(BASE_URL + "/101");
            System.out.println("DELETE Response: " + deleteResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // POST Request: Create a program
    public static String sendPostRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the program data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // PUT Request: Update an existing program by programId
    public static String sendPutRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the updated program data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // DELETE Request: Delete a program by programId
    public static String sendDeleteRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .DELETE()  // No body for DELETE
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }
}
