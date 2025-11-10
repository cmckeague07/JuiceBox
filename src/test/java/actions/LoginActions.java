package actions;

import com.google.inject.Inject;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class LoginActions extends UIInteractionSteps {
    String username = ConfigReader.get("login.username");
    String password = ConfigReader.get("login.password");
    String question = ConfigReader.get("login.securityQuestion");
    String answer = ConfigReader.get("login.securityAnswer");

    @Inject
    LoginPage loginPage;

    public void navigateToLoginPage() {
        loginPage.open();
    }

    public void dismissPopupIfPresent() {
        try {
            WebElement closePopup = $(By.cssSelector("button[aria-label='Close Welcome Banner']"));
            if (closePopup.isDisplayed()) {
                closePopup.click();
            }
        } catch (NoSuchElementException ignored) {

        }
    }

    public void enterEmail() {
        $(By.id("email")).type(username);
    }

    public void enterPassword() {
        $(By.id("password")).type(password);
    }


    public void clickLogin() {
        WebElementFacade loginButton = $(By.id("loginButton"));
        if (loginButton.isEnabled()) {
            loginButton.click();
        } else {
            System.out.println("Login button is disabled - skipping click.");
        }
    }


    public void verifySuccessfulLogin() {
        String currentUrl = getDriver().getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);

        Assert.assertFalse("Still on login page after login attempt!",
                currentUrl.contains("/#/login"));

        Assert.assertTrue("User not redirected to home page!",
                currentUrl.contains("/#/search"));
    }



    public void enterEmailDT(String email) {
        $(By.id("email")).type(email);
    }


    public void enterPasswordDT(String password) {
        $(By.id("password")).type(password);
    }

    public boolean isLoginButtonEnabled() {
        return $(By.id("loginButton")).isEnabled();
    }


    public void markAsPassedWithoutExecution() {
        // Nothing needed here
    }

}

