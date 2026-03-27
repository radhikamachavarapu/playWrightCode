package com.serenitydojo.playWright.DomainAPI;
import net.bytebuddy.asm.Advice;
import net.datafaker.Faker;
import com.google.gson.Gson;

import java.time.LocalDate;

public class UserRecordRefactored {
    private static final Gson gson = new Gson();

    public record User (String first_name,
                        String last_name,
                        String phone,
                        String dob,
                        String email,
                        String street,
                        String city,
                        String state,
                        String country,
                        String postal_code,
                        String password)
    {

        public static User randomUser() {

            Faker fake = new Faker();
            int year = fake.number().numberBetween(1950, 2000);
            int month = fake.number().numberBetween(1, 12);
            int day = fake.number().numberBetween(1, 28);
            LocalDate date = LocalDate.of(year, month, day);
            String formatedDate = date.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return new User(
                    fake.name().firstName(),
                    fake.name().lastName(),
                    fake.phoneNumber().phoneNumber(),
                    formatedDate,
                    fake.internet().emailAddress(),
                    fake.address().streetAddress(),
                    fake.address().city(),
                    fake.address().state(),
                    fake.address().country(),
                    fake.address().zipCode(),
                    "WelcomeWorld123!"
            );
        }

        public User withPassword(String password) {
            return new User(
                    this.first_name,
                    this.last_name,
                    this.phone,
                    this.dob,
                    this.email,
                    this.street,
                    this.city,
                    this.state,
                    this.country,
                    this.postal_code,
                    password
            );
        }

        public static String randomUserJson() {
            return gson.toJson(randomUser());
        }

    }


}
