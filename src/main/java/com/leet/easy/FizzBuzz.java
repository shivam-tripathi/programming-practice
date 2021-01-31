package com.leet.easy;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
  public List<String> fizzBuzz(int n) {
    var l = new ArrayList<String>();
    for (int i = 1; i <= n; i++) {
      if (i % 15 == 0) {
        l.add("FizzBuzz");
      } else if (i % 3 == 0) {
        l.add("Fizz");
      } else if (i % 5 == 0) {
        l.add("Buzz");
      } else {
        l.add(String.valueOf(i));
      }
    }
    return l;
  }
}
