package com.serenitydojo.playWright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

public class Locaters {

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
        playwright.selectors().setTestIdAttribute("data-test");
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
        page.getByPlaceholder("Search").fill("pliers");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
        int matchingResults = page.locator(".card").count();
        assertThat(page.locator(".card")).hasCount(4);
        System.out.println("Number of matching results: " + matchingResults);
        Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

        List<String> products = page.getByTestId("product-name").allTextContents();
        Assertions.assertTrue(products.stream().allMatch(name -> name.toLowerCase().contains("pliers")));

        Locator outofStock = page.locator(".card")
                                 .filter(new Locator.FilterOptions().setHasText("Out of stock"))
                                 .getByTestId("product-name");

        assertThat(outofStock).hasCount(1);
        assertThat(outofStock).hasText(" Long Nose Pliers ");


    }

}
