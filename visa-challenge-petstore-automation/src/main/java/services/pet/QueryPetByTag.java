package services.pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.ResponseValidator;
import utils.WebServiceEndPoints;


public class QueryPetByTag {
    @Step("Query pet by Tag")
    public void withDetails(String tagName) {
        Response response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .queryParam("tags", tagName)
                .when()
                .get(WebServiceEndPoints.PETBYTAG.getUrl());
        System.out.println(response.body().prettyPrint());
        ResponseValidator.validateStatus(response); // Manejo de errores
    }
}
