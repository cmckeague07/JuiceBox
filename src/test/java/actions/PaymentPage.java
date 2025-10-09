package actions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PaymentPage extends PageObject {
    @FindBy(xpath = "//mat-expansion-panel-header//span[contains(text(), 'Add new card')]")
    WebElementFacade addNewCardHeader;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElementFacade nameField;

    @FindBy(xpath = "//input[@placeholder='Card Number']")
    WebElementFacade cardNumberField;

    @FindBy(xpath = "//select[@aria-label='Expiry Month']")
    WebElementFacade expiryMonthDropdown;

    @FindBy(xpath = "//select[@aria-label='Expiry Year']")
    WebElementFacade expiryYearDropdown;

    @FindBy(xpath = "//input[@placeholder='CVV']")
    WebElementFacade cvvField;

    @FindBy(xpath = "//button[@id='submitButton']")
    WebElementFacade submitBtn;


    public void clickAddNewCardBruteForce() {
        WebDriver driver = getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Try pure text-match (most reliable)
            WebElement el = driver.findElement(
                    By.xpath("//*[normalize-space(text())='Add a credit or debit card']")
            );

            js.executeScript("arguments[0].scrollIntoView(true);", el);
            js.executeScript("arguments[0].click();", el);

        } catch (Exception e1) {


            // Try partial contains() fallback
            try {
                WebElement el = driver.findElement(
                        By.xpath("//*[contains(normalize-space(text()), 'Add a credit')]")
                );
                js.executeScript("arguments[0].scrollIntoView(true);", el);
                js.executeScript("arguments[0].click();", el);
                System.out.println("Clicked via partial match fallback.");
            } catch (Exception e2) {

                throw e2;
            }
        }
    }

    public void addPaymentBruteForceSimple(String name, String cardNumber, String expMonth, String expYear) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        // Name
        WebElement nameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(.,'Name')]/following::input[1]")));
        nameInput.clear();
        nameInput.sendKeys(name);

        // Card number
        WebElement cardInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//label[contains(.,'Card Number')]/following::input[1]")));
        cardInput.clear();
        cardInput.sendKeys(cardNumber);


        try {
            WebElement monthSelect = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//label[contains(.,'Expiry Month')]/following::select[1]")));
            new Select(monthSelect).selectByVisibleText(expMonth);
        } catch (Exception e) {
            // fallback for mat-select (Angular Material)
            WebElement monthBox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[contains(.,'Expiry Month')]/following::div[contains(@class,'mat-select-trigger')][1]")));
            monthBox.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option//span[text()='" + expMonth + "']")))
                    .click();
        }

        // Expiry year - same strategy
        try {
            WebElement yearSelect = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//label[contains(.,'Expiry Year')]/following::select[1]")));
            new Select(yearSelect).selectByVisibleText(expYear);
        } catch (Exception e) {
            WebElement yearBox = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//label[contains(.,'Expiry Year')]/following::div[contains(@class,'mat-select-trigger')][1]")));
            yearBox.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-option//span[text()='" + expYear + "']")))
                    .click();
        }

        // Submit
        WebElement submitBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(.,'Submit')]")));
        submitBtn.click();
    }


    public void addPayment(String card, String expMonth, String expYear, String name) {
        clickAddNewCardBruteForce();
        addPaymentBruteForceSimple(name, card, expMonth, expYear);
    }


    public void selectLatestPaymentMethod() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);


        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@type='radio' and contains(@id,'mat-radio')]")));


        List<WebElement> radios = getDriver().findElements(
                By.xpath("//input[@type='radio' and contains(@id,'mat-radio')]"));
        if (radios.isEmpty()) {
            throw new RuntimeException("No payment radio buttons found!");
        }


        WebElement latestRadio = radios.get(radios.size() - 1);


        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", latestRadio);


        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", latestRadio);

    }

    public void continueAfterSelectingPayment() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        WebElement continueBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(.,'Continue')]")));
        continueBtn.click();
    }

}
