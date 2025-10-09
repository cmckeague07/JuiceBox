package actions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static net.serenitybdd.rest.RestRequests.given;

public class BasketApiActions {

    public String token;
    public int basketId;
    public int basketItemId;

    // Base URL for local Juice Shop
    private static final String BASE_URL = "http://localhost:3000";

    public void login(String email, String password) {
        Response response = SerenityRest
                .given()
                .contentType(JSON)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post("http://localhost:3000/rest/user/login");

        System.out.println("LOGIN raw response: " + response.getBody().asPrettyString());

        token = response.jsonPath().getString("authentication.token");
        basketId = response.jsonPath().getInt("authentication.bid");
    }

    public void addItemToBasket(int productId) {
        Response response = SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body("{\"BasketId\": " + basketId + ", \"ProductId\": " + productId + ", \"quantity\": 1}")
                .post("http://localhost:3000/api/BasketItems/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        basketItemId = response.jsonPath().getInt("data.id");
    }

    @Step("Get all products")
    public List<Map<String, Object>> getProducts() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:3000/api/Products")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Fetched products, count: " + response.jsonPath().getList("data").size());
        return response.jsonPath().getList("data");
    }


    public Response getBasketContents() {
        Response response = SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .get("http://localhost:3000/rest/basket/" + basketId);

        System.out.println("Basket GET Status: " + response.statusCode());
        System.out.println("Basket response:\n" + response.getBody().asPrettyString());

        return response;
    }

    public void removeItemFromBasket() {
        Response response = getBasketContents();
        List<Map<String, Object>> items = response.jsonPath().getList("data.Products");

        if (items == null || items.isEmpty()) {
            throw new RuntimeException("No items in basket to remove.");
        }

        Integer basketItemId = (Integer) items.get(0).get("BasketItem.id");
        if (basketItemId == null) {
            throw new RuntimeException("BasketItem ID is missing from the first basket item.");
        }

        SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .delete("http://localhost:3000/api/BasketItems/" + basketItemId)
                .then()
                .statusCode(200);
    }
}
