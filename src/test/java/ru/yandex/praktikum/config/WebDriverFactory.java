package ru.yandex.praktikum.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getBrowser(String browserName){

        switch (browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Browser not found");
        }
    }
}
