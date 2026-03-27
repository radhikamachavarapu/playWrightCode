package com.serenitydojo.playWright.refactored;

import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playWright.headLessChromeOptions;
import org.junit.jupiter.api.*;

@UsePlaywright(headLessChromeOptions.class)
public class displayTitle_usingPlayWrightAnnotation {
    
    @Test
    void shouldShowThePageTitle(Page page) {

        page.navigate("https://practicesoftwaretesting.com/");
        String title = page.title();
        System.out.println("Title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"));
        String url = page.url();
        System.out.println("URL is: " + url);

    }
    @Test
    void seachbyKeyword(Page page) {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();
        int matchingResults = page.locator(".card").count();
        System.out.println("Number of matching results: " + matchingResults);
        Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

    }
}
