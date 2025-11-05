package actions;

import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class SecurityHeaderActions {

    private static final String BASE_URL = "http://localhost:3000"; // update if different

    /**
     * Send a GET request to the Juice Shop home page
     */
    @Step("Send GET request to the home page")
    public void sendGetRequestToHomePage() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(BASE_URL).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send request to " + BASE_URL + ": " + e.getMessage(), e);
        }
    }

    /**
     * Verify that a security header is present in the last response
     */
    public boolean verifyHeaderPresent(String headerName) {
        Map<String, String> headers = getResponseHeaders("http://localhost:3000");
        return headers.containsKey(headerName);

    }

    private Map<String, String> getResponseHeaders(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();

            Map<String, String> headers = connection.getHeaderFields().entrySet().stream()
                    .filter(e -> e.getKey() != null)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> String.join(",", e.getValue())
                    ));

            connection.disconnect();
            return headers;
        } catch (Exception e) {
            throw new RuntimeException("Could not fetch response headers: " + e.getMessage(), e);
        }
    }
}
