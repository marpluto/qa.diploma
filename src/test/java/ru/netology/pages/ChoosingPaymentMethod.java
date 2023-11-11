package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ChoosingPaymentMethod {
    private SelenideElement heading = $$("h3").find(exactText("Оплата по карте"));
    private final SelenideElement paymentButton = $$("button").find(exactText("Купить"));
    private final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public ChoosingPaymentMethod() {
        heading.shouldBe(visible);
    }

    public PaymentGate goToPaymentPage() {
        paymentButton.click();
        return new PaymentGate();
    }

    public CreditGate goToCreditPage() {
        creditButton.click();
        return new CreditGate();
    }
}
