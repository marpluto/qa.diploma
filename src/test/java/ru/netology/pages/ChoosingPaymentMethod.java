package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ChoosingPaymentMethod {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement paymentButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

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
