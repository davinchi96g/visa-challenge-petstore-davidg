package stepdefinitions;
import exceptions.ServiceException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import services.pet.*;
import templates.FieldValues;
import templates.MergeFrom;
import utils.ImageUpload;
import utils.RecordPetResponse;
import utils.ResponseValidator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class PetStepDefinitions {

    @Before
    public void setUp() throws ServiceException {
        RestAssured.port = 8085;
    }

    @Steps
    RecordNewPet recordNewPet;

    @Steps
    QueryPetById queryPetById;

    @Steps
    QueryPetByTag queryPetByTag;

    @Steps
    QueryPetByStatus queryPetByStatus;

    @Steps
    DeletePetById deletePetById;

    @Steps
    UpdatePet updatePet;

    @Steps
    RecordPetResponse recordPetResponse;

    @Steps
    UpdatePetFormData updatePetFormData;

    @Steps
    UploadPetImage uploadPetImage;

    String pet;

    String petExpected;

    String idImage;

    Response dummyResponse;


    @Given("the following data for the pet:")
    public void setDataPet(List<Map<String, String>> petDetails) throws IOException {


        pet = MergeFrom.template("templates/petCreate.json")
                .withDefaultValuesFrom(FieldValues.in("templates/standard-pet.properties"))
                .withFieldsFrom(petDetails.get(0));

    }
    @When("I record the pet")
    public void recordPet() {
        recordNewPet.withDetails(pet);
    }

    @Then("the response message should have a valid body")
    public void validateResponse() {
        restAssuredThat(response -> response.statusCode(200));
        SerenityRest.restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("schemas/pet-schema.json")));

    }

    @Given("the following data for searching the pet:")
    public void the_following_data_for_searching_the_pet(List<Map<String, String>> petId) {
        queryPetById.withDetails(petId.get(0).get("id"));
    }

    @Then("the response message should have a valid body for the query")
    public void the_response_message_should_have_a_valid_body_for_the_query() {
        restAssuredThat(response -> response.statusCode(200));
        SerenityRest.restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("schemas/pet-query-schema.json")));

    }

    @Given("the following data for searching the pet byTag:")
    public void the_following_data_for_searching_the_pet_by_tag(List<Map<String, String>> petTag) {
        queryPetByTag.withDetails(petTag.get(0).get("tag"));

    }
    @Then("the response message should have a valid body for the query Parameter")
    public void the_response_message_should_have_a_valid_body_for_the_query_by_tag() {
        restAssuredThat(response -> response.statusCode(200));
        SerenityRest.restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("schemas/pet-query-by-tag-schema.json")));

    }

    @Given("the following data for searching the pet byStatus:")
    public void the_following_data_for_searching_the_pet_by_status(List<Map<String, String>> petStatus) {
        queryPetByStatus.withDetails(petStatus.get(0).get("status"));
    }

    @Given("the following data for deleting the pet:")
    public void the_following_data_for_deleting_the_pet(List<Map<String, String>> petId) {
        deletePetById.withDetails(petId.get(0).get("id"));
    }

    @Then("the response message should be {string}")
    public void the_response_message_should_be(String expectedMessage) {
        restAssuredThat(lastResponse -> lastResponse.body(equalTo(expectedMessage)));

    }

    @Given("the following data for the pet edition:")
    public void the_following_data_for_the_pet_edition(List<Map<String, String>> petDetails) throws IOException {
        pet = MergeFrom.template("templates/petCreate.json")
                .withDefaultValuesFrom(FieldValues.in("templates/standard-pet.properties"))
                .withFieldsFrom(petDetails.get(0));
    }

    @When("I edit the data of the pet")
    public void i_edit_the_data_of_the_pet() {
        updatePet.withDetails(pet);
    }

    @Then("the response should include the following details:")
    public void the_response_should_include_the_following_details(List<Map<String, String>> petDetails) throws IOException{
        petExpected = MergeFrom.template("templates/petCreate.json")
                .withDefaultValuesFrom(FieldValues.in("templates/standard-pet.properties"))
                .withFieldsFrom(petDetails.get(0));

        restAssuredThat(response -> response.statusCode(200));

        Map<String, String> expectedResponse = recordPetResponse.returned();
        Map<String, String> actualResponse  = recordPetResponse.fromJson(petExpected);

        ResponseValidator.assertResponseContains(actualResponse, expectedResponse);
//        assertThat(actualResponse).as("Fail validation, the expected Value is diferent from the actual value").containsAllEntriesOf(expectedResponse);

    }
    @Given("I update the the pet with edition on form data:")
    public void the_following_data_for_the_pet_edition_on_form_data(List<Map<String, String>> petData) {
        updatePetFormData.withDetails(petData.get(0).get("id"), petData.get(0).get("name"), petData.get(0).get("status"));
    }

    @Given("the id for uploading the image")
    public void the_id_for_uploading_the_image(List<Map<String, String>> petId) {
        idImage = petId.get(0).get("id");
    }

    @When("I send the image {string}")
    public void i_send_the_image(String imagePath) throws IOException {
        File image = ImageUpload.getImage(imagePath);
        dummyResponse = uploadPetImage.withDetails(idImage,image);


    }

    @Then("the response message should have a valid body for the image upload")
    public void the_response_message_should_have_a_valid_body_for_the_image_upload() {
        assertThat(dummyResponse.getStatusCode(), equalTo(200));
        assertThat(dummyResponse.asString(), matchesJsonSchemaInClasspath("schemas/pet-upload-image-schema.json"));

    }



}
