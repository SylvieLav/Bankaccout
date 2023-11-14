package fr.arolla.main;

import static fr.arolla.main.OperationType.DEPOSIT;
import static fr.arolla.main.OperationType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {

   private static BankAccount bankAccount;

   @BeforeEach
   void setUp() {
      bankAccount = new BankAccount("ABC");
   }

   @Test
   void should_balance_be_75_when_balance_is_100_has_withdraw_of_25() {
      // given
      bankAccount.deposit(100);

      // when
      bankAccount.withdraw(25);

      // then
      assertThat(bankAccount.getBalance()).isEqualTo(75);
   }

   @Test
   void should_balance_be_150_when_balance_is_50_has_deposit_of_100() {
      // given
      bankAccount.deposit(50);

      // when
      bankAccount.deposit(100);

      // then
      assertThat(bankAccount.getBalance()).isEqualTo(150);
   }

   @Test
   void should_throw_exception_when_balance_is_50_has_withdraw_of_100() {
      // given
      bankAccount.deposit(50);

      // when then
      assertThatThrownBy(() -> bankAccount.withdraw(100))
            .hasMessage("Cannot withdraw 100, amount left is 50");
   }

   @Test
   void should_two_accounts_be_equal_when_having_same_iban() {
      // when
      BankAccount bankAccount2 = new BankAccount("ABC");

      // then
      assertThat(bankAccount).isEqualTo(bankAccount2);
   }

   @Test
   void should_two_accounts_not_be_equal_when_having_different_ibans() {
      // given when
      BankAccount bankAccount2 = new BankAccount("DEF");

      // then
      assertThat(bankAccount).isNotEqualTo(bankAccount2);
   }

   @Test
   void should_get_operations_with_exact_data() {
      // when
      bankAccount.deposit(100);
      bankAccount.withdraw(30);
      bankAccount.withdraw(20);

      // then
      assertThat(bankAccount.getOperations()).containsExactly(
            new Operation(DEPOSIT, LocalDate.now(), 100),
            new Operation(WITHDRAWAL, LocalDate.now(), 30),
            new Operation(WITHDRAWAL, LocalDate.now(), 20));
   }

   @Test
   void should_return_average_amount() {
      // when
      bankAccount.deposit(100);
      bankAccount.withdraw(30);
      bankAccount.withdraw(20);

      //then
      assertThat(bankAccount.getAverageOperationAmounts()).isEqualTo(50);
   }

   @Test
   void should_return_sum_of_withdrawal_between_two_dates() {
      // given
      List<Operation> operations = new ArrayList<>();
      operations.add(createOperation(DEPOSIT, LocalDate.of(2023, 11, 1), 100));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 10));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 20));
      operations.add(createOperation(WITHDRAWAL, LocalDate.now(), 30));
      bankAccount = new BankAccount("ABC", operations);

      // when
      double withdrawalsAmount = bankAccount.getSumOfWithdrawalBetween(LocalDate.of(2023, 11, 10), LocalDate.of(2023, 11, 11));

      // then
      assertThat(withdrawalsAmount).isEqualTo(30);
   }

   @Test
   void should_return_dates_of_all_operations() {
      // given
      List<Operation> operations = new ArrayList<>();
      operations.add(createOperation(DEPOSIT, LocalDate.of(2023, 11, 1), 100));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 10));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 20));
      operations.add(createOperation(WITHDRAWAL, LocalDate.now(), 30));
      bankAccount = new BankAccount("ABC", operations);

      // when
      List<LocalDate> dates = bankAccount.getOperationsDates();

      // then
      assertThat(dates).containsExactly(
            LocalDate.of(2023, 11, 1),
            LocalDate.of(2023, 11, 10),
            LocalDate.of(2023, 11, 10),
            LocalDate.now());
   }

   private static Operation createOperation(OperationType operationType, LocalDate date, int amount) {
      return new Operation(operationType, date, amount);
   }

   @Test
   void should_return_all_operations_given_a_date() {
      // given
      List<Operation> operations = new ArrayList<>();
      operations.add(createOperation(DEPOSIT, LocalDate.of(2023, 11, 1), 100));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 10));
      operations.add(createOperation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 20));
      operations.add(createOperation(WITHDRAWAL, LocalDate.now(), 30));
      bankAccount = new BankAccount("ABC", operations);

      // when
      Map<LocalDate, List<Operation>> operationsByDate = bankAccount.getOperationsByDate();

      // then
      assertThat(operationsByDate).containsExactly(
            entry(LocalDate.now(), List.of(new Operation(WITHDRAWAL, LocalDate.now(), 30))),
            entry(LocalDate.of(2023, 11, 10),
                  List.of(
                        new Operation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 10),
                        new Operation(WITHDRAWAL, LocalDate.of(2023, 11, 10), 20))),
            entry(LocalDate.of(2023, 11, 1), List.of(new Operation(DEPOSIT, LocalDate.of(2023, 11, 1), 100))));
   }

   @Test
   void should_access_by_reflexion() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//      Method[] methods = BankAccount.class.getMethods();
//      for (Method method : methods) {
//         System.out.println(method);
//      }

      Operation operation = Operation.class.getDeclaredConstructor(
              OperationType.class,
              LocalDate.class,
              Integer.class)
              .newInstance(DEPOSIT, LocalDate.now(), 100);
      System.out.println(operation);

      System.out.println(Operation.class.isEnum());
   }
}
