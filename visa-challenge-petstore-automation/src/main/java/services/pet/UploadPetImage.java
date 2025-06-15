package services.pet;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

import io.restassured.builder.ResponseBuilder;
import net.thucydides.core.annotations.Step;
import utils.Constants;
import utils.ResponseValidator;
import io.restassured.response.Response;



public class UploadPetImage {

    @Step("Upload Pet Image")
    public static Response withDetails(String id, File imageFile) throws IOException {
        String endpoint = String.format(Constants.SERVICE_IMAGE,id);
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setRequestProperty("Content-Length", String.valueOf(imageBytes.length));

        try (OutputStream os = connection.getOutputStream()) {
            os.write(imageBytes);
        }

        int responseCode = connection.getResponseCode();
        ResponseValidator.validateStatusImage(responseCode);

        StringBuilder responseBody = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("📥 Response: " + line);
                responseBody.append(line);
            }
        }
        Response dummyResponse = new ResponseBuilder()
                .setBody(responseBody.toString())
                .setStatusCode(responseCode)
                .setContentType("application/json")
                .build();

        return dummyResponse;
    }
    }
