package ru.yandex.praktikum.config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import static ru.yandex.praktikum.config.AppConfig.APP_MAIN_PAGE_URL;

public class BaseTest {
    public WebDriver webDriver;
    String CHROME = "chrome";

    @Before
    public void init(){
        webDriver = WebDriverFactory.getBrowser(CHROME);
        Cookie newCookie = new Cookie.Builder("Cartoshka", "true")
                .domain("qa-scooter.praktikum-services.ru").build();
        webDriver.manage().addCookie(newCookie);
        webDriver.get(APP_MAIN_PAGE_URL);
    }
    @After
    public void close() {
        webDriver.quit();
    }
}