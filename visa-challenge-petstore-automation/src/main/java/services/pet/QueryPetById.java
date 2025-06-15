package services.pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.ResponseValidator;
import utils.WebServiceEndPoints;

public class QueryPetById {
    @Step("Query pet by Id")
    public void withDetails(String id) {
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WebServiceEndPoints.PET.getUrl().concat("/").concat(id));

        ResponseValidator.validateStatus(response);
    }
}
