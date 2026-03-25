package com.serenitydojo.playWright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

@UsePlaywright(headLessChromeOptions.class)
public class findingLocatersbyRole {


    @DisplayName("Finding by Text")
    @Nested
    class findbyTextandTitle {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("By Role")
        @Test
        void byLabel(Page page) {
            page.locator("[placeholder=Search]").fill("pliers");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search")).click();
             int matchingResults = page.locator(".card").count();
             System.out.println("Number of matching results: " + matchingResults);

        }

    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");

    }

}
