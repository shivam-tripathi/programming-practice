/**
 * https://leetcode.com/problems/fizz-buzz/
 * Write a program that outputs the string representation of numbers from 1 to n.
 *
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 *
 * Example:
 *
 * n = 15,
 *
 * Return:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 */


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
