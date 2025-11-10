package actions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ValidationActions extends PageObject {
    @FindBy(css = "button[aria-label='Show the shopping cart']")
    WebElementFacade basketIcon;
    @FindBy(id = "checkoutButton")
    WebElementFacade checkoutButton;
    @FindBy(id = "submitButton")
    WebElementFacade submitBtn;
    @FindBy(css = "button[aria-label='Add a new address']")
    WebElementFacade addAddressBtn;
    @FindBy(css = "span.fa-layers-counter.fa-layers-top-right.fa-3x.warn-notification")
    WebElementFacade basketCountBadge;
    AddressActions address = new AddressActions();


    public void navigateToAddressForm() {
        openUrl("http://localhost:3000/#/address/saved");
        addAddressBtn.click();

    }

    public void leaveRequiredFieldBlank(String fieldName) {
        if (fieldName.equalsIgnoreCase("country")) {
            WebElement input = $(By.xpath("//input[@placeholder='Please provide a country.']"))
                    .waitUntilVisible();
            input.clear(); // ensures it’s blank
        }
    }

    public void clickButton(String buttonText) {
        // submit (will be disabled until all required fields are valid)
        submitBtn.waitUntilEnabled().click();
    }

    public int getBasketCount() {
        // Wait a moment for badge update, then read text
        String text = basketCountBadge.waitUntilVisible().getText().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }


    public void addItemToBasket() {
        $(By.cssSelector(".mat-grid-tile button")).click(); // click first 'Add to Basket'
    }

    public void navigateToCheckout() {
        waitFor(basketIcon).waitUntilClickable().click();
        waitABit(500);  // brief half-second buffer
        waitFor(checkoutButton).waitUntilClickable().click();
    }

    public void refreshCheckoutPage() {
        getDriver().navigate().refresh();
    }

    public boolean isBasketPersistedAfterRefresh() {
        return $(By.cssSelector(".mat-table .mat-row")).isPresent();
    }



}
