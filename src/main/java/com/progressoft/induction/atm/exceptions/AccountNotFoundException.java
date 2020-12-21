package com.progressoft.induction.atm.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String s) {
        System.out.println(s);
    }
}
