package actions;

import net.thucydides.core.annotations.Step;
import actions.PaymentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentActions {
    PaymentPage paymentPage;


    public void addPayment(String card, String expMonth, String expYear, String name) {
        paymentPage.addPayment(card, expMonth, expYear, name);
    }

    public void selectLatestPaymentMethod() {
        paymentPage.selectLatestPaymentMethod();
        paymentPage.continueAfterSelectingPayment();

    }

}
