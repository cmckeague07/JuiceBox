package actions;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DeliveryPage extends PageObject {
    // Each option found by its visible text in the mat-cell
    @FindBy(xpath = "//mat-cell[contains(text(),'One Day Delivery')]")
    WebElementFacade oneDayOption;

    @FindBy(xpath = "//mat-cell[contains(text(),'Fast Delivery')]")
    WebElementFacade fastOption;

    @FindBy(xpath = "//mat-cell[contains(text(),'Standard Delivery')]")
    WebElementFacade standardOption;

    @FindBy(xpath = "//span[contains(text(),'Continue')]/ancestor::button")
    WebElementFacade continueBtn;

    public void chooseDeliveryOption(String option) {
        switch (option.toLowerCase()) {
            case "one day":
            case "one day delivery":
                waitFor(oneDayOption).waitUntilClickable().click();
                break;
            case "fast":
            case "fast delivery":
                waitFor(fastOption).waitUntilClickable().click();
                break;
            case "standard":
            case "standard delivery":
                waitFor(standardOption).waitUntilClickable().click();
                break;
            default:
                throw new IllegalArgumentException("Invalid delivery option: " + option);
        }

        waitFor(continueBtn).waitUntilClickable().click();
    }
}
