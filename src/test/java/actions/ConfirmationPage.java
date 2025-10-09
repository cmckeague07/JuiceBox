package actions;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfirmationPage extends PageObject {
    @FindBy(css=".order-details") WebElementFacade orderDetails;

    public void placeOrder() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement placeOrderBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(.,'Place your order and pay')]")));
        placeOrderBtn.click();
    }

    public void shouldSeeConfirmation() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);


        By thankYouLocator = (By) By.xpath("//*[contains(text(),'Thank you for your purchase')]");
        WebElement thankYouMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouLocator));
        assertThat(thankYouMsg.isDisplayed()).as("Thank you message should be visible").isTrue();

    }


    public void downloadOrderConfirmation() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        // Wait for the 'Print order confirmation' button to be clickable
        WebElement downloadBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@aria-label,'Print order confirmation')]")
        ));

        downloadBtn.click();

        // Optional: verify file was triggered (basic wait for download popup or page stability)
        System.out.println("Order confirmation download triggered successfully.");
    }



}
