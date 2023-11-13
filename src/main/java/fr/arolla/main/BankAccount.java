package fr.arolla.main;

import fr.arolla.exceptions.NotEnoughMoneyException;

import static fr.arolla.main.OperationType.DEPOSIT;
import static fr.arolla.main.OperationType.WITHDRAWAL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public final class BankAccount {

   private final String iban;
   private final List<Operation> operations;

   public BankAccount(String iban) {
      this.iban = iban;
      this.operations = new ArrayList<>();
   }

   public BankAccount(String iban, List<Operation> operations) {
      this.iban = iban;
      this.operations = operations;
   }

   void withdraw(int amount) {
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
            .filter(o -> o.operationType() == DEPOSIT)
            .map(Operation::amount)
            .mapToInt(i -> i)
            .sum();
      int withdrawals = operations.stream()
            .filter(o -> o.operationType() == WITHDRAWAL)
            .map(Operation::amount)
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
      return new ArrayList<>(operations);
   }

   public double getAverageOperationAmounts() {
      OptionalDouble average = operations.stream().mapToInt(Operation::amount).average();
      if (average.isEmpty()) {
         return 0;
      }

      return average.getAsDouble();
   }

   public double getSumOfWithdrawalBetween(LocalDate start, LocalDate end) {
      return operations.stream()
            .filter(o -> o.operationType() == WITHDRAWAL)
            .filter(o -> o.localDate().isAfter(start.minusDays(1)) && o.localDate().isBefore(end.plusDays(1)))
            .mapToInt(Operation::amount)
            .sum();
   }

   public List<LocalDate> getOperationsDates() {
      return operations.stream().map(Operation::localDate).toList();
   }

   public Map<LocalDate, List<Operation>> getOperationsByDate() {
      return operations.stream().collect(Collectors.groupingBy(Operation::localDate));
   }
}
