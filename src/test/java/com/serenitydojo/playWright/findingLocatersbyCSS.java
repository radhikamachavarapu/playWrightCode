package com.serenitydojo.playWright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;

import java.util.List;

@UsePlaywright(headLessChromeOptions.class)
public class findingLocatersbyCSS {


    @DisplayName("Finding by CSS locater")
    @Nested
    class findbyCSS {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("By ID")
        @Test
        void byID(Page page) {
            page.locator("#first_name").fill("PlayWright");
            String str = page.locator("#first_name").inputValue();
            System.out.println("Value is: " + str);
            Assertions.assertTrue(str.contains("PlayWright"));
        }

        @DisplayName("By CSS class")
        @Test
        void byCSS(Page page) {
            page.locator("#first_name").fill("PlayWright");
            page.locator(".btnSubmit").click();
            List<String> list = page.locator(".alert").allTextContents();
            Assertions.assertFalse(list.isEmpty());
        }

        @DisplayName("By Attibute")
        @Test
        void byAttibute(Page page) {
            page.locator("[placeholder='Your first name *']").fill("PlayWrightTesting");
            Assertions.assertTrue(page.locator("[placeholder='Your first name *']").inputValue().contains("PlayWright"));

        }


    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/contact");

    }

}
