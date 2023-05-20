package ru.yandex.praktikum.config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver webDriver;
    String CHROME = "chrome";

    @Before
    public void init(){
        webDriver = WebDriverFactory.getBrowser(CHROME);
    }
    @After
    public void close() {
        webDriver.quit();
    }
}