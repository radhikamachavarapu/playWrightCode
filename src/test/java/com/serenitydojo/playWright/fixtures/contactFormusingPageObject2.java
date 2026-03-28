package com.serenitydojo.playWright.fixtures;

import com.serenitydojo.playWright.pageObjects.ContactForm2;
import com.serenitydojo.playWright.playWrightpackage.playWrightClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class contactFormusingPageObject2 extends playWrightClass {

    ContactForm2 contactForm2;


    @DisplayName("Contact form using Page Object")
    @Test
    void contactForm() throws URISyntaxException {
        contactForm2 = new ContactForm2(page);
        contactForm2.enterFirstName("PlayWright");
        contactForm2.enterLastName("Testing");
        contactForm2.enterEmail("test123@playwright.com");
        contactForm2.enterMessage("Hello, this is a test messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee.");
        contactForm2.selectSubject("Warranty");
        contactForm2.attachment();
        contactForm2.submitForm();
    }

    @BeforeEach
    public void openPage() {
        page.navigate("https://practicesoftwaretesting.com/contact");

    }

    @BeforeEach
    void seup() {
        contactForm2 = new ContactForm2(page);
    }

}
