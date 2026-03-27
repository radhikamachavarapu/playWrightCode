package com.serenitydojo.playWright.fixtures.locaters;

import com.serenitydojo.playWright.fixtures.playWrightClass;
import org.junit.jupiter.api.*;

public class findingLocatersbyTestId extends playWrightClass {

    @Test
    void byTestId() {
        page.navigate("https://practicesoftwaretesting.com/");
        playwright.selectors().setTestIdAttribute("data-test");
        page.getByTestId("search-query").fill("pliers");
        page.getByTestId("search-submit").click();
        String title = page.title();
        System.out.println("Title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"));
        String url = page.url();
        System.out.println("URL is: " + url);

    }


}
