package com.serenitydojo.playWright.pageObjects;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public class ProductList {

    private final Page page;

    public ProductList(Page page) {
        this.page = page;
    }

    public List<String> getProductNames() {
        return page.getByTestId(".product-name").allInnerTexts();
    }

    public void viewProductDetail(String productName) {
        page.locator(".card").getByText(productName).click();
    }

    public String getSearchCompletedMessage() {
        return page.getByTestId("search_completed").textContent();
    }

}
