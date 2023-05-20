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

    public MakeNewOrderTest(String firstName, String lastName, String address, String phone, String dateFrom, String rentPeriod, String courierComment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateFrom = dateFrom;
        this.rentPeriod = rentPeriod;
        this.courierComment = courierComment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        //Получаем актуальные даты (сегодня и завтра) для использования в тесте (можно поставить другие)
        String dateFromToday = now().format(ofPattern("d"));
        String dateFromTomorrow = now().plusDays(1).format(ofPattern("d"));
        return new Object[][]{
                {"Иванов", "Иван", "ул. Мира 16-34", "+79124445533", dateFromToday, "сутки", "Оплата картой"},
                {"Пётр", "Петров", "ул. Никольская 20-77", "+79224785413", dateFromTomorrow, "двое суток", "Оплата наличными"},
        };
    }

    @Test
    public void makeNewOrder() {

        new MainPage(webDriver)
                .clickCloseCoockisNotification()
                .clickOrderButton()
                .setUserData(firstName, lastName, address, phone)
                .clickMetroStation()
                .nextClick()
                .setOrderOptions(dateFrom, rentPeriod, courierComment)
                .checkSuccessWindow();
    }
}