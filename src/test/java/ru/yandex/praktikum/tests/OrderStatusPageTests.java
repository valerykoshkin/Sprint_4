package ru.yandex.praktikum.tests;

import org.junit.Test;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.config.BaseTest;

public class OrderStatusPageTests extends BaseTest {

    String nonexistentOrder = "1234";

    @Test
    public void checkSearchOfIncorrectOrder() {
        new MainPage(webDriver)
                .clickOrderStatusButton()
                .fillOrderNumberInput(nonexistentOrder)
                .clickGoButton()
                .checknotFoundContainer();
    }
}