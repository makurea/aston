package com.mts.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mts.by");
    }

    @Test
    public void testCheckTitle() {
        handleCookiePopup();

        String expectedTitle = "МТС – мобильный оператор в Беларуси";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "Название страницы не совпадает.");
    }

    @Test
    public void testCheckBlockTitle() {
        handleCookiePopup();

        By blockTitleLocator = By.cssSelector(".pay__wrapper h2");
        WebElement blockTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitleLocator));

        String actualBlockTitle = blockTitleElement.getText().replace("\n", " ").replace("\u00A0", " ").trim();
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedBlockTitle, actualBlockTitle, "Заголовок блока не совпадает.");
    }

    @Test
    public void testOnlinePaymentForm() {
        handleCookiePopup();

        By dropdownButton = By.cssSelector(".select__header");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();

        By communicationServicesOption = By.xpath("//p[text()='Услуги связи']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(communicationServicesOption));
        option.click();

        By phoneInput = By.id("connection-phone");
        By sumInput = By.id("connection-sum");
        By emailInput = By.id("connection-email");
        By submitButton = By.cssSelector("#pay-connection .button__default");

        driver.findElement(phoneInput).sendKeys("297777777");
        driver.findElement(sumInput).sendKeys("30");
        driver.findElement(emailInput).sendKeys("test@mail.ru");

        driver.findElement(submitButton).click();

        
    }


    private void handleCookiePopup() {
        try {
            By cookieAcceptButton = By.id("cookie-agree");
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Всплывающее окно cookie не появилось или уже было закрыто.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
