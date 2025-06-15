package services.pet;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.WebServiceEndPoints;

public class RecordNewPet {

    @Step("Record a new pet")
    public void withDetails(String pet) {
            SerenityRest.given()
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .when()
                    .post(WebServiceEndPoints.PET.getUrl());
    }
}