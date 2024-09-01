package com.mts.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локатор для кнопки закрытия окна
    private By closeButton = By.cssSelector(".header__close-button svg-icon[src='assets/images/icons/x.svg']");
    // Локатор для выпадающего меню
    private By dropdownButton = By.cssSelector(".select__header");
    // Локатор для кнопки принятия cookie
    private By cookieAcceptButton = By.id("cookie-agree");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void acceptCookies() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Всплывающее окно cookie не появилось или уже было закрыто.");
        }
    }

    public void openDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();
    }

    public void selectServiceOption(String optionText) {
        // Локатор для выбора опции в выпадающем меню
        By optionLocator = By.xpath("//p[text()='" + optionText + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public void closeWindow() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }
}
