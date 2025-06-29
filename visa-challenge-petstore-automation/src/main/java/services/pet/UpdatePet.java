package services.pet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.Constants;
import utils.ResponseValidator;
import utils.WebServiceEndPoints;

public class UpdatePet {
    @Step("Update a pet")
    public void withDetails(String pet) {
        Response response = SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .put(WebServiceEndPoints.PET.getPath());
        ResponseValidator.validateStatus(response);
    }
}
