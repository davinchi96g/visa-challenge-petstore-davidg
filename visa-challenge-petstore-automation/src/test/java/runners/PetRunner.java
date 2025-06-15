package runners;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/pet/pet.feature"},
        glue = {"stepdefinitions"},
        plugin = {"pretty"},
        tags = "@ApiTest")

public class PetRunner {
}
