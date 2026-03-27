package com.serenitydojo.playWright.pageObjects;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class ProductDetails {

    private final Page page;

    public ProductDetails(Page page) {
        this.page = page;
    }

    public void incrementQuantity(int increment) {
        for (int i = 0 ; i < increment; i++) {
            page.getByTestId("increase-quantity").click();
        }
    }

    public void addToCart() {
        page.waitForResponse(
                response -> response.url().contains("/carts") && response.request().method().equals("POST"),
                () -> {
                    page.getByText("Add to cart").click();
                    page.getByRole(AriaRole.ALERT).click();
                }
        );
    }



}
