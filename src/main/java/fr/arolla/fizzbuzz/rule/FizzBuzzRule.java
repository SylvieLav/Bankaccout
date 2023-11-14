package fr.arolla.fizzbuzz.rule;

import fr.arolla.fizzbuzz.rule.FizzBuzzPredicate;

public class FizzBuzzRule {

   private final FizzBuzzPredicate predicate;
   private final String output;

   FizzBuzzRule(FizzBuzzPredicate predicate, String output) {
      this.predicate = predicate;
      this.output = output;
   }

   public FizzBuzzPredicate getPredicate() {
      return predicate;
   }

   public String getOutput() {
      return output;
   }
}
