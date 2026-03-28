package com.serenitydojo.playWright.fixtures.locaters;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.serenitydojo.playWright.playWrightpackage.playWrightClass;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
public class waits extends playWrightClass {

    @DisplayName("Wait by product names")
    @Test
    void waitfunction() {

        page.navigate("https://practicesoftwaretesting.com");
        playwright.selectors().setTestIdAttribute("data-test");
        page.waitForSelector("[data-test='product-name']");
        List<String> productsList = page.getByTestId("product-name").allInnerTexts();
        System.out.println("Products: " + productsList);
        assertThat(productsList).contains("Pliers");

    }

    @DisplayName("Wait by images")
    @Test
    void waitfunctionWithImage() {

        page.navigate("https://practicesoftwaretesting.com");
        page.waitForSelector(".card-img-top");
        List<String> productImageTitle = page.locator(".card-img-top")
                .all()
                .stream()
                .map(imp->imp.getAttribute("alt"))
                .toList();

        System.out.println("Products: " + productImageTitle);
        assertThat(productImageTitle).contains("Pliers");
    }

    @DisplayName("Checkbox wait")
    @Test
    void checkbox() {
        page.navigate("https://practicesoftwaretesting.com");
        var  Screwdriver = page.getByLabel("Screwdriver");
        Screwdriver.click();
        assertThat(Screwdriver).isChecked();
        assertThat(Screwdriver).isVisible();
    }

    @DisplayName("Selector wait")
    @Test
    void selectorWait() {

        playwright.selectors().setTestIdAttribute("data-test");
        page.navigate("https://practicesoftwaretesting.com");
        page.getByRole(AriaRole.MENUBAR).getByText("Categories").click();
        page.getByRole(AriaRole.MENUBAR).getByText("Power Tools").click();

        page.waitForSelector(".card", new Page.WaitForSelectorOptions().setTimeout(2000));

        var productNames = page.getByTestId("product-name").allInnerTexts();
        System.out.println("Products: " + productNames);
        assertThat(productNames).contains("Belt Sander", "Sheet Sander");
    }

    @DisplayName("Add items to cart")
    @Test
    void addItemtoCart() {

        playwright.selectors().setTestIdAttribute("data-test");
        page.navigate("https://practicesoftwaretesting.com");
        page.getByText("Bolt Cutters").first().click();
        page.getByText("Add to cart").click();
        assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
        assertThat(page.getByRole(AriaRole.ALERT)).hasText("Product added to shopping cart.");

        page.waitForCondition(() -> page.getByRole(AriaRole.ALERT).isHidden());
    }

    @DisplayName("Add cart item count")
    @Test
    void updateCartItemCount() {

        playwright.selectors().setTestIdAttribute("data-test");
        page.navigate("https://practicesoftwaretesting.com");
        page.getByText("Bolt cutter").click();
        page.getByText("Add to cart").click();
        //page.waitForCondition(() -> page.getByTestId("cart-quantity").count() > 0);
        page.waitForSelector("[data-test='cart-quantity']:has-text('1')");
        int ct = page.getByTestId("cart-quantity").count();
        System.out.println("Cart item count: " + ct);
        assertThat(ct).isEqualTo(1);
    }

    @DisplayName("sort by decending price")
    @Test
    void priceDecending() {

        playwright.selectors().setTestIdAttribute("data-test");
        page.navigate("https://practicesoftwaretesting.com");
        page.getByLabel("sort").selectOption("Price (High - Low)");

        var productPrices = page.getByTestId("product-price").allInnerTexts()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();

        System.out.println("Products: " + productPrices);
        assertThat(productPrices)
                .isNotEmpty()
                .isSortedAccordingTo(Comparator.reverseOrder());


    }




}
