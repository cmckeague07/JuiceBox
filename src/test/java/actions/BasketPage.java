package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@DefaultUrl("http://localhost:3000/#/basket")
public class BasketPage extends PageObject {

    @FindBy(css = ".increase-btn")
    WebElement increaseQuantityButton;

    @FindBy(id = "checkoutButton")
    WebElementFacade checkoutButton;

    @FindBy(css = "button:has(svg[data-icon='trash-alt'])")
    WebElementFacade removeButton;

    @FindBy(css = "button[aria-label='Show the shopping cart']")
    WebElementFacade basketIcon;

    public void clickBasketIcon() {
        waitFor(basketIcon).waitUntilClickable().click();
    }

    public void clickIncreaseQuantity() {
        increaseQuantityButton.click();
    }


    public String getTotalPriceText() {
        return $(".total-price").getText();
    }


    public void clickCheckout() {
        waitABit(500);  // brief half-second buffer
        waitFor(checkoutButton).waitUntilClickable().click();
    }

    public int getBasketItemCount() {
        // Count the visible rows in the basket table
        return $$(".mat-row").size();
    }

    public void removeFirstItem() {
        waitFor(removeButton).waitUntilClickable().click();
    }
}
