package fr.arolla.bankaccount.exceptions;

public class NotEnoughMoneyException extends RuntimeException {

   public NotEnoughMoneyException(String message) {
      super(message);
   }
}
