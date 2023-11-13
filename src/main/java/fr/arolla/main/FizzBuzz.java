package fr.arolla.main;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

   private final List<FizzBuzzRule> rules = new ArrayList<>();

   FizzBuzz() {
      rules.add(new FizzBuzzRule(value -> value % 3 == 0, "fizz"));
      rules.add(new FizzBuzzRule(value -> value % 5 == 0, "buzz"));
   }

   public String output(int value) {
      return rules.stream()
            .filter(rule -> rule.getPredicate().test(value))
            .map(FizzBuzzRule::getOutput)
            .reduce(String::concat)
            .orElse(String.valueOf(value));
   }
}
