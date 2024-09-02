package com.mts.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mts.by");
        homePage = new HomePage(driver, wait);
    }

    /**
     * Проверка названия блока
     */
    @Test
    public void testCheckBlockTitle() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        String actualBlockTitle = homePage.getBlockTitle();
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedBlockTitle, actualBlockTitle, "Заголовок блока не совпадает.");
    }

    /**
     * Проверяет наличие логотипов платёжных систем
     */
    @Test
    public void testPaymentSystemLogos() {
        // Принятие cookies и выбор услуги
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        // Локаторы для логотипов платёжных систем
        By visaLogo = By.cssSelector("img[alt='Visa']");
        By mastercardLogo = By.cssSelector("img[alt='MasterCard']");
        By belkartLogo = By.cssSelector("img[alt='Белкарт']");

        // Проверка наличия логотипов платёжных систем
        assertTrue(isElementDisplayed(visaLogo), "Логотип Visa не найден.");
        assertTrue(isElementDisplayed(mastercardLogo), "Логотип MasterCard не найден.");
        assertTrue(isElementDisplayed(belkartLogo), "Логотип Белкарт не найден.");
    }


    /**
     * Проверяет работу ссылки Подробнее о сервисе
     */
    @Test
    public void testMoreAboutServiceLink() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        By moreInfoLink = By.xpath("//a[text()='Подробнее о сервисе']");
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        linkElement.click();


        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl, "URL после нажатия на ссылку не соответствует ожидаемому.");
    }

    /**
     * Заполняет поля формы и проверяет работу кнопки Продолжить
     */
    @Test
    public void testOnlinePaymentForm() {
        // Принятие cookies и выбор услуги
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        // Локаторы для элементов формы
        By phoneInput = By.id("connection-phone");
        By sumInput = By.id("connection-sum");
        By emailInput = By.id("connection-email");
        By submitButton = By.cssSelector("#pay-connection .button__default");

        // Заполнение полей
        driver.findElement(phoneInput).sendKeys("297777777");
        driver.findElement(sumInput).sendKeys("30");
        driver.findElement(emailInput).sendKeys("test@mail.ru");

        // Прокрутка до кнопки и нажатие на кнопку Продолжить
        WebElement submitButtonElement = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButtonElement);
        submitButtonElement.click();

        // Ожидание и проверка URL после нажатия на кнопку
        String expectedUrl = "https://www.mts.by/";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "URL после нажатия на кнопку не совпадает.");
    }


    /**
     * Проверяет текст плейсхолдеров полей ввода
     */
    @Test
    public void testCheckPlaceholders() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        String actualPhonePlaceholder = getPlaceholderText(By.id("connection-phone"));
        String actualSumPlaceholder = getPlaceholderText(By.id("connection-sum"));
        String actualEmailPlaceholder = getPlaceholderText(By.id("connection-email"));

        assertEquals("Номер телефона", actualPhonePlaceholder, "Плейсхолдер поля телефона не совпадает.");
        assertEquals("Сумма", actualSumPlaceholder, "Плейсхолдер поля суммы не совпадает.");
        assertEquals("E-mail для отправки чека", actualEmailPlaceholder, "Плейсхолдер поля email не совпадает.");
    }

    private boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private String getPlaceholderText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getAttribute("placeholder");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
