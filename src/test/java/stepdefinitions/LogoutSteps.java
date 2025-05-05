package stepdefinitions;

import actions.LoginActions;
import actions.LogoutPage;
import io.cucumber.java.Before;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutSteps extends LoginActions {

    LogoutPage logoutPage;

    @Managed
    WebDriver driver;

    @Before
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    @When("I click the logout button")
    public void userClicksLogout() {
        logoutPage.clickLogout();
    }

    @Then("I should be logged out")
    public void userSeesLoginPage() {
        assertThat(logoutPage.isLoginButtonVisible()).isTrue();
    }
}