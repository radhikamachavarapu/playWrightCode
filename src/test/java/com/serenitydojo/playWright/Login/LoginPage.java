package com.serenitydojo.playWright.Login;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playWright.DomainAPI.UserRecord;
import com.serenitydojo.playWright.DomainAPI.UserRecordRefactored;
import com.microsoft.playwright.junit.UsePlaywright;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://practicesoftwaretesting.com/auth/login");
    }

    public void loginAsUsser(UserRecordRefactored.User user) {
        page.getByPlaceholder("Your email").fill(user.email());
        page.getByPlaceholder("Your password").fill(user.password());
        page.getByTestId("login-submit").click();
        //page.getByRole(AriaRole.BUTTON,
        //        new Page.GetByRoleOptions().setName("Login")).click();

    }

    public void loginwithIncorrectPassword(UserRecordRefactored.User user) {
        page.getByPlaceholder("Your email").fill(user.email());
        page.getByPlaceholder("Your password").fill(user.password());
        page.getByTestId("login-submit").click();

    }

    public String title() {
        return page.getByTestId("page-title").textContent();
    }

    public String loginErrorMessage() {
        return page.getByTestId("login-error").textContent();
    }
}
