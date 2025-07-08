package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@DefaultUrl("https://juice-shop.herokuapp.com/#/basket")
public class BasketPage extends PageObject {

    @FindBy(css = ".increase-btn")
    WebElement increaseQuantityButton;

    public void clickIncreaseQuantity() {
        increaseQuantityButton.click();
    }


    public String getTotalPriceText() {
        return $(".total-price").getText();
    }

    @FindBy(css = "button:has(svg[data-icon='trash-alt'])")
    WebElementFacade removeButton;




    public void removeFirstItem() {
        waitFor(removeButton).waitUntilClickable();
        removeButton.click();
    }





}
