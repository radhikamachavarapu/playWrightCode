package com.serenitydojo.playWright.pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactForm2 {
    private Page page;
    private Locator firstNameField;
    private Locator lastNameField;
    private Locator emailField;
    private Locator messageField;
    private Locator subjectField;
    private Locator submitForm;

    public ContactForm2(Page page) {
        this.page = page;
        this.firstNameField = page.getByLabel("First Name");
        this.lastNameField = page.getByLabel("Last Name");
        this.emailField = page.getByLabel("Email");
        this.messageField= page.getByLabel("Message");
        this.subjectField = page.getByLabel("Subject");
        this.submitForm = page.locator(".btnSubmit");
    }

    public void enterFirstName(String firstName) {
        firstNameField.fill(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.fill(lastName);
    }

    public void enterEmail(String email) {
        emailField.fill(email);
    }

    public void enterMessage(String message) {
        messageField.fill(message);
    }

    public void selectSubject(String subject) {
        subjectField.selectOption(subject);
    }

    public void attachment() throws URISyntaxException {
        var attachmentField = page.getByLabel("Attachment");
        Path attachment = Paths.get(ClassLoader.getSystemResource("data/sample-data.txt").toURI());
        page.setInputFiles("#attachment", attachment);
        String uploadedFile = attachmentField.inputValue();
        org.assertj.core.api.Assertions.assertThat(uploadedFile).endsWith("sample-data.txt");
    }

    public void submitForm() {
        submitForm.click();
    }

}
