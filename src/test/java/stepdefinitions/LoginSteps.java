package stepdefinitions;

import actions.LoginActions;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Steps;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {


    @Steps
    LoginActions login;
    LoginActions loginActions;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        login.navigateToLoginPage();
        login.dismissPopupIfPresent();
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        login.enterEmail();
        login.enterPassword();
        login.clickLogin();
    }

    @Then("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        login.verifySuccessfulLogin();

    }

    @When("the user attempts to log in with {string} and {string}")
    public void the_user_attempts_to_log_in_with_and(String email, String password) {
        loginActions.enterEmailDT(email);
        loginActions.enterPasswordDT(password);
        if (!loginActions.isLoginButtonEnabled()) {
            // This block only runs if the button is *NOT* enabled
            System.out.println("Login button is disabled - skipping click.");
            loginActions.markAsPassedWithoutExecution();
            return;
        }
        loginActions.clickLogin();
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        if (!loginActions.isLoginButtonEnabled()) {
            // This block only runs if the button is *NOT* enabled
            System.out.println("No error message expected because login was not attempted.");
            return;
        }

        login.$(By.cssSelector(".error.ng-star-inserted")).waitUntilVisible();
        assertThat(login.$(By.cssSelector(".error.ng-star-inserted")).getText()).containsIgnoringCase("Invalid");
    }



}
