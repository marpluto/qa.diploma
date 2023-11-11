package ru.netology.test;

import ru.netology.data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.pages.ChoosingPaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditGateTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDataBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSuccessfullyBuyWithApprovedCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void shouldDeclineBuyingWithDeclinedCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationDeclined();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void shouldDeclineBuyingWithoutDbSavingWithCardThatIsNotInDatabase() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getCardThatIsNotInDatabase());
        payment.waitNotificationDeclined();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldSuccessfullyBuyWithCurrentMonthAntCurrentYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithCurrentMonthAntCurrentYear());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void shouldRejectInBuyingWithCardWithLastMonthAntCurrentYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithLastMonthAntCurrentYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingithCurrentMonthAntLastYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithCurrentMonthAntLastYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithWrongFormatMonth() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithWrongFormatMonth());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithAllFieldsEmpty() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormatAllFields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithFifteenSymbolsCardNumber() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWhitFifteenSymbolsCardNumber());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithCyrillicCardHolder() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithCyrillicCardHolder());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithOneWordCardHolder() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithOneWordCardHolder());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithCVVTwoSymbols() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithCVVTwoSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldRejectInBuyingWithCVVOneSymbol() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToCreditPage();
        payment.inputData(DataHelper.getApprovedCardWithCVVOneSymbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}