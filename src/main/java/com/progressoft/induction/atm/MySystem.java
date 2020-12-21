package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MySystem implements BankingSystem{

    private List<Account>accountList;
    public MySystem()
    {
        accountList=new ArrayList<>();
        String[]InitialAccount={"123456789","111111111","222222222","333333333","444444444"};
        BigDecimal BalanceInitial=new BigDecimal(1000);
        //initial accounts and balances in the BankingSystem
        for(String account_number:InitialAccount)
        {
            accountList.add(new Account(account_number,BalanceInitial));
        }
    }


    @Override
    public BigDecimal getAccountBalance(String accountNumber)
    {
        for(Account person:accountList)
        {
            if(person.getNumberAccount().equals(accountNumber))
                return person.getBalance();
        }
        //not valid accountNumber
         throw new AccountNotFoundException("Sorry , Account Number not Valid");
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount)
    {

        for(Account person:accountList)
        {
            if(person.getNumberAccount().equals(accountNumber))
            {
                BigDecimal BalanceBeforeDebit=person.getBalance().subtract(amount);
                person.setBalance(BalanceBeforeDebit);
            }
        }
    }
}
