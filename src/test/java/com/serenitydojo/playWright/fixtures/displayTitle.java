package com.serenitydojo.playWright.fixtures;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class displayTitle {
    @Test
    void shouldShowThePageTitle() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        String title = page.title();
        System.out.println("Title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"));
        String url = page.url();
        System.out.println("URL is: " + url);
        page.close();
        browser.close();
        playwright.close();
    }

    @Test
    void seachbyKeyword() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();
        int matchingResults = page.locator(".card").count();
        System.out.println("Number of matching results: " + matchingResults);
        Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");
        page.close();
        browser.close();
        playwright.close();
    }
}
