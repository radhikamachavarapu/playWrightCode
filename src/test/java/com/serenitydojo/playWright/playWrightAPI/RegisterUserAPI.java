package com.serenitydojo.playWright.playWrightAPI;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;
import com.serenitydojo.playWright.DomainAPI.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@UsePlaywright
public class RegisterUserAPI {

    private APIRequestContext request;
    Gson gson = new Gson();

    @Test
    void registerUser() {

        UserRecord.User validUser = UserRecord.User.randomUser();
        String validUserJson = gson.toJson(validUser);
        System.out.println(validUserJson);
        APIResponse response = request.post("/users/register", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(validUserJson)
        );
        System.out.println("Response status: " + response.status());
        System.out.println("Response body: " + response.text());
        System.out.println("Response status: " + response.status());
        System.out.println("Response statusText: " + response.statusText());
        Assertions.assertTrue(response.status() == 201 , "Expected status code is NOT 201");
        Assertions.assertTrue(response.ok() == true);
        Assertions.assertEquals(response.statusText(), "Created", "Expected status text is NOT 'Created'");

        UserRecord.User responseUser = gson.fromJson(response.text(), UserRecord.User.class);
        System.out.println("Registered user: " + responseUser);


    }
    @DisplayName("First name is missing")
    @Test
    void firstNameMissing() {
        UserRecord.User invalidUser = new UserRecord.User(
                null,
                "Smith",
                "(983) 782-8030",
                "1990-01-01",
                "warren.kuvalis@yahoo.com",
                "1326 Albert Viaduct",
                "North Rudolph",
                "New Mexico",
                "Grenada",
                "85364",
                "WelcomeWorld123!");

        APIResponse response = request.post("/users/register", RequestOptions.create()
                .setHeader("Content-Type", "application/json")
                .setData(invalidUser)
        );
        JsonObject responseBody = gson.fromJson(response.text(), JsonObject.class);
        System.out.println("Response status: " + response.status());
        System.out.println("Response body: " + response.text());
        System.out.println("Response status: " + response.status());
        System.out.println("Response statusText: " + responseBody);
        System.out.println("Response statusText: " + responseBody.get("first_name").getAsString());
        System.out.println("Response statusText: " + responseBody.get("email").getAsString());
        Assertions.assertTrue(response.status() == 422 , "Expected status code is NOT 422");

    }

    @BeforeEach
    void setUp(Playwright playwright) {
        request = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://api.practicesoftwaretesting.com")
        );

    }

    @AfterEach
    void tearDown() {
        if(request != null) {
            request.dispose();
        }
    }


}
