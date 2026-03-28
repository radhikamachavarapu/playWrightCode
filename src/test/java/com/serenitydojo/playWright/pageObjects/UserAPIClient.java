package com.serenitydojo.playWright.Login;

import com.google.gson.Gson;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import com.serenitydojo.playWright.DomainAPI.UserRecordRefactored;

public class UserAPIClient {

    private final Page page;
    private static final String REGISTER_USER_BASE_URL = "https://api.practicesoftwaretesting.com/users/register";
    Gson gson = new Gson();

    public UserAPIClient(Page page) {
        this.page = page;
    }

    public void registerUser(UserRecordRefactored.User user) {
        var response = page.request().post(REGISTER_USER_BASE_URL,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Accept", "application/json")
                        .setData(user));

        if(response.status() == 201) {
            System.out.println("User registered successfully: " + response.text());
        } else {
            System.out.println("Failed to register user. Status: " + response.status() + ", Body: " + response.text());
        }

    }

}
