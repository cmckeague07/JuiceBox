package stepdefinitions;

import actions.AddressPage;
import actions.BasketApiActions;
import actions.BasketPage;
import actions.ValidationActions;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationStepDefinitions {

    @Steps
    ValidationActions validationActions;
    AddressPage addressPage;
    BasketPage basketPage;

    int initialItemCount;
    int postAddItemCount;


    @FindBy(id = "submitButton")
    WebElementFacade submitBtn;

    @Given("the user is on the address creation page")
    public void userIsOnAddressCreationPage() {
        validationActions.navigateToAddressForm();
    }

    @When("the user leaves the required {string} field blank")
    public void userLeavesRequiredFieldBlank(String fieldName) {
        validationActions.leaveRequiredFieldBlank(fieldName);
    }

    @When("clicks the {string} button")
    public void userClicksButton(String buttonText) {
        validationActions.clickButton(buttonText);
    }


    @Then("the {string} button should remain disabled")
    public void buttonShouldRemainDisabled(String buttonText) {
        assertThat(addressPage.isButtonEnabled(buttonText)).isFalse();
    }

    @Then("the address form should stay open")
    public void formShouldStayOpen() {
        assertThat(addressPage.isAddressFormVisible())
                .as("Expected the address form to remain open while required fields are missing")
                .isTrue();
    }

    @Given("the user adds an item to the basket")
    public void userAddsItemToBasket() throws InterruptedException {
        // Capture basket count before
        initialItemCount = validationActions.getBasketCount();

        validationActions.addItemToBasket();
        Thread.sleep(1000); // 1 second
        postAddItemCount = validationActions.getBasketCount();

        // Validate increased by 1
        assertThat(postAddItemCount).isEqualTo(initialItemCount + 1);

    }

    @Given("navigates to the checkout page")
    public void navigatesToCheckoutPage() {
        validationActions.navigateToCheckout();
    }

    @Then("the basket should still contain the previously added item")
    public void basketShouldPersistAfterRefresh() {
        int refreshedCount = validationActions.getBasketCount();

        // Assert persisted
        assertThat(refreshedCount).isEqualTo(initialItemCount + 1);

    }

    @And("the basket is cleared down")
    public void theBasketisClearedDown(){
        basketPage.removeFirstItem();
    }


}
