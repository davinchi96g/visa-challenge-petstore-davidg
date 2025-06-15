package services.pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import utils.Constants;
import utils.ResponseValidator;
import utils.WebServiceEndPoints;

public class UpdatePetFormData {
    @Step("Update pet by Form Data")
    public void withDetails(String id, String name,String status) {
        Response response = SerenityRest.given()
                .baseUri(Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .queryParam("name", name)
                .queryParam("status", status)
                .when()
                .post(WebServiceEndPoints.PET.getPath().concat("/").concat(id));
        ResponseValidator.validateStatus(response);
    }
}
