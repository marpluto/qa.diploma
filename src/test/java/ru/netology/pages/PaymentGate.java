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
    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("input");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private final SelenideElement yearField = $(byText("Год")).parent().$("input");
    private final SelenideElement cardHolderField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("input");
    private final SelenideElement approvedOperation = $(byText("Операция одобрена Банком.")).parent().$("[class=notification__content]");
    private final SelenideElement declinedOperation = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=notification__content]");
    private final SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private final ElementsCollection wrongFormatErrorAll = $$(byText("Неверный формат"));
    private SelenideElement cardInvalidDateError = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private final SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    private final SelenideElement cancelField = $$("[class=icon-button__text]").first();
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

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

    public void waitNotificationInvalidDateError() {
        cardInvalidDateError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationExpiredError() {
        cardExpiredError.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitNotificationWrongFormatAllFields() {
        wrongFormatErrorAll.shouldHave(CollectionCondition.size(4));
        requiredFieldError.shouldBe(visible, Duration.ofSeconds(15));
    }
}
