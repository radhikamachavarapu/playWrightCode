package cucumber.stepdefinations;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class playWrightCucumberFixtures {

        private static ThreadLocal<Playwright> playwright
                = ThreadLocal.withInitial(() -> {
                    Playwright playwright = Playwright.create();
                    playwright.selectors().setTestIdAttribute("data-test");
                    return playwright;
                }
        );

        private static ThreadLocal<Browser> browser = ThreadLocal.withInitial(() ->
                playwright.get().chromium().launch(
                        new BrowserType.LaunchOptions().setHeadless(false)
                                .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
                )
        );

        private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();

        private static ThreadLocal<Page> page = new ThreadLocal<>();

    public static Page getPage()  {
        return page.get();
    }

    @Before
    public void setUpBrowserContext() {
        System.out.println("setUpBrowser Context");
        browserContext.set(browser.get().newContext());
        page.set(browserContext.get().newPage());
    }

    @After
    public void closeContext() {
        System.out.println("Close Browser Context");
        browserContext.get().close();
    }

    @AfterAll
    public static void tearDown() {
        browser.get().close();
        browser.remove();

        playwright.get().close();
        playwright.remove();
    }


}