package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.yandex.praktikum.config.AppConfig.APP_MAIN_PAGE_URL;

public class MainPage{
    WebDriver webDriver;

    //кнопка скрытия согласия
    public By closeCookiesButton = By.id("rcc-confirm-button");

    //кнопка заказа
    public By orderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //кнопка статуса заказа
    public By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");

    //инпут ввода номера заказа
    public By orderNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");

    //Лого Яндекса
    public By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    //Лого Самоката
    public By samokatLogo = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Go!
    public By goButton = By.xpath(".//button[text()='Go!']");


    public MainPage(WebDriver webDriver){
        this.webDriver = webDriver;
        webDriver.get(APP_MAIN_PAGE_URL);
    }

    //Закрываем уведомление о куках
    public MainPage clickCloseCoockisNotification(){
        webDriver.findElement(closeCookiesButton).click();
        return this;

    }

    //Скролл до вопросов
    public MainPage scrollToAccordion(){
        WebElement element = webDriver.findElement(By.className("accordion"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    //Клик по созданию заказа
    public OrderPage clickOrderButton(){
        webDriver.findElement(orderButton).click();
        return new OrderPage(webDriver);
    }

    //Клик по кнопке в аккордеоне
    public MainPage clickAccordionButton(String questionButton){
        webDriver.findElement(By.id(questionButton)).click();
        return this;
    }

    //Получаем текст ответа в аккордеоне
    public String getAnswerTextInAccordion(String answerLocator){
        return webDriver.findElement(By.id(answerLocator)).getText();
    }

    //Клик по лого яндекса
    public void clickYandexLogo(){
        webDriver.findElement(yandexLogo).click();
    }

    //Клик по статусу заказа
    public MainPage clickOrderStatusButton(){
        webDriver.findElement(orderStatusButton).click();
        return this;
    }

    //Заполняем инпут номера заказа
    public MainPage fillOrderNumberInput(String nonexistentOrder){
        new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderNumberInput));
        webDriver.findElement(orderNumberInput).sendKeys(nonexistentOrder);
        return this;
    }

    //Клик по кнопке Go
    public OrderStatusPage clickGoButton(){
        webDriver.findElement(goButton).click();
        return new OrderStatusPage(webDriver);
    }
}