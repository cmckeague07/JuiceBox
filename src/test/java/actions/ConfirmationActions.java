package actions;

import net.thucydides.core.annotations.Step;
import actions.ConfirmationPage;

public class ConfirmationActions {
    ConfirmationPage confirmationPage;


    public void placeOrder() {
        confirmationPage.placeOrder();
    }


    public void shouldSeeConfirmation() {
        confirmationPage.shouldSeeConfirmation();
    }


    public void downloadOrderConfirmation() throws InterruptedException {
        confirmationPage.downloadOrderConfirmation();
    }

}
