package stepdefinitions;

import actions.SecurityHeaderActions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import actions.SecurityActions;
import org.junit.Assert;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class SecurityStepDefinitions {

    @Steps
    SecurityActions securityActions;

    SecurityHeaderActions securityHeaderActions = new SecurityHeaderActions();

    @Given("the user opens the Juice Shop application")
    public void openJuiceShopApp() {
        securityActions.openJuiceShop();
    }

    @Given("the browser is cleared of all cookies and sessions")
    public void clearBrowserCookies() {
        securityActions.clearCookies();
    }

    @When("the user refreshes the page")
    public void refreshPage() {
        securityActions.refreshPage();
    }

    @Then("the user should remain logged in")
    public void verifyUserStillLoggedIn() {
        boolean isLoggedIn = securityActions.verifyUserStillLoggedIn();

        Assert.assertTrue(
                "User not logged in after refresh – 'Your Basket' not visible",
                isLoggedIn
        );
    }

    @When("the user sends a GET request to the home page")
    public void theUserSendsAGetRequestToTheHomePage() {
        securityHeaderActions.sendGetRequestToHomePage();
    }

    @Then("the response headers should include {string}")
    public void theResponseHeadersShouldInclude(String headerName) {
        boolean headerPresent = securityHeaderActions.verifyHeaderPresent(headerName);
        Assert.assertTrue("Missing security header: " + headerName, headerPresent);
    }

    @And("the headers should include {string}")
    public void theHeadersShouldInclude(String headerName) {
        boolean headerPresent = securityHeaderActions.verifyHeaderPresent(headerName);
        Assert.assertTrue("Missing security header: " + headerName, headerPresent);
    }


    @Given("the user is not logged in")
    public void ensureUserIsNotLoggedIn() {
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().refresh();
    }


    @When("the user tries to access the checkout page directly via URL")
    public void tryAccessingCheckoutDirectly() {
        String checkoutUrl = "http://localhost:3000/#/address/select";
        getDriver().get(checkoutUrl);
        System.out.println("Attempted direct access to: " + checkoutUrl);
    }

    @Then("the application should redirect them to the login page")
    public void verifyRedirectedToLoginPage() {
        String currentUrl = getDriver().getCurrentUrl();
        Assert.assertTrue("Expected redirect to login, but was: " + currentUrl,
                currentUrl.contains("/#/login"));
        System.out.println("Redirected to login page successfully.");
    }

    @And("display a message indicating authentication is required")
    public void verifyAuthRequiredMessage() {
        String pageSource = getDriver().getPageSource().toLowerCase();
        boolean messageVisible = pageSource.contains("please login")
                || pageSource.contains("403")
                || pageSource.contains("authentication required");

        Assert.assertTrue("Expected authentication required message or 403 page.", messageVisible);
    }

    @Given("the user is on the main shop page")
    public void userIsOnMainShopPage() {
        securityActions.navigateToHomePage();
    }

    @When("the user searches for {string}")
    public void userSearchesFor(String query) {
        securityActions.performSearch(query);
    }

    @Then("no alert dialog should appear")
    public void noAlertDialogShouldAppear() {
        boolean alertAppeared = securityActions.isAlertPresent();
        Assert.assertFalse("Alert dialog appeared, XSS vulnerability detected!", alertAppeared);
    }

    @And("the search results should safely render escaped text instead of executing the script")
    public void searchResultsShouldBeEscaped() {
        boolean textEscaped = securityActions.isSearchResultEscaped();
        Assert.assertTrue("Search result text not escaped, potential XSS issue!", textEscaped);
    }


}
