package services.pet;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.Constants;
import utils.WebServiceEndPoints;


public class RecordNewPet {

    @Step("Record a new pet")
    public void withDetails(String pet) {
            SerenityRest.given()
                    .baseUri(Constants.BASE_URL)
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .when()
                    .post(WebServiceEndPoints.PET.getPath());
    }
}