package com.serenitydojo.playWright.pageObjects;

import com.microsoft.playwright.Page;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactForm {
    private Page page;

    public ContactForm(Page page) {
        this.page = page;
    }

    public void enterFirstName(String firstName) {
        page.getByLabel("First Name").fill(firstName);
    }

    public void enterLastName(String lastName) {
        page.getByLabel("Last Name").fill(lastName);
    }

    public void enterEmail(String email) {
        page.getByLabel("Email").fill(email);
    }

    public void enterMessage(String message) {
        page.getByLabel("Message").fill(message);
    }

    public void selectSubject(String subject) {
        page.getByLabel("Subject").selectOption(subject);
    }

    public void attachment() throws URISyntaxException {
        var attachmentField = page.getByLabel("Attachment");
        Path attachment = Paths.get(ClassLoader.getSystemResource("data/sample-data.txt").toURI());
        page.setInputFiles("#attachment", attachment);
        String uploadedFile = attachmentField.inputValue();
        org.assertj.core.api.Assertions.assertThat(uploadedFile).endsWith("sample-data.txt");
    }

    public void submitForm() {
        page.locator(".btnSubmit").click();
    }

}
