package org.basicoops.classObject;

public class Polymorphism {

    class BankAccount {

        public void calculateInterest() {
            System.out.println("Calculating interest for bank account");
        }

    }
    class SavingsAccount extends BankAccount {

        @Override
        public void calculateInterest() {
            System.out.println("Calculating interest for savings account");
        }


    }
    class CheckingAccount extends BankAccount {

        @Override
        public void calculateInterest() {
            System.out.println("No interest for checking account");
        }

    }
    public class Main {

        public static void main(String[] args) {
           /* BankAccount account1 = new SavingsAccount();
            BankAccount account2 = new CheckingAccount();
            account1.calculateInterest(); // Output: Calculating interest for savings account
            account2.calculateInterest(); // Output: No interest for checking account*/
        }


    }
}
