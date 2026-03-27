package com.serenitydojo.playWright.playWrightAPI;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
public class APIcall  {

    record Product(String name, double price) {}
    private static Playwright playwright;
    private static APIRequestContext request;
    private static final Gson gson = new Gson();

    @DisplayName("API call to get products in page 2")
    @ParameterizedTest(name = "Checking product: {0}")
    @MethodSource("provideProducts")
    void APIcall(Product product) {
        System.out.println("product is " + product);
        System.out.println("Product Name: " + product.name() + ", Price: " + product.price());
        assertThat(product.name()).isNotEmpty();
        assertThat(product.price()).isGreaterThan(0);
    }

    static Stream<Product> provideProducts() {

        APIResponse response = request.get("/products?page=2");
        assertThat(response.status()).isEqualTo(200);
        JsonObject jsonObject = gson.fromJson(response.text(), JsonObject.class);
        JsonArray data = jsonObject.getAsJsonArray("data");

        return data.asList().stream()
                .map(jsonElement -> {
                    JsonObject productJson= jsonElement.getAsJsonObject();
                    return new Product(
                            productJson.get("name").getAsString(),
                            productJson.get("price").getAsDouble()
                    );
                });
     }


    @BeforeAll
    static void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://api.practicesoftwaretesting.com")
                        .setExtraHTTPHeaders(new HashMap<>(){{
                            put("Accept","application/json");
                        }})

        );
    }

    @AfterAll
    static void tearDown() {
        if (request != null) {
            request.dispose();
        }
        if (playwright != null) {
            playwright.close();
        }
    }



}
