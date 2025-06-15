package hooks;

import exceptions.ServiceException;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import io.cucumber.java.Before;

public class ServiceAvailabilityHook {

    @Before(order = 0)
    public void checkServiceAvailability() {
        RestAssured.port = 8085;

        String baseUri = "http://localhost";
        String healthEndpoint = "/pet"; // Usa un endpoint que esté activo

        try {
            int statusCode = SerenityRest.given()
                    .baseUri(baseUri)
                    .port(RestAssured.port)
                    .get(healthEndpoint)
                    .getStatusCode();

            if (statusCode >= 500 || statusCode == 404) {
                throw new ServiceException("❌ Servicio caído: HTTP " + statusCode);
            }

            System.out.println("✅ Servicio disponible - HTTP " + statusCode);

        } catch (Exception e) {
            throw new ServiceException("❌ Error de conexión con el servicio en " + baseUri + ":" + RestAssured.port);
        }
    }
}