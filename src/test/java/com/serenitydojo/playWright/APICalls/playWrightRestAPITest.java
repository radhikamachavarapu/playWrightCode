package com.serenitydojo.playWright.APICalls;
import com.microsoft.playwright.*;
import com.serenitydojo.playWright.playWrightpackage.playWrightClass;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class playWrightRestAPITest  {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;

    Page page;

    @BeforeAll
    static void setUpBrowser() {
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );
    }

    @BeforeEach
    void setUp() {
        browserContext = browser.newContext();
        page = browserContext.newPage();

        page.navigate("https://practicesoftwaretesting.com");
        page.getByPlaceholder("Search").waitFor();

    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }


    @DisplayName("When Search returns single product")
    @Test
    void singleProduct() {

        page.navigate("https://practicesoftwaretesting.com");
        page.route("**/products/search?q=pliers",
                route -> route.fulfill(new Route.FulfillOptions()
                        .setBody(MockSearchResponses.RESPONSE_WITH_A_SINGLE_ENTRY)
                        .setStatus(200))
        );

        var searchBox = page.getByPlaceholder("Search");
        searchBox.fill("pliers");
        searchBox.press("Enter");

        assertThat(page.getByTestId("product-name")).hasCount(1);
        assertThat(page.getByTestId("product-name")
                .filter(new Locator.FilterOptions().setHasText("Super Pliers")))
                .isVisible();
    }

    @DisplayName("Search returns no products")
    @Test
    void noProducts() {

        page.navigate("https://practicesoftwaretesting.com");
        page.route("**/products/search?q=NotAvailable",
                route -> route.fulfill(new Route.FulfillOptions()
                        .setBody(MockSearchResponses.RESPONSE_WITH_NO_ENTRIES)
                        .setStatus(200))
        );

        var searchBox = page.getByPlaceholder("Search");
        searchBox.fill("NotAvailable");
        searchBox.press("Enter");

        assertThat(page.getByTestId("product-name")).hasCount(0);
        assertThat(page.getByTestId("search_completed")).hasText("There are no products found.");
    }

}
