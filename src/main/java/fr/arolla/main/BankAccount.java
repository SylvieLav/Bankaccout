package fr.arolla.main;

import fr.arolla.exceptions.NotEnoughMoneyException;

import java.util.Objects;

public class BankAccount {

   private final String iban;
   private int balance;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;

      if (o == null || getClass() != o.getClass()) return false;

      BankAccount that = (BankAccount) o;
      return Objects.equals(iban, that.iban);
   }

   @Override
   public int hashCode() {
      return Objects.hash(iban);
   }

   public BankAccount(String iban) {
      this.iban = iban;
   }

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
