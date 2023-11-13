package fr.arolla.main;

import fr.arolla.exceptions.NotEnoughMoneyException;

import static fr.arolla.main.OperationType.DEPOSIT;
import static fr.arolla.main.OperationType.WITHDRAWAL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class BankAccount {

   private final String iban;
   private final List<Operation> operations;

   public BankAccount(String iban) {
      this.iban = iban;
      this.operations = new ArrayList<>();
   }

   void withdraw (int amount) {
      int balance = getBalance();
      if (amount > balance) {
         throw new NotEnoughMoneyException("Cannot withdraw " + amount + ", amount left is " + balance);
      }

      operations.add(new Operation(WITHDRAWAL, LocalDate.now(), amount));
   }

   void deposit(int amount) {
      operations.add(new Operation(DEPOSIT, LocalDate.now(), amount));
   }

   int getBalance() {
      int deposits = operations.stream()
            .filter(o -> o.getOperationType() == DEPOSIT)
            .map(Operation::getAmount)
            .mapToInt(i -> i)
            .sum();
      int withdrawals = operations.stream()
            .filter(o -> o.getOperationType() == WITHDRAWAL)
            .map(Operation::getAmount)
            .mapToInt(i -> i)
            .sum();

      return deposits - withdrawals;
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

   public double getAverageOperationAmounts() {
      OptionalDouble average = operations.stream().mapToInt(Operation::getAmount).average();
      if (average.isEmpty()) {
         return 0;
      }
      
      return average.getAsDouble();
   }
}
