package fr.arolla.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BankAccountTest {

   @Test
   void should_balance_be_75_when_balance_is_100_has_withdraw_of_25() {
      // given
      BankAccount bankAccount = new BankAccount("ABC", 100);

      // when
      bankAccount.withdraw(25);

      // then
      assertThat(bankAccount.getBalance()).isEqualTo(75);
   }

   @Test
   void should_balance_be_150_when_balance_is_50_has_deposit_of_100() {
      // given
      BankAccount bankAccount = new BankAccount("ABC", 50);

      // when
      bankAccount.deposit(100);

      // then
      assertThat(bankAccount.getBalance()).isEqualTo(150);
   }

   @Test
   void should_throw_exception_when_balance_is_50_has_withdraw_of_100() {
      // given
      BankAccount bankAccount = new BankAccount("ABC", 50);

      // when then
      assertThatThrownBy(() -> bankAccount.withdraw(100))
            .hasMessage("Cannot withdraw 100, amount left is 50");
   }

   @Test
   void should_two_accounts_be_equal_when_having_same_iban() {
      // given when
      BankAccount bankAccount1 = new BankAccount("ABC");
      BankAccount bankAccount2 = new BankAccount("ABC");

      // then
      assertThat(bankAccount1).isEqualTo(bankAccount2);
   }

   @Test
   void should_two_accounts_not_be_equal_when_having_different_ibans() {
      // given when
      BankAccount bankAccount1 = new BankAccount("ABC");
      BankAccount bankAccount2 = new BankAccount("DEF");

      // then
      assertThat(bankAccount1).isNotEqualTo(bankAccount2);
   }
}
