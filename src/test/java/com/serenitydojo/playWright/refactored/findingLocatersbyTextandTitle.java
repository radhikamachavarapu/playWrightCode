package com.serenitydojo.playWright.refactored;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playWright.headLessChromeOptions;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(headLessChromeOptions.class)
public class findingLocatersbyTextandTitle {

    @DisplayName("Finding by Text")
    @Nested
    class findbyTextandTitle {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("By Text")
        @Test
        void byText(Page page) {
            page.getByText("Bolt Cutters").click();
            PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
        }

        @DisplayName("By Alt Text")
        @Test
        void byAltText(Page page) {
            page.getByAltText("Bolt Cutters").click();
            PlaywrightAssertions.assertThat(page.getByText("ForgeFlex Tools")).isVisible();
        }

        @DisplayName("By Title")
        @Test
        void byTitle(Page page) {
            page.getByAltText("Bolt Cutters").click();
            page.getByTitle("Practice Software Testing - Toolshop").click();
        }
    }

    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com");

    }

}
