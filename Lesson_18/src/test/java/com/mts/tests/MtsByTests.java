package com.mts.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
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

@Epic("MTS Website Tests")
public class MtsByTests {

    private WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeEach
    @Step("Настройка WebDriver и открытие страницы MTS")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test
    @Feature("Block Title Verification")
    @Story("Check the block title after navigating to the Services page")
    @Description("Verifies that the block title on the Services page is correct")
    public void testCheckBlockTitle() {
        acceptCookiesAndNavigateToServices();

        String actualBlockTitle = homePage.getBlockTitle();
        String expectedBlockTitle = "Онлайн пополнение без комиссии";
        assertEquals(expectedBlockTitle, actualBlockTitle, "Заголовок блока не совпадает.");
    }

    @Test
    @Feature("Payment System Logos Verification")
    @Story("Check the presence of payment system logos on the Services page")
    @Description("Verifies that Visa, MasterCard, and Белкарт logos are displayed")
    public void testPaymentSystemLogos() {
        acceptCookiesAndNavigateToServices();

        assertTrue(homePage.isVisaLogoDisplayed(), "Логотип Visa не найден.");
        assertTrue(homePage.isMastercardLogoDisplayed(), "Логотип MasterCard не найден.");
        assertTrue(homePage.isBelkartLogoDisplayed(), "Логотип Белкарт не найден.");
    }

    @Test
    @Feature("More About Service Link")
    @Story("Check the URL of the 'More About Service' link")
    @Description("Verifies that clicking on the 'More About Service' link navigates to the correct URL")
    public void testMoreAboutServiceLink() {
        acceptCookiesAndNavigateToServices();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By moreInfoLink = By.xpath("//a[text()='Подробнее о сервисе']");
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        linkElement.click();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl, "URL после нажатия на ссылку не соответствует ожидаемому.");
    }

    @Test
    @Feature("Placeholder Text Verification")
    @Story("Check the placeholder text in various payment forms")
    @Description("Verifies the placeholder text in the payment forms for different options")
    public void testCheckPlaceholdersForAllPaymentOptions() {
        acceptCookiesAndNavigateToServices();

        paymentPage.scrollToElement(By.id("connection-phone"));

        assertEquals("Номер телефона", paymentPage.getPhonePlaceholderText(), "Плейсхолдер для услуги связи неверен.");
        assertEquals("Сумма", paymentPage.getSumPlaceholderText(), "Плейсхолдер для суммы неверен.");
        assertEquals("E-mail для отправки чека", paymentPage.getEmailPlaceholderText(), "Плейсхолдер для email неверен.");

        selectInternetOption();
        paymentPage.scrollToElement(By.id("internet-phone"));
        assertEquals("Номер абонента", paymentPage.getInternetPhonePlaceholderText(), "Плейсхолдер для домашнего интернета неверен.");

        selectInstallmentOption();
        paymentPage.scrollToElement(By.id("score-instalment"));
        assertEquals("Номер счета на 44", paymentPage.getInstallmentPhonePlaceholderText(), "Плейсхолдер для рассрочки неверен.");

        selectDebtOption();
        paymentPage.scrollToElement(By.id("score-arrears"));
        assertEquals("Номер счета на 2073", paymentPage.getDebtPhonePlaceholderText(), "Плейсхолдер для задолженности неверен.");
    }

    @Test
    @Feature("Online Payment Form")
    @Story("Verify online payment form functionality")
    @Description("Checks if the payment form displays the correct information and buttons")
    public void testOnlinePaymentForm() {
        acceptCookiesAndNavigateToServices();

        String phoneNumber = "297777777";
        String amount = "30";

        fillAndSubmitPaymentForm(phoneNumber, amount, "test@mail.ru");

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
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Принять cookies и перейти на страницу 'Услуги связи'")
    private void acceptCookiesAndNavigateToServices() {
        homePage.acceptCookies();
        homePage.openDropdown();
        homePage.selectServiceOption("Услуги связи");
    }

    @Step("Выбрать опцию 'Интернет'")
    private void selectInternetOption() {
        homePage.selectInternetOption();
    }

    @Step("Выбрать опцию 'Рассрочка'")
    private void selectInstallmentOption() {
        homePage.selectInstallmentOption();
    }

    @Step("Выбрать опцию 'Задолженность'")
    private void selectDebtOption() {
        homePage.selectDebtOption();
    }

    @Step("Заполнить и отправить форму оплаты")
    private void fillAndSubmitPaymentForm(String phoneNumber, String amount, String email) {
        paymentPage.fillPaymentForm(phoneNumber, amount, email);
        paymentPage.clickSubmitButton();
    }

    @Step("Форматировать номер телефона {phoneNumber}")
    private String formatPhoneNumber(String phoneNumber) {
        return "Оплата: Услуги связи Номер:375" + phoneNumber;
    }

    @Step("Форматировать сумму {amount}")
    private String formatAmount(String amount) {
        if (!amount.contains(".")) {
            amount += ".00";
        }
        return amount + " BYN";
    }
}
