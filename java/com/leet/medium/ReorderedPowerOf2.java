package com.leet.medium;

/**
 * 869. Reordered Power of 2
 * https://leetcode.com/problems/reordered-power-of-2/
 * Medium
 *
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 *
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 *
 * Example 2:
 *
 * Input: 10
 * Output: false
 *
 * Example 3:
 *
 * Input: 16
 * Output: true
 *
 * Example 4:
 *
 * Input: 24
 * Output: false
 *
 * Example 5:
 *
 * Input: 46
 * Output: true
 *
 *
 *
 * Note:
 *
 *     1 <= N <= 10^9
 */

public class ReorderedPowerOf2 {
  public boolean match(int[] digitsOriginal, int num) {
    int[] digits = new int[10];
    while (num != 0) {
      digits[num % 10]++;
      num /= 10;
    }

    for (int i = 0; i < 10; i++) {
      if (digits[i] - digitsOriginal[i] != 0) return false;
    }

    return true;
  }

  public boolean reorderedPowerOf2(int num) {
    int order = (int)(Math.log(num) / Math.log(10));
    int[] digits = new int[10];
    while (num != 0) {
      digits[num % 10]++;
      num /= 10;
    }

    int base = 1;
    for (int i = 0; i <= 30; i++) {
      int baseOrder = (int)(Math.log(base) / Math.log(10));
      if (baseOrder >= order && match(digits, base)) {
        return true;
      }
      base <<= 1;
    }
    return false;
  }
}
