package runners;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/status/check_on_application_status.feature"},
        glue = {"stepdefinitions"},
        plugin = {"pretty"})

public class statusRunner {
}
