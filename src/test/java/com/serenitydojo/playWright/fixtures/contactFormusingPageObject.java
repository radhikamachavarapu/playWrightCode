package com.serenitydojo.playWright.fixtures;
import com.serenitydojo.playWright.pageObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;

public class contactFormusingPageObject extends playWrightClass {

    ContactForm contactForm;


    @DisplayName("Contact form using Page Object")
    @Test
    void contactForm() throws URISyntaxException {
        contactForm = new ContactForm(page);
        contactForm.enterFirstName("PlayWright");
        contactForm.enterLastName("Testing");
        contactForm.enterEmail("test123@playwright.com");
        contactForm.enterMessage("Hello, this is a test messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee.");
        contactForm.selectSubject("Warranty");
        contactForm.attachment();
        contactForm.submitForm();
        contactForm.submitForm();


    }

    @BeforeEach
    public void openPage() {
        page.navigate("https://practicesoftwaretesting.com/contact");

    }

    @BeforeEach
    void seup() {
        contactForm = new ContactForm(page);
    }

}
