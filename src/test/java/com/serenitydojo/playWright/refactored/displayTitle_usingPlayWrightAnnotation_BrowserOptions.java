package com.serenitydojo.playWright.refactored;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@UsePlaywright(displayTitle_usingPlayWrightAnnotation_BrowserOptions.MyOptions.class)
public class displayTitle_usingPlayWrightAnnotation_BrowserOptions {

    public static class MyOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(new BrowserType.LaunchOptions()
                            .setArgs(Arrays.asList("--disable-gpu", "--no-sandbox", "--disable-extensions")));

        }
    }
    
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
