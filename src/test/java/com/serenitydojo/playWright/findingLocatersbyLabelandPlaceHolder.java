package com.serenitydojo.playWright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

@UsePlaywright(headLessChromeOptions.class)
public class findingLocatersbyLabelandPlaceHolder {


    @DisplayName("Finding by Text")
    @Nested
    class findbyTextandTitle {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("By Label")
        @Test
        void byLabel(Page page) {
            page.getByLabel("Search").fill("pliers");
            page.locator("button:has-text('Search')").click();
            int matchingResults = page.locator(".card").count();
            System.out.println("Number of matching results: " + matchingResults);
            Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

        }

        @DisplayName("By Placeholder")
        @Test
        void byPlaceholder(Page page) {
            page.getByPlaceholder("Search").fill("pliers");
            page.locator("button:has-text('Search')").click();
            int matchingResults = page.locator(".card").count();
            System.out.println("Number of matching results: " + matchingResults);
            Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

        }


    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");

    }

}
