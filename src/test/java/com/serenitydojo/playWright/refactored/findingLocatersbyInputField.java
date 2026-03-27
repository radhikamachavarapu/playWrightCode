package com.serenitydojo.playWright.refactored;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.serenitydojo.playWright.headLessChromeOptions;
import org.junit.jupiter.api.*;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(headLessChromeOptions.class)
public class findingLocatersbyInputField {


    @DisplayName("All input fields")
    @Nested
    class findbyInputField {

        @BeforeEach
        void navigateToPage(Page page) {
            openPage(page);
        }

        @DisplayName("Input fields")
        @Test
        void byInputField(Page page) throws URISyntaxException {
            var firstName = page.getByLabel("First Name");
            firstName.fill("PlayWright");
            assertThat(firstName).hasValue("PlayWright");

            var lastName = page.getByLabel("Last Name");
            lastName.fill("Testing");
            assertThat(lastName).hasValue("Testing");

            var email = page.getByLabel("Email");
            email.fill("test123@gmail.com");
            assertThat(email).hasValue("test123@gmail.com");

            var messageField = page.getByLabel("Message");
            messageField.fill("Hello, this is a test message.");
            assertThat(messageField).hasValue("Hello, this is a test message.");

            //Dropdown selection
            var subjectDropdown = page.getByLabel("Subject");
            subjectDropdown.selectOption("Webmaster");
            assertThat(subjectDropdown).hasValue("webmaster");

            //another way of selecting dropdown-1
            subjectDropdown = page.getByLabel("subject");
            subjectDropdown.selectOption(new SelectOption().setLabel("Warranty"));
            assertThat(subjectDropdown).hasValue("warranty");

            //another way of selecting dropdown-2
            subjectDropdown = page.getByLabel("subject");
            subjectDropdown.selectOption(new SelectOption().setIndex(1));
            assertThat(subjectDropdown).hasValue("customer-service");

            //Upload a file
            var attachmentField = page.getByLabel("Attachment");
            Path attachment = Paths.get(ClassLoader.getSystemResource("data/sample-data.txt").toURI());
            page.setInputFiles("#attachment", attachment);
            String uploadedFile = attachmentField.inputValue();
            org.assertj.core.api.Assertions.assertThat(uploadedFile).endsWith("sample-data.txt");

        }

        @DisplayName("Mandetory Fields")
        @Test
        void mandetoryFields(Page page) {
            var firstName = page.getByLabel("First Name");
            var lastName = page.getByLabel("Last Name");
            var email = page.getByLabel("Email");
            var subjectDropdown = page.getByLabel("Subject");
            page.locator(".btnSubmit").click();

            //Alternate way - 1
            //String errorMessage = page.getByTestId("first-name-error").textContent();
            //Alternate way - 2
            //String errorMessage = page.getByText("First name is required").textContent();
            //Alternate way - 3
            //String errorMessage = page.locator("#first_name_alert").textContent();
            //Assertions.assertTrue((errorMessage).contains("First name is required"));
            //Alternate way - 4
            var errorMessage = page.getByRole(AriaRole.ALERT).getByText("First name is required");
            assertThat(errorMessage).isVisible();

        }

    }


    public void openPage(Page page) {
        page.navigate("https://practicesoftwaretesting.com/contact");

    }

}
