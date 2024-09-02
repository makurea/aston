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

import static org.junit.jupiter.api.Assertions.*;

public class MtsByTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    /**
     * Проверяет название указанного блока
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
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не найден.");
        assertTrue(homePage.isMastercardLogoDisplayed(), "Логотип MasterCard не найден.");
        assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не найден.");
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
     * Проверяет надписи в незаполненных полях каждого варианта оплаты услуг
     */
    @Test
    public void testCheckPlaceholdersForAllPaymentOptions() {
        // Принятие cookies
        homePage.acceptCookies();

        // Проверка для услуги связи
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        // Прокрутка до формы
        paymentPage.scrollToElement(By.id("connection-phone"));

        // Проверка плейсхолдеров
        assertEquals("Номер телефона", paymentPage.getPhonePlaceholderText(), "Плейсхолдер для услуги связи неверен.");
        assertEquals("Сумма", paymentPage.getSumPlaceholderText(), "Плейсхолдер для суммы неверен.");
        assertEquals("E-mail для отправки чека", paymentPage.getEmailPlaceholderText(), "Плейсхолдер для email неверен.");

        // Прокрутка до формы домашнего интернета
        homePage.selectInternetOption();
        paymentPage.scrollToElement(By.id("internet-phone"));

        assertEquals("Номер абонента", paymentPage.getInternetPhonePlaceholderText(), "Плейсхолдер для домашнего интернета неверен.");

        // Прокрутка до формы рассрочки
        homePage.selectInstallmentOption();
        paymentPage.scrollToElement(By.id("score-instalment"));

        assertEquals("Номер счета на 44", paymentPage.getInstallmentPhonePlaceholderText(), "Плейсхолдер для рассрочки неверен.");

        // Прокрутка до формы задолженности
        homePage.selectDebtOption();  // метод с прокруткой и кликом
        paymentPage.scrollToElement(By.id("score-arrears"));

        assertEquals("Номер счета на 2073", paymentPage.getDebtPhonePlaceholderText(), "Плейсхолдер для задолженности неверен.");
    }


    /**
     * Проверяет работу кнопки продолжить и корректность отображения информации
     */
    @Test
    public void testOnlinePaymentForm() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        String phoneNumber = "297777777";
        String amount = "30";

        paymentPage.fillPaymentForm(phoneNumber, amount, "test@mail.ru");
        paymentPage.clickSubmitButton();

        assertTrue(paymentPage.isPaymentDetailsWindowDisplayed(), "Окно подтверждения оплаты не отображается.");

        paymentPage.switchToPaymentFrame();

        assertEquals(formatPhoneNumber(phoneNumber), paymentPage.getDisplayedPhone(), "Номер телефона отображается неверно.");
        assertEquals(formatAmount(amount), paymentPage.getDisplayedSum(), "Сумма отображается неверно.");
        assertTrue(paymentPage.getPaymentButtonText().contains(amount), "Текст на кнопке неверен.");
        assertTrue(paymentPage.isGooglePayButtonDisplayed(), "Кнопка Google Pay не отображается.");
        assertTrue(paymentPage.isYandexPayButtonDisplayed(), "Кнопка Yandex Pay не отображается.");

        paymentPage.switchToDefaultContent();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String formatPhoneNumber(String phoneNumber) {
        return "Оплата: Услуги связи Номер:375" + phoneNumber;
    }

    private String formatAmount(String amount) {
        if (!amount.contains(".")) {
            amount += ".00";
        }
        return amount + " BYN";
    }


}
