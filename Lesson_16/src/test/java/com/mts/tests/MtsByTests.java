package com.mts.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

    @Test
    public void testCheckBlockTitle() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        String actualBlockTitle = homePage.getBlockTitle();
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedBlockTitle, actualBlockTitle, "Заголовок блока не совпадает.");
    }

    @Test
    public void testPaymentSystemLogos() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не найден.");
        assertTrue(homePage.isMastercardLogoDisplayed(), "Логотип MasterCard не найден.");
        assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не найден.");
    }

    @Test
    public void testCheckPlaceholders() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");

        String actualPhonePlaceholder = paymentPage.getPhonePlaceholderText();
        String actualSumPlaceholder = paymentPage.getSumPlaceholderText();
        String actualEmailPlaceholder = paymentPage.getEmailPlaceholderText();

        assertEquals("Номер телефона", actualPhonePlaceholder, "Плейсхолдер поля телефона не совпадает.");
        assertEquals("Сумма", actualSumPlaceholder, "Плейсхолдер поля суммы не совпадает.");
        assertEquals("E-mail для отправки чека", actualEmailPlaceholder, "Плейсхолдер поля email не совпадает.");
    }

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
