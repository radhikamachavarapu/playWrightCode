package com.serenitydojo.playWright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.serenitydojo.playWright.fixtures.playWrightClass;

import java.util.Arrays;

public class headLessChromeOptions extends playWrightClass implements OptionsFactory {

    @Override
    public Options getOptions() {
        return new Options()
                .setLaunchOptions(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(1000)
                        .setArgs(Arrays.asList("--disable-gpu", "--no-sandbox", "--disable-extensions")
                )).setTestIdAttribute("data-test");


    }
}
