package stepdefinitions;

import actions.BasketPage;
import actions.LoginActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.Steps;
import actions.BasketApiActions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.core.Serenity.getDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

public class BasketSteps {

    @Steps
    BasketApiActions basketApi;
    BasketPage basketPage;
    LoginActions login;

    @Given("I am logged in via API")
    public void i_am_logged_in_via_api() {
        basketApi.login("juicebox.qa@test.com", "Test@1234");
    }

    @When("I add product {int} to the basket")
    public void i_add_product_to_basket(int productId) {
        basketApi.addItemToBasket(productId);
    }

    @Given("I have added an item to the basket")
    public void i_have_added_an_item_to_the_basket() {
        // Step 2️⃣ - Click the first "Add to Basket" button
        WebElement addToBasketButton = getDriver()
                .findElement(By.cssSelector("button[aria-label='Add to Basket']"));
        addToBasketButton.click();

        // Small wait to let the UI update
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        basketPage.open();

        // Find all spans in the quantity column and sum only valid integers
        List<WebElement> quantityElements = getDriver().findElements(By.cssSelector(".mat-column-quantity span"));

        int basketItemCount = quantityElements.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())   // ignore empty values
                .mapToInt(Integer::parseInt)      // safe parse
                .sum();

        System.out.println("Basket item count: " + basketItemCount);

        assertThat(basketItemCount)
                .as("Basket should contain at least one item")
                .isGreaterThan(0);
    }


    @Then("the basket should contain product {int}")
    public void basket_should_contain_product(int expectedProductId) {
        Response response = basketApi.getBasketContents();
        List<Map<String, Object>> items = response.jsonPath().getList("data.Products");

        boolean found = items.stream()
                .anyMatch(p -> ((Integer) p.get("id")) == expectedProductId);

        Assert.assertTrue("Basket should contain product ID " + expectedProductId, found);
    }

    @Given("I navigate to the basket page")
    public void i_navigate_to_the_basket_page() {
        basketPage.open();
    }

    @When("I manually remove the item from the basket")
    public void i_manually_remove_the_item_from_the_basket() {
        basketPage.removeFirstItem();
    }



    @Then("the basket should be empty")
    public void the_basket_should_be_empty() {
        Response response = basketApi.getBasketContents();
        response.then().statusCode(200);
        List<?> items = response.jsonPath().getList("data.Products");
        Assert.assertTrue("Basket is not empty!", items.isEmpty());
    }

}
