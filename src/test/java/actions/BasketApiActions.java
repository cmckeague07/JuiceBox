package actions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class BasketApiActions {

    public String token;
    public int basketId;

    public void login(String email, String password) {
        Response response = SerenityRest
                .given()
                .contentType(JSON)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post("https://juice-shop.herokuapp.com/rest/user/login");

        System.out.println("🔑 LOGIN raw response: " + response.getBody().asPrettyString());

        token = response.jsonPath().getString("authentication.token");
        basketId = response.jsonPath().getInt("authentication.bid");
    }
    public int basketItemId;
public void addItemToBasket(int productId) {
        Response response = SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON)
                .body("{\"BasketId\": " + basketId + ", \"ProductId\": " + productId + ", \"quantity\": 1}")
                .post("https://juice-shop.herokuapp.com/api/BasketItems/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        basketItemId = response.jsonPath().getInt("data.id");
    }

    public Response getBasketContents() {
        Response response = SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .get("https://juice-shop.herokuapp.com/rest/basket/" + basketId);

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

        // This is the correct ID to use in the DELETE request
        Integer basketItemId = (Integer) items.get(0).get("BasketItem.id");
        if (basketItemId == null) {
            throw new RuntimeException("BasketItem ID is missing from the first basket item.");
        }

        SerenityRest
                .given()
                .header("Authorization", "Bearer " + token)
                .delete("https://juice-shop.herokuapp.com/api/BasketItems/" + basketItemId)
                .then()
                .statusCode(200);
    }









}
