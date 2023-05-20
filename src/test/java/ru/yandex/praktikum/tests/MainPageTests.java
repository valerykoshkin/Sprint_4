package ru.yandex.praktikum.tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.config.BaseTest;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;
import static ru.yandex.praktikum.config.AppConfig.APP_MAIN_PAGE_URL;

public class MainPageTests extends BaseTest {
    String yandexURL = "https://dzen.ru/?yredirect=true";

    @Test
    public void clickYandexLogoTest(){
        MainPage mainPage = new MainPage(webDriver);

        //Переключаемся на открытую вкладку. Код взят с оф. документации Selenium
        String originalWindow = webDriver.getWindowHandle();
        assert webDriver.getWindowHandles().size() == 1;
        mainPage.clickYandexLogo();

        new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String windowHandle : webDriver.getWindowHandles()){
            if(!originalWindow.contentEquals(windowHandle)){
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(webDriver, 5)
                .until(urlMatches(yandexURL));
    }

    @Test
    public void clickSamokatLogoTest(){

        new MainPage(webDriver)
                //Делаем переход на /order, чтобы убедиться, что клик по лого работает корректно и возвращает на главную
                .clickOrderButton()
                .clickSamokatLogo();

        new WebDriverWait(webDriver, 5)
                .until(urlMatches(APP_MAIN_PAGE_URL));
    }
}