package com.serenitydojo.playWright.fixtures.locaters;

import com.serenitydojo.playWright.fixtures.playWrightClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class displayTitle_refactor extends playWrightClass {

    @Test
    void shouldShowThePageTitle() {

        page.navigate("https://practicesoftwaretesting.com/");
        String title = page.title();
        System.out.println("Title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"));
        String url = page.url();
        System.out.println("URL is: " + url);

    }

    @Test
    void seachbyKeyword() {
        page.navigate("https://practicesoftwaretesting.com/");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();
        int matchingResults = page.locator(".card").count();
        System.out.println("Number of matching results: " + matchingResults);
        Assertions.assertTrue(matchingResults > 0, "Expected to find at least one search result");

    }
}
