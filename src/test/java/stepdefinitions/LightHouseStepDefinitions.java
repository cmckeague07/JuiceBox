package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class LightHouseStepDefinitions {
    @Given("the accessibility and performance audits were run externally")
    public void auditsRunExternally() {
        System.out.println("Lighthouse executed manually or via run_lighthouse.bat script.");
    }

    @Then("the generated reports should exist in the reports lighthouse folder")
    public void verifyLighthouseReportsExist() {
        File htmlReport = new File("reports/lighthouse-report.report.html");
        File jsonReport = new File("reports/lighthouse-report.report.json");
        assertThat(htmlReport.exists()).isTrue();
        assertThat(jsonReport.exists()).isTrue();
    }

}
