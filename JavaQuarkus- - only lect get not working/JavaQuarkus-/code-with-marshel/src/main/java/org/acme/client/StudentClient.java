package org.acme.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class StudentClient {

    public static final String BASE_URL = "http://localhost:8080/student/students";

    public static void main(String[] args) {
        try {
            // 1. POST Request: Create a new student
            String studentJson = """
                {
                    "firstName": "Seth",
                    "lastName": "Kwame",
                    "indexNumber": "6789",
                    "email": "s.k@example.com",
                    "program": "109"
                    "department":"901"
                }
            """;
            String postResponse = sendPostRequest(BASE_URL + "/post", studentJson);
            System.out.println("POST Response: " + postResponse);

            // 2. PUT Request: Update an existing student by indexNumber
            String updateJson = """
                {
                    "firstName": "Seth",
                    "lastName": "Kwame",
                    "indexNumber": "7890",
                    "email": "s.k@updated.com",
                    "program": "109"
                    "department": 901"
                }
            """;
            String putResponse = sendPutRequest(BASE_URL + "/students/S13790", updateJson);
            System.out.println("PUT Response: " + putResponse);

            // 3. DELETE Request: Delete a student by indexNumber
            String deleteResponse = sendDeleteRequest(BASE_URL + "/S13711");
            System.out.println("DELETE Response: " + deleteResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create a student
    public static String sendPostRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the student data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // Update a student by indexNumber
    public static String sendPutRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the updated student data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // Delete a student by indexNumber
    public static String sendDeleteRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .DELETE()  // Nobody for DELETE
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }
}

