package org.acme.client;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class LecturerClient {

    public static final String BASE_URL = "http://localhost:8080/lecturers";

    public static void main(String[] args) {
        try {
            // Request body(for POST)
            String jsonBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "johndoe@example.com",
                    "course": "Computer Science"
                }
            """;

            // A POST request to create lecturer
            String response = sendPostRequest(BASE_URL + "create", jsonBody);
            System.out.println("Response from POST (Create): " + response);

            // A PUT request to update the lecturer
            String updateJsonBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "johndoe@example.com",
                    "course": "Data Science"
                }
            """;
            response = sendPutRequest(BASE_URL + "/johndoe@example.com", updateJsonBody);
            System.out.println("Response from PUT (Update): " + response);

            // DELETE request to delete the lecturer
            response = sendDeleteRequest(BASE_URL + "/johndoe@example.com");
            System.out.println("Response from DELETE: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Send POST request
    public static String sendPostRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Send PUT request
    public static String sendPutRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Send DELETE request
    public static String sendDeleteRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .DELETE()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}


