package com.mts.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для формы "Услуги связи"
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By emailInput = By.id("connection-email");
    private By submitButton = By.cssSelector("#pay-connection .button__default");

    // Локаторы для плейсхолдеров
    private By phonePlaceholder = By.id("connection-phone");
    private By sumPlaceholder = By.id("connection-sum");
    private By emailPlaceholder = By.id("connection-email");

    // Локатор для iframe оплаты
    private By paymentIFrame = By.xpath("//iframe[@class='bepaid-iframe']");

    // Локаторы для окна подтверждения (внутри iframe)
    private By displayedPhone = By.cssSelector(".pay-description__text span");
    private By displayedSum = By.cssSelector(".pay-description__cost span");
    private By paymentButton = By.cssSelector(".colored");

    // Локаторы для платежных систем
    private By googlePayButton = By.id("gpay-button-online-api-id");
    private By yandexPayButton = By.id("yandex-button");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput)).sendKeys(sum);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    public String getPhonePlaceholderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phonePlaceholder)).getAttribute("placeholder");
    }

    public String getSumPlaceholderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sumPlaceholder)).getAttribute("placeholder");
    }

    public String getEmailPlaceholderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailPlaceholder)).getAttribute("placeholder");
    }

    public boolean isPaymentDetailsWindowDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentIFrame)).isDisplayed();
    }

    public void switchToPaymentFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(paymentIFrame));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getDisplayedPhone() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(displayedPhone)).getText();
    }

    public String getDisplayedSum() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(displayedSum)).getText();
    }

    public String getPaymentButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentButton)).getText();
    }

    public boolean isGooglePayButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(googlePayButton)).isDisplayed();
    }

    public boolean isYandexPayButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(yandexPayButton)).isDisplayed();
    }
}