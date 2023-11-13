package fr.arolla.main;

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
