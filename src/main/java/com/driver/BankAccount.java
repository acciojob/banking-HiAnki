package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        int total = sum;
        int i = digits;
        String ans = "";
        while(i>0) {
            if(total>=9) {
                total -= 9;
                ans += 9;
            }else if(total<9 && total>0) {
                ans += total;
                total = 0;
            }else {
                ans += 0;
            }
            i--;
        }
        if(total>0) {
            // throw exception
            throw new AccountNumberException( "Account Number can not be generated");
        }else {
            return ans;
        }

//        return null;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance = balance + amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance-amount<minBalance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }else {
            this.balance = balance - amount;
        }
    }

}