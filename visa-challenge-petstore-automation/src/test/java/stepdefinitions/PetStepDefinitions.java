package stepdefinitions;
import exceptions.ServiceException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import services.pet.QueryPetById;
import services.pet.QueryPetByTag;
import services.pet.RecordNewPet;
import starter.trades.TradeResponse;
import templates.FieldValues;
import templates.MergeFrom;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;



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

    String pet;

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
    @Then("the response message should have a valid body for the query byTag")
    public void the_response_message_should_have_a_valid_body_for_the_query_by_tag() {
        restAssuredThat(response -> response.statusCode(200));
        SerenityRest.restAssuredThat(response -> response.body(matchesJsonSchemaInClasspath("schemas/pet-query-by-tag-schema.json")));

    }
}
