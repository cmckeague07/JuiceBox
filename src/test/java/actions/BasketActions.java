package actions;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class BasketActions {

    @Steps
    BasketPage basketPage;

    @Step("Open the basket page")
    public void openBasketPage() {
        basketPage.clickBasketIcon(); // uses @DefaultUrl → localhost:3000/#/basket
    }

    @Step("Proceed to checkout")
    public void proceedToCheckout() {
        basketPage.clickCheckout(); // clicks the checkout button → navigates to /#/address/saved
        basketPage.waitForTextToAppear("Add New Address"); // confirm next page loaded
    }
}