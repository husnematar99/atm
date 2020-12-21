package com.progressoft.induction.atm;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;
import java.math.BigDecimal;
import java.util.*;
import static com.progressoft.induction.atm.Banknote.*;



//Name:Husne Hasan
//email:husnematar99@gmail.com

public class MyATM implements ATM {

    //frequency Coins respectively i.e. index 0 >FIVE_JOD Repetition N,index 1 >TEN_JOD Repetition N
    private  int []freqCoins ;
    private List<Banknote> banknote ;
    //Balance my ATM
    private BigDecimal balance;
    private MySystem system;
    private Banknote[] allJOD;

    public MyATM()
    {
        system=new MySystem();
        balance=BigDecimal.ZERO;
        banknote = new ArrayList<Banknote>();
        freqCoins=new int[] {100,100,20,10};
        allJOD=new Banknote[]{FIVE_JOD,TEN_JOD,TWENTY_JOD,FIFTY_JOD};
        //Initialize  money inside the ATM and calculate before withdraw function
        calculateBanknote();

    }

    private void calculateBanknote()
    {
        balance=BigDecimal.ZERO;

        int index=0;
        for(Banknote value:allJOD)
        {
            for (int i = 0; i < freqCoins[index]; i++)
            {
                banknote.add(value);
                balance = balance.add(value.getValue());
            }
            index++;
        }

    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {

        BigDecimal Balance_person =system.getAccountBalance(accountNumber);
        //if the account user did not have enough  money
        if(Balance_person.compareTo(amount)==-1)
             throw new InsufficientFundsException("Sorry , You Did not have enough  money ");

        //ATM not Enough many
        if(Balance_person.compareTo(this.balance)==1)
            throw new NotEnoughMoneyInATMException ("Sorry ,ATM don't have Enough many");

        system.debitAccount(accountNumber,amount);
        List<Banknote> drawBankNote =new ArrayList<>();
        int index=3;
        //mix of banknotes
        while(amount.compareTo(BigDecimal.ZERO)!=0)
        {
            if(freqCoins[index]>0&&allJOD[index].getValue().compareTo(amount)!=1)
            {
                freqCoins[index]--;
                drawBankNote.add(allJOD[index]);
                amount=amount.subtract(allJOD[index].getValue());

            }
            index--;
            if(index==-1)index=3;

        }
        calculateBanknote();
        Collections.sort(drawBankNote);
        return drawBankNote;
    }


    // Example
    public static void main(String[] args)
    {


        //Create ATM and s Initialize System
        MyATM atm=new MyATM();
        Scanner s = new Scanner(System.in);
        //Initialize Variable Account
        String accountNumber;
        BigDecimal amount;

        do {
        System.out.println("Please Enter Account Number and amount draw  money.");
        accountNumber=s.next();

        //Validating user input
        while (!s.hasNextInt()) {
            System.out.println("That's not a number! ,Please  Enter a valid Number");
            s.next(); // this is important!
        }
        amount=new BigDecimal(s.nextInt());

        //List Draw Money
        List<Banknote> banknoteList=atm.withdraw(accountNumber,amount);
        System.out.format("Remain of your balance %.2f\n",atm.system.getAccountBalance(accountNumber));
        System.out.println("Done draw  money and Banknote:");
        System.out.println(banknoteList);

        System.out.println("thanks to use ATM \n do you want another draw money?");

        }
        while (s.next().toLowerCase().equals("yes"));

    }



    }
