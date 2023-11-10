package ru.netology.test;

import ru.netology.data.DataHelper;

import org.junit.jupiter.api.*;
import ru.netology.data.SQLHelper;
import ru.netology.pages.ChoosingPaymentMethod;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentGateTest {
    public static String url = System.getProperty("sut.url");

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDataBase();
    }

    // настроить аллюр

    @Test
    void buyWithApprovedCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyWithDeclinedCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitNotificationDeclined();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyWithCardThatIsNotInDatabase() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getCardThatIsNotInDatabase());
        payment.waitNotificationDeclined();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithCurrentMonthAntCurrentYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithCurrentMonthAntCurrentYear());
        payment.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyWithCardWithLastMonthAntCurrentYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithLastMonthAntCurrentYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithCurrentMonthAntLastYearCard() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithCurrentMonthAntLastYear());
        payment.waitNotificationExpiredError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithWrongFormatMonth() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithWrongFormatMonth());
        payment.waitNotificationExpirationDateError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithAllFieldsEmpty() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitNotificationWrongFormatAllFields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithFifteenSymbolsCardNumber() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWhitFifteenSymbolsCardNumber());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithCyrillicCardHolder() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithCyrillicCardHolder());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithOneWordCardHolder() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithOneWordCardHolder());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithCVVTwoSymbols() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithCVVTwoSymbols());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyWithCVVOneSymbol() {
        var paymentMethodPage = new ChoosingPaymentMethod();
        var payment = paymentMethodPage.goToPaymentPage();
        payment.inputData(DataHelper.getApprovedCardWithCVVOneSymbol());
        payment.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
