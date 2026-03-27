package com.serenitydojo.playWright.Login;
import com.serenitydojo.playWright.DomainAPI.UserRecordRefactored;
import com.serenitydojo.playWright.fixtures.playWrightClass;
import com.serenitydojo.playWright.playWrightAPI.RegisterUserAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

public class loginWithRegiteredUser extends playWrightClass {

    @DisplayName("Login with registered user")
    @Test
    void loginWithRegisteredUser() {

        UserRecordRefactored.User user = UserRecordRefactored.User.randomUser();
        UserAPIClient userAPIClient = new UserAPIClient(page);
        userAPIClient.registerUser(user);
        System.out.println("Registered user: " + user.email() + " with password: " + user.password());
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.loginAsUsser(user);
        System.out.println("Logged in with user: " + user.email());
        System.out.println("Page title after login: " + loginPage.title());
        Assertions.assertEquals(loginPage.title().equals("My account"), true);

    }

    @DisplayName("Should reject user with invalid password")
    @Test
    void invalidPassword() {

        UserRecordRefactored.User user = UserRecordRefactored.User.randomUser();
        UserAPIClient userAPIClient = new UserAPIClient(page);
        userAPIClient.registerUser(user);
        System.out.println("Registered user: " + user.email() + " with password: " + user.password());

        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.loginwithIncorrectPassword(user.withPassword("incorrectPassword"));
        System.out.println("User detaails: " + user);
        System.out.println("Attempted login with user: " + user.email() + " and "+user.withPassword("SomePassword"));
        System.out.println("Logged in with email: " + user.email());
        System.out.println("Logged in with password: " + user.withPassword("SomePassword"));

        Assertions.assertEquals(loginPage.loginErrorMessage().equals("Invalid email or password"), true);

    }

}
