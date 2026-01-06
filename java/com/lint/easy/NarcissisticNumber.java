package com.lint.easy;
/**
 * 147 · Narcissistic Number
 * https://www.lintcode.com/problem/147/
 * Algorithms
 * Easy
 *
 * Description
 *
 * Narcissistic Number is a number that is the sum of its own digits each raised to the power of the number of digits.
 * See wiki
 * For example the 3-digit decimal number 153 is a narcissistic number because 153 = 13 + 53 + 33.
 * And the 4-digit decimal number 1634 is a narcissistic number because 1634 = 14 + 64 + 34 + 44.
 * Given n, return all narcissistic numbers with n digits.
 * You may assume n is smaller than 8.
 *
 * Example
 * Example 1:
 * Input: 1
 * Output: [0,1,2,3,4,5,6,7,8,9]
 *
 * Example 2:
 * Input:  2
 * Output: []
 *
 * Explanation: There is no Narcissistic Number with 2 digits.
 */

import java.util.*;

public class NarcissisticNumber {
  /**
   * @param n: The number of digits
   * @return: All narcissistic numbers with n digits
   */
  public List<Integer> getNarcissisticNumbers(int n) {
    double[] powers = new double[10];
    for (int i = 0; i < 10; i++) {
      powers[i] = Math.pow(i, n);
    }
    List<Integer> ans = new ArrayList<Integer>();
    for (int i = (int) Math.pow(10, n - 1); i < Math.pow(10, n); i++) {
      int num = i;
      double sum = 0;
      while (num != 0) {
        sum += powers[num % 10];
        num /= 10;
      }
      if (i == sum) ans.add(i);
    }
    if (n == 1) ans.add(0, 0);
    return ans;
  }
}
