package com.serenitydojo.playWright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class displayTitle_refactor_BrowserOptions {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    void setup() {
         playwright = Playwright.create();
         browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                 .setHeadless(false)
                 .setArgs(Arrays.asList("--disable-gpu", "--no-sandbox", "--disable-extensions")));
         page = browser.newPage();
    }

    @AfterEach
    void teardown() {
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void shouldShowThePageTitle() {

        page.navigate("https://practicesoftwaretesting.com/");
        String title = page.title();
        System.out.println("Title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"));
        String url = page.url();
        System.out.println("URL is: " + url);

    }

    @Test
    void seachbyKeyword() {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();
        int matchingResults = page.locator(".card").count();
        System.out.println("Number of matching results: " + matchingResults);
        Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

    }
}
