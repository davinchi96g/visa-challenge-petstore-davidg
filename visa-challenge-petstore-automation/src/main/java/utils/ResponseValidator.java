package utils;

import exceptions.ServiceException;
import io.restassured.response.Response;

public class ResponseValidator {

    public static void validateStatus(Response response) {
        int status = response.statusCode();

        if (status == 404) {
            throw new ServiceException("Pet not found. Status code: " + status);
        }
        if (status == 400) {
            throw new ServiceException("ID invalid. Status code: " + status);
        }
        if (status >= 500) {
            throw new ServiceException("Server error. Status code: " + status);
        }

    }
}