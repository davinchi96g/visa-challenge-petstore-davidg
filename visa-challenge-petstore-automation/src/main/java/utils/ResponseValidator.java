package utils;

import exceptions.ServiceException;
import io.restassured.response.Response;


import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


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
    public static void assertResponseContains(Map<String, String> actual, Map<String, String> expected) {
        try {
            assertThat(actual).containsAllEntriesOf(expected);
        } catch (AssertionError e) {
            throw new ServiceException("Fail validation, the expected Value is different from the actual value.\n"
                    + "Expected: " + expected + "\n"
                    + "Actual: " + actual);
        }
    }

    public static void validateStatusImage(int status) {

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
