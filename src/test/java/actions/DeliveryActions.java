package actions;

import net.thucydides.core.annotations.Step;
import actions.DeliveryPage;

public class DeliveryActions {
    DeliveryPage deliveryPage;

    @Step("Choose delivery option: {0}")
    public void chooseDeliveryOption(String option) {
        deliveryPage.chooseDeliveryOption(option);
    }
}
