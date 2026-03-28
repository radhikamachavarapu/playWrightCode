package com.serenitydojo.playWright.playWrightpackage;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public abstract class playWrightClass {
    public static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;

    protected static Page page;


    @BeforeAll
    public static void setupBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000)
                .setArgs(Arrays.asList("--disable-gpu", "--no-sandbox", "--disable-extensions")));
        browserContext= browser.newContext();
        playwright.selectors().setTestIdAttribute("data-test");
    }

    @BeforeEach
    void setUpBrowserContext() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    public static void teardown() {
        browser.close();
        playwright.close();
    }

}
