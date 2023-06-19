package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.config.BaseTest;
import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@RunWith(Parameterized.class)
public class MakeNewOrderTest extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String dateFrom;
    private final String rentPeriod;
    private final String courierComment;
    private final String orderButton;

    public MakeNewOrderTest(String firstName, String lastName, String address, String phone, String dateFrom, String rentPeriod, String courierComment, String orderButton) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateFrom = dateFrom;
        this.rentPeriod = rentPeriod;
        this.courierComment = courierComment;
        this.orderButton = orderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        //Получаем актуальные даты (сегодня и завтра) для использования в тесте (можно поставить другие)
        String dateFromToday = now().format(ofPattern("d"));
        String dateFromTomorrow = now().plusDays(1).format(ofPattern("d"));
        return new Object[][]{
                {"Иванов", "Иван", "ул. Мира 16-34", "+79124445533", dateFromToday, "сутки", "Оплата картой", "orderButtonTop"},
                {"Пётр", "Петров", "ул. Никольская 20-77", "+79224785413", dateFromTomorrow, "двое суток", "Оплата наличными", "orderButtonDown"},
        };
    }

    @Test
    public void makeNewOrder() {

        new MainPage(webDriver)
                .clickOrderButton(orderButton)
                .setUserData(firstName, lastName, address, phone)
                .clickMetroStation()
                .nextClick()
                .setOrderOptions(dateFrom, rentPeriod, courierComment)
                .checkSuccessWindow();
    }
}