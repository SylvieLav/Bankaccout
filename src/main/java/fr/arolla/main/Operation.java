package fr.arolla.main;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {

   private final OperationType operationType;
   private final LocalDate localDate;
   private final int amount;

   public Operation(OperationType operationType, LocalDate localDate, int amount) {

      this.operationType = operationType;
      this.localDate = localDate;
      this.amount = amount;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;

      if (o == null || getClass() != o.getClass()) return false;

      Operation operation = (Operation) o;

      return amount == operation.amount
            && operationType == operation.operationType
            && Objects.equals(localDate, operation.localDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(operationType, localDate, amount);
   }

   @Override
   public String toString() {
      return "Operation{" +
            "operationType=" + operationType +
            ", localDate=" + localDate +
            ", amount=" + amount +
            '}';
   }
}
