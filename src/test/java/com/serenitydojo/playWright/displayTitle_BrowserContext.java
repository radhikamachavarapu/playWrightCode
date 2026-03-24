package com.serenitydojo.playWright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class displayTitle_BrowserContext {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext browserContext;
    Page page;

    @BeforeAll
    public static void setupBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000)
                .setArgs(Arrays.asList("--disable-gpu", "--no-sandbox", "--disable-extensions")));
        browserContext= browser.newContext();
    }

    @BeforeEach
    public void setup() {
         page = browserContext.newPage();
    }

    @AfterAll
    public static void teardown() {
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
