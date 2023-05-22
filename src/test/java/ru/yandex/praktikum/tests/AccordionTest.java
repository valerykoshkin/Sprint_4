package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pages.MainPage;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.config.BaseTest;

@RunWith(Parameterized.class)
public class AccordionTest extends BaseTest {

    private final String question;
    private final String answer;
    private final String expected;

    public AccordionTest(String question, String answer, String expected) {
        this.question = question;
        this.answer = answer;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {"0", "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"1", "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"2", "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"3", "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"4", "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"5", "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"6", "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"7", "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void accordionTest() {

        MainPage mainPage = new MainPage(webDriver)
                .scrollToAccordion()
                .clickAccordionButton("accordion__heading-" + question);

        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + answer)));

        assertEquals(mainPage.getAnswerTextInAccordion("accordion__panel-" + answer), expected);
    }
}