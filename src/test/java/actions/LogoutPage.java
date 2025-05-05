package actions;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LogoutPage extends PageObject {

    @FindBy(id = "navbarAccount")
    WebElementFacade accountButton;

    @FindBy(id = "navbarLogoutButton")
    WebElementFacade logoutButton;

    @FindBy(id = "navbarLoginButton")
    WebElementFacade loginButton;

    public void clickLogout() {
        accountButton.click();
        logoutButton.click();
    }

    public boolean isLoginButtonVisible(){
        accountButton.click();
        return loginButton.isDisplayed();
    }


}