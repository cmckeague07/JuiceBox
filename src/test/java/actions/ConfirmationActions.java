package actions;

import net.thucydides.core.annotations.Step;
import actions.ConfirmationPage;

public class ConfirmationActions {
    ConfirmationPage confirmationPage;

    @Step("Place the order")
    public void placeOrder() {
        confirmationPage.placeOrder();
    }

    @Step("Verify order confirmation page is visible")
    public void shouldSeeConfirmation() {
        confirmationPage.shouldSeeConfirmation();
    }

    @Step("Download the order confirmation")
    public void downloadOrderConfirmation() throws InterruptedException {
        confirmationPage.downloadOrderConfirmation();
    }

}
