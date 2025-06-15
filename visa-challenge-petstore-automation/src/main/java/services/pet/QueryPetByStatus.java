package services.pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.Constants;
import utils.ResponseValidator;
import utils.WebServiceEndPoints;

public class QueryPetByStatus {
    @Step("Query pet by Status")
    public void withDetails(String status) {
        Response response = SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("status", status)
                .when()
                .get(WebServiceEndPoints.PETBYSTATUS.getPath());
        ResponseValidator.validateStatus(response);
    }
}
