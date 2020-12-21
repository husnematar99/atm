package com.progressoft.induction.atm;

import java.math.BigDecimal;

public class Account  {

    private String numberAccount;
    private BigDecimal balance;

    public Account(String numberAccount, BigDecimal balance) {
        this.numberAccount = numberAccount;
        this.balance = balance;
    }


    public String getNumberAccount() {
        return numberAccount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
