package ru.netology.data;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    private DataHelper() {
    }

    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "24", "Mikhail Maykhailov", "123");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "24", "Mikhail Maykhailov", "123");
    }

    //номер карты, которой нет в базе данных
    public static Card getCardThatIsNotInDatabase() {
        return new Card("1234567890123456", getApprovedCard().month, getApprovedCard().year, getApprovedCard().cardHolder, getApprovedCard().cvv);
    }

    //текущий месяц и год
    public static Card getApprovedCardWithCurrentMonthAntCurrentYear() {
        return new Card(getApprovedCard().cardNumber, getCurrentMonthMinusShift(0), getCurrentYearMinusShift(0), getApprovedCard().cardHolder, getApprovedCard().cvv);
    }

    //текущий год, прошлый месяц
    public static Card getApprovedCardWithLastMonthAntCurrentYear() {
        return new Card(getApprovedCard().cardNumber, getCurrentMonthMinusShift(1), getCurrentYearMinusShift(0), getApprovedCard().cardHolder, getApprovedCard().cvv);
    }

    //текущий месяц, прошлый год
    public static Card getApprovedCardWithCurrentMonthAntLastYear() {
        return new Card(getApprovedCard().cardNumber, getCurrentMonthMinusShift(0), getCurrentYearMinusShift(1), getApprovedCard().cardHolder, getApprovedCard().cvv);
    }

    //пустая карта
    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }

    //номер карты из 15-ти символов
    public static Card getApprovedCardWhitFifteenNumbersCardNumber() {
        return new Card("444444444444444", getApprovedCard().month, getApprovedCard().year, getApprovedCard().cardHolder, getApprovedCard().cvv);
    }

    //имя владельца на кириллице
    public static Card getApprovedCardWithCyrillicCardHolder() {
        return new Card(getApprovedCard().cardNumber, getApprovedCard().month, getApprovedCard().year, "Михаил Михайлов", getApprovedCard().cvv);
    }

    //имя владельца одним словом
    public static Card getApprovedCardWithOneWordCardHolder() {
        return new Card(getApprovedCard().cardNumber, getApprovedCard().month, getApprovedCard().year, "Mikhail", getApprovedCard().cvv);
    }

    //CVV двумя цифрами
    public static Card getApprovedCardWithCVVTwoNumbers() {
        return new Card(getApprovedCard().cardNumber, getApprovedCard().month, getApprovedCard().year, getApprovedCard().cardHolder, "12");
    }

    //CVV одной цифрой
    public static Card getApprovedCardWithCVVOneNumber() {
        return new Card(getApprovedCard().cardNumber, getApprovedCard().month, getApprovedCard().year, getApprovedCard().cardHolder, "1");
    }

    @Value
    public static class Card {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }

    public static String getCurrentYearMinusShift(int shift) {
        return LocalDate.now().minusYears(shift).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static String getCurrentMonthMinusShift(int shift) {
        return LocalDate.now().minusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }
}
