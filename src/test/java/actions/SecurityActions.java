package actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecurityActions extends PageObject {

    // Use your local Juice Shop instance
    private final String localUrl = "http://localhost:3000";

    public void openJuiceShop() {
        getDriver().get(localUrl);
    }

    public void clearCookies() {
        getDriver().manage().deleteAllCookies();
        try {
            WebElement closePopup = $(By.cssSelector("button[aria-label='Close Welcome Banner']"));
            if (closePopup.isDisplayed()) {
                closePopup.click();
            }
        } catch (NoSuchElementException ignored) {

        }
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public boolean verifyUserStillLoggedIn() {
        // Wait for 'Your Basket' element to become visible
        WebElementFacade basket = $(By.xpath("//*[contains(text(),'Your Basket')]"))
                .waitUntilVisible();


        // Return true if element is visible, false otherwise
        return basket.isVisible();
    }

    public void navigateToHomePage() {
        getDriver().get("http://localhost:3000/#/");
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().refresh();
        try {
            WebElement closePopup = $(By.cssSelector("button[aria-label='Close Welcome Banner']"));
            if (closePopup.isDisplayed()) {
                closePopup.click();
            }
        } catch (NoSuchElementException ignored) {

        }
    }

    public void performSearch(String query) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

         WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("mat-icon.mat-search_icon-search")));
        searchIcon.click();

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[id^='mat-input-']")));

        searchInput.click();

        searchInput.sendKeys(query);

        searchInput.sendKeys(Keys.ENTER);
    }



    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isSearchResultEscaped() {
        // Example: Ensure raw "<script>" tags do NOT appear in rendered results
        String pageSource = getDriver().getPageSource().toLowerCase();
        return !pageSource.contains("<script>") && pageSource.contains("&lt;script&gt;");
    }








}