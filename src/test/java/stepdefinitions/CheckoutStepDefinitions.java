package stepdefinitions;


import actions.*;
import net.thucydides.core.annotations.Steps;
import io.cucumber.java.en.*;

public class CheckoutStepDefinitions {

    @Steps BasketActions basket;
    @Steps AddressActions address;
    @Steps DeliveryActions delivery;
    @Steps PaymentActions payment;
    @Steps ConfirmationActions confirmation;

    @When("I open the basket")
    public void iOpenTheBasket() {
        basket.openBasketPage();   // goes to /#/basket
    }
    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        basket.proceedToCheckout();   // clicks checkout button → /#/address/saved
    }

    @When("I add a new address with valid details")
    public void iAddNewAddress() {
        address.addNewAddress(
                "Ireland",
                "John Doe",
                "0851234567",
                "D02AB12",
                "123 Test Street",
                "Dublin",
                "Leinster");
    }

    @When("I select the newly added address")
    public void iSelectAddress() {
        address.selectLatestAddress();
    }

    @When("I choose {string} as my delivery option")
    public void iChooseDeliveryOption(String option) {
        delivery.chooseDeliveryOption(option);
    }

    @When("I add a new payment method with valid details")
    public void iAddPaymentMethod() {
        payment.addPayment("4111111111111111", "12", "2089", "John Doe");
    }


    @When("I select the newly added payment method")
    public void iSelectPaymentMethod() {
        payment.selectLatestPaymentMethod();
    }


    @When("I place the order")
    public void iPlaceTheOrder() {
        confirmation.placeOrder();
    }

    @Then("I should see an order confirmation page")
    public void iShouldSeeOrderConfirmationPage() {
        confirmation.shouldSeeConfirmation();
    }

    @Then("I should be able to download the order confirmation")
    public void iShouldDownloadConfirmation() throws InterruptedException {
        confirmation.downloadOrderConfirmation();
    }



  }