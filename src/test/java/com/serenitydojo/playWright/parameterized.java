package com.serenitydojo.playWright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(headLessChromeOptions.class)
public class parameterized {


    @DisplayName("All input fields")
    @Nested
    class findbyInputField {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("Mandetory Fields")
        @ParameterizedTest
        @ValueSource(strings = {"First Name", "Last Name", "Email", "Message"})
        void mandetoryFields(String fieldsName, Page page) {
            var firstName = page.getByLabel("First Name");
            var lastName = page.getByLabel("Last Name");
            var email = page.getByLabel("Email");
            var message = page.getByLabel("Message");
            var subjectDropdown = page.getByLabel("Subject");
            var sendBtton = page.getByText("Send");

            firstName.fill("Testing");
            lastName.fill("PlayWright");
            email.fill("test123@playwright.com");
            subjectDropdown.selectOption("Webmaster");
            message.fill("Hello, this is a test messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

            page.getByLabel(fieldsName).clear();
            sendBtton.click();

            var errorMessage = page.getByRole(AriaRole.ALERT).getByText(fieldsName +" is required");
            assertThat(errorMessage).isVisible();

        }

    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/contact");

    }

}
