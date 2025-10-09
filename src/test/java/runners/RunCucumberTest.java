package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        tags="@OnlyThis",
        plugin = {
                "pretty",
                "junit:C:/JuiceBox/JuiceBoxAutomation/junit-report.xml",
                "html:C:/JuiceBox/JuiceBoxAutomation/html-report",

        }
)
public class RunCucumberTest {
}
