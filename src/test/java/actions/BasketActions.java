package actions;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class BasketActions {

    @Steps
    BasketPage basketPage;


    public void openBasketPage() {
        basketPage.clickBasketIcon();
    }

    public void proceedToCheckout() {
        basketPage.clickCheckout();
        basketPage.waitForTextToAppear("Add New Address");
    }
}