package com.serenitydojo.playWright.refactored;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.LoadState;
import com.serenitydojo.playWright.headLessChromeOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(headLessChromeOptions.class)
public class assettions {


    @DisplayName("Assertions")
    @Nested
    class Assertions {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("Assert Types")
        @Test
        void assertTypes(Page page) {
           page.waitForCondition(() -> page.getByTestId("product-name").count() > 0);

            List<Double> prices = page.getByTestId("product-price").allInnerTexts()
                    .stream()
                    .map(price -> Double.parseDouble(price.replace("$", "")))
                    .toList();

            assertThat(prices)
                    .isNotEmpty()
                    .allMatch(price -> price > 0.0)
                    .doesNotContain(0.0)
                    .allMatch(price -> price < 1000.0)
                    .allSatisfy(price -> assertThat(price).isGreaterThan(0.0).isLessThan(100.0)) ;
        }

        @DisplayName("Assert Alphabetically")
        @Test
        void assertTypes2(Page page) {

            page.getByLabel("sort").selectOption("Name (A - Z)");
            page.waitForLoadState(LoadState.NETWORKIDLE);

            List<String> productNames = page.getByTestId("product-name").allInnerTexts();

            assertThat(productNames)
                    .isNotEmpty()
                    .isSorted()
                    .isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER)
                    .allSatisfy(name -> assertThat(name).isNotBlank().hasSizeGreaterThan(5));
        }

        @DisplayName("Assert Alphabetically in Reverse")
        @Test
        void assertTypes3(Page page) {

            page.getByLabel("sort").selectOption("Name (Z - A)");
            page.waitForLoadState(LoadState.NETWORKIDLE);

            List<String> productNames = page.getByTestId("product-name").allInnerTexts();

            assertThat(productNames)
                    .isNotEmpty()
                    .isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER.reversed())
                    .allSatisfy(name -> assertThat(name).isNotBlank().hasSizeGreaterThan(5));
        }

    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com");

    }

}
