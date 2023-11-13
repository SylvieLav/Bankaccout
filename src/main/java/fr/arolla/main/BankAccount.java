package fr.arolla.main;

public class BankAccount {

   private final String iban;
   private int balance;

   public BankAccount(String iban, int balance) {
      this.iban = iban;
      this.balance = balance;
   }

   void withdraw(int amount) {
      balance = balance - amount;
   }

   void deposit(int amount) {
      balance = balance + amount;
   }

   int getBalance() {
      return balance;
   }
}
