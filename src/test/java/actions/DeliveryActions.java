package actions;

import net.thucydides.core.annotations.Step;
import actions.DeliveryPage;

public class DeliveryActions {
    DeliveryPage deliveryPage;

    public void chooseDeliveryOption(String option) {
        deliveryPage.chooseDeliveryOption(option);
    }
}
