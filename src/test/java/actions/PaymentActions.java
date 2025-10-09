package actions;

import net.thucydides.core.annotations.Step;
import actions.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentActions {
    PaymentPage paymentPage;

    @Step("Add a new payment method")
    public void addPayment(String card, String expMonth, String expYear, String name) {
        paymentPage.addPayment(card, expMonth, expYear, name);
    }

    @Step("Select newly added payment method")
    public void selectLatestPaymentMethod() {
        paymentPage.selectLatestPaymentMethod();
        paymentPage.continueAfterSelectingPayment();

    }

}
