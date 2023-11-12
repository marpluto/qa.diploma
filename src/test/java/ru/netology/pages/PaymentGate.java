package ru.netology.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentGate {
    private SelenideElement heading = $(byText("Оплата по карте"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("input");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private SelenideElement yearField = $(byText("Год")).parent().$("input");
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("input");
    private SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("input");
    private SelenideElement declinedOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("input");
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private ElementsCollection wrongFormatErrorAll = $$(byText("Неверный формат"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    private SelenideElement cancelField = $$("[class=icon-button__text]").first();
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public PaymentGate() {
        heading.shouldBe(visible);
    }

    public void inputData(DataHelper.Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void waitNotificationApproved() {
        approvedOperation.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void waitNotificationDeclined() {
        declinedOperation.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationWrongFormat() {
        wrongFormatError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationExpiredError() {
        cardExpiredError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationWrongFormatAllFields() {
        wrongFormatErrorAll.shouldHave(CollectionCondition.size(4));
        requiredFieldError.shouldBe(visible, Duration.ofSeconds(15));
    }
}
