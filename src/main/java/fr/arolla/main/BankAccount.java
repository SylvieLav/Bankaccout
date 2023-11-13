package fr.arolla.main;

import fr.arolla.exceptions.NotEnoughMoneyException;

import static fr.arolla.main.OperationType.DEPOSIT;
import static fr.arolla.main.OperationType.WITHDRAWAL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccount {

   private final String iban;
   private int balance;
   private final List<Operation> operations;

   public BankAccount(String iban) {
      this.iban = iban;
      this.operations = new ArrayList<>();
   }

   public BankAccount(String iban, int balance) {
      this.iban = iban;
      this.balance = balance;
      this.operations = new ArrayList<>();
   }

   void withdraw (int amount) {
      if (amount > balance) {
         throw new NotEnoughMoneyException("Cannot withdraw " + amount + ", amount left is " + balance);
      }

      balance = balance - amount;
      operations.add(new Operation(WITHDRAWAL, LocalDate.now(), amount));
   }

   void deposit(int amount) {
      balance = balance + amount;
      operations.add(new Operation(DEPOSIT, LocalDate.now(), amount));
   }

   int getBalance() {
      return balance;
   }

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

   public List<Operation> getOperations() {
      return operations;
   }
}
