package fr.arolla.main;

import fr.arolla.exceptions.NotEnoughMoneyException;

public class BankAccount {

   private final String iban;
   private int balance;

   public BankAccount(String iban, int balance) {
      this.iban = iban;
      this.balance = balance;
   }

   void withdraw (int amount) {
      if (amount > balance) {
         throw new NotEnoughMoneyException("Cannot withdraw " + amount + ", amount left is " + balance);
      }

      balance = balance - amount;
   }

   void deposit(int amount) {
      balance = balance + amount;
   }

   int getBalance() {
      return balance;
   }
}
