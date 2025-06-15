package starter.trades;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.WebServiceEndPoints;

public class RecordNewPet {
    @Step("Record a new pet")
    public void withDetails(String pet) {
        SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post(WebServiceEndPoints.PET.getUrl());
    }
}
