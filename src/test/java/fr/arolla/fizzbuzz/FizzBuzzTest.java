package fr.arolla.fizzbuzz;

import static org.assertj.core.api.Assertions.assertThat;

import fr.arolla.fizzbuzz.FizzBuzz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FizzBuzzTest {

   FizzBuzz fizzBuzz;

   @BeforeEach
   void setUp() {
      fizzBuzz = new FizzBuzz();
   }

   @ParameterizedTest()
   @ValueSource(ints = { 3, 6, 9 })
   void should_output_fizz(int value) {
      assertThat(fizzBuzz.output(value)).isEqualTo("fizz");
   }

   @ParameterizedTest()
   @ValueSource(ints = { 5, 10, 20 })
   void should_output_buzz(int value) {
      assertThat(fizzBuzz.output(value)).isEqualTo("buzz");
   }

   @ParameterizedTest()
   @ValueSource(ints = { 15, 30, 45 })
   void should_output_fizzbuzz(int value) {
      assertThat(fizzBuzz.output(value)).isEqualTo("fizzbuzz");
   }

   @ParameterizedTest()
   @ValueSource(ints = { 1, 2, 7 })
   void should_output_same_number(int value) {
      assertThat(fizzBuzz.output(value)).isEqualTo(String.valueOf(value));
   }
}
