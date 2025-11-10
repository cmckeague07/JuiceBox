package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class AddressPage extends PageObject {

    @FindBy(css = "button[aria-label='Add a new address']")
    WebElementFacade addAddressBtn;
    @FindBy(css = "input[placeholder='Please provide a country.']")
    WebElementFacade countryField;
    @FindBy(css = "input[placeholder='Please provide a name.']")
    WebElementFacade nameField;
    @FindBy(css = "input[placeholder='Please provide a mobile number.']")
    WebElementFacade mobileNumberField;
    @FindBy(css = "input[placeholder='Please provide a ZIP code.']")
    WebElementFacade zipCodeField;
    @FindBy(css = "textarea[placeholder='Please provide an address.']")
    WebElementFacade addressField;
    @FindBy(css = "input[placeholder='Please provide a city.']")
    WebElementFacade cityField;
    @FindBy(css = "input[placeholder='Please provide a state.']")
    WebElementFacade stateField;

    @FindBy(css = "mat-row:last-of-type mat-radio-button input[type='radio']")
    WebElementFacade latestAddressRadio;
    @FindBy(css = "button.mat-mdc-raised-button.mat-primary[aria-label*='Proceed to payment']")
    WebElementFacade continueBtn;
    @FindBy(xpath = "//button[contains(., 'Me want it!')]")
    WebElementFacade cookieAcceptBtn;

    // --- BUTTONS ---
    @FindBy(id = "submitButton")
    WebElementFacade submitBtn;


    public void addNewAddress(String country, String name, String mobile, String zip, String address, String city, String state) {
        addAddressBtn.click();
        waitFor(countryField).waitUntilVisible().type(country);
        nameField.type(name);
        mobileNumberField.type(mobile);
        zipCodeField.type(zip);
        addressField.type(address);
        cityField.type(city);
        stateField.type(state);

        // --- handle cookie popup if visible ---
        if (cookieAcceptBtn.isCurrentlyVisible()) {
            cookieAcceptBtn.click();
        }
        // submit (will be disabled until all required fields are valid)
        submitBtn.waitUntilEnabled().click();
    }

    public void selectLatestAddress() {

        try {
            // Dismiss cookie banner if visible
            WebElementFacade cookieDismiss = find(By.cssSelector("a.cc-btn.cc-dismiss"));
            if (cookieDismiss.isVisible()) {
                cookieDismiss.click();
            }
        } catch (Exception ignored) {}
        try {
            // wait for any address rows to appear
            List<WebElement> addresses = getDriver().findElements(By.cssSelector("mat-row, .mat-row"));
            if (addresses.isEmpty()) {
                return;
            }

            // grab the last one
            WebElement last = addresses.get(addresses.size() - 1);

            // scroll into view & click via JS (bypasses disabled/enabled checks)
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", last);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", last);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to click address: " + e.getMessage());
        }
        waitFor(continueBtn).waitUntilClickable().click();
    }



    public boolean isButtonEnabled(String buttonText) {
        return $(By.xpath("//span[normalize-space(text())='" + buttonText + "']/ancestor::button"))
                .waitUntilVisible()
                .isEnabled();
    }

    public boolean isAddressFormVisible() {
        return $(By.xpath("//app-address-create")).waitUntilVisible().isVisible();
    }

}

