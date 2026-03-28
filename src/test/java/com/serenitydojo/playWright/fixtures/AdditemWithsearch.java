package com.serenitydojo.playWright.fixtures;

import com.google.errorprone.annotations.DoNotMock;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playWright.pageObjects.*;
import com.serenitydojo.playWright.playWrightpackage.playWrightClass;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.util.List;

public class AdditemWithsearch extends playWrightClass {

    SearchComponent searchComponent;
    ProductList productList;
    ProductDetails productDetails;
    NavBar navBar;
    CheckoutCart checkoutCart;

    @DisplayName("Search for products using the search box")
    @Test
    void seachProducts() throws InterruptedException {

        searchComponent.searchBy("pliers");
        productList.viewProductDetail("Combination Pliers");
        productDetails.incrementQuantity(2);
        productDetails.addToCart();
        navBar.openCart();

        List<CartLineItem> lineItems = checkoutCart.getLineItems();

        Assertions.assertThat(lineItems)
                .hasSize(1)
                .first()
                .satisfies(item -> {
                    Assertions.assertThat(item.title()).contains("Combination Pliers");
                    Assertions.assertThat(item.quantity()).isEqualTo(3);
                    Assertions.assertThat(item.total()).isEqualTo(item.quantity() * item.price());
                });
    }

    @DisplayName("Not a matching producet should show no results")
    @Test
    void searchForNonExistingProduct() {
        searchComponent.searchBy("NotAvailable");
        page.route("**/products/search/?q=NotAvailable",
                route -> route.fulfill(new Route.FulfillOptions()
                        .setStatus(200)
                        .setBody(DoNotMock.class.getResourceAsStream("/empty-search-response.json").toString())));

        productList.getProductNames();
        System.out.println(productList.getSearchCompletedMessage());
        System.out.println(productList.getProductNames());
        Assertions.assertThat(productList.getProductNames()).isEmpty();
        Assertions.assertThat(productList.getSearchCompletedMessage()).contains("There are no products found.");
    }

    @DisplayName("Clear Search")
    @Test
    void clearSearch() {
        SearchComponent searchComponent = new SearchComponent(page);
        ProductList productList = new ProductList(page);
        searchComponent.searchBy("saw");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector(".card", new Page.WaitForSelectorOptions().setTimeout(2000));
        var matchingFilteredProducts = productList.getProductNames();
        Assertions.assertThat(matchingFilteredProducts).hasSize(2);

        searchComponent.clearSearch();

        var matchingProducts = productList.getProductNames();
        Assertions.assertThat(matchingProducts).hasSize(9);

    }


    @BeforeEach
    public void openPage() {
        page.navigate("https://practicesoftwaretesting.com");
    }

    @BeforeEach
    public void setTrace() {
        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );
    }

    @BeforeEach
    void seup() {
        searchComponent = new SearchComponent(page);
        productList = new ProductList(page);
        productDetails = new ProductDetails(page);
        navBar = new NavBar(page);
        checkoutCart = new CheckoutCart(page);
    }

    @AfterEach
    void recordTRace(TestInfo testInfo) {
        String traceName = testInfo.getDisplayName().replaceAll(" ", "_").toLowerCase();
        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target/traces/trace-"+traceName+".zip"))
        );
    }

}

