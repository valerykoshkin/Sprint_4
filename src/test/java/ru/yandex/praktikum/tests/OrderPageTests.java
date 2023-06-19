package ru.yandex.praktikum.tests;

import org.junit.Test;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.config.BaseTest;

public class OrderPageTests extends BaseTest {

    String incorrectFirstName = "John";
    String incorrectLastName = "Smith";
    String incorrectAddress = "Moscow";
    String incorrectPhone = "7512";
    String orderButton = "orderButtonTop";

    @Test
    public void checkErrorInputs(){
        new MainPage(webDriver)
                .clickOrderButton(orderButton)
                .setUserData(incorrectFirstName, incorrectLastName, incorrectAddress, incorrectPhone)
                .nextClick()
                .checkInputValidation();
    }
}