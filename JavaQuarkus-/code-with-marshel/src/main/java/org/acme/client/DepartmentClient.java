package org.acme.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class DepartmentClient {

    private static final String BASE_URL = "http://localhost:8080/departments";  // Update with your actual API URL

    public static void main(String[] args) {
        try {
            // 1. POST Request: Create a new department
            String departmentJson = """
                {
                    "name": "Computer Science",
                    "location": "Building A",
                    "budget": 500000
                }
            """;
            String postResponse = sendPostRequest(BASE_URL + "/post", departmentJson);
            System.out.println("POST Response: " + postResponse);

            // 2. PUT Request: Update an existing department by ID
            String updateJson = """
                {
                    "name": "Computer Engineering",
                    "location": "Building B",
                    "budget": 600000
                }
            """;
            String putResponse = sendPutRequest(BASE_URL + "/deptId/1", updateJson); // Update department with ID 1
            System.out.println("PUT Response: " + putResponse);

            // 3. DELETE Request: Delete a department by ID
            String deleteResponse = sendDeleteRequest(BASE_URL + "/1"); // Delete department with ID 1
            System.out.println("DELETE Response: " + deleteResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // POST Request: Create a department
    public static String sendPostRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the department data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // PUT Request: Update an existing department by ID
    public static String sendPutRequest(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(10))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))  // Send the updated department data as JSON
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();  // Return the response body
    }

    // DELETE Request: Delete a department by ID
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
