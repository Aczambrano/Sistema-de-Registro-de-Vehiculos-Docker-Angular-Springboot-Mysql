package com.gizlo.crud.security.response.userapi;

public class Bank {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;

    public Bank(String cardExpire, String cardNumber, String cardType, String currency, String iban) {
        this.cardExpire = cardExpire;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.currency = currency;
        this.iban = iban;
    }

    public String getCardExpire() {
        return this.cardExpire;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getIban() {
        return this.iban;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
