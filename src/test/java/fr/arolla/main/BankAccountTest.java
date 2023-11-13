package fr.arolla.main;

import static org.assertj.core.api.Assertions.assertThat;

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


}
