package com.leet.medium;

/**
 * 29. Divide Two Integers
 * https://leetcode.com/problems/divide-two-integers/
 * Medium
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part. For example,
 * truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * Note:
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
 * [−231,  231 − 1]. For this problem, assume that your function returns 231 − 1 when the division result overflows.
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 * Example 3:
 * Input: dividend = 0, divisor = 1
 * Output: 0
 * Example 4:
 * Input: dividend = 1, divisor = 1
 * Output: 1
 * Constraints:
 * -231 <= dividend, divisor <= 231 - 1
 * divisor != 0
 */

public class DivideTwoIntegers {
  /**
   * We can handle cases where both are positive or both are negative. But cross handling is not easy.
   * So we convert all positive numbers into negative numbers (because we cannot do vice versa).
   * Easy way to flip the sign is take two's complement (~a + 1) = -a
   *
   * Initially, we know INT_MIN / -1 would result in overflow. So we get it out of the way. Next we calculate all values
   * for divisor * 2^x till we overflow. Point of overflow is point of break.
   *
   * Next we start to reverse subtract maximum value from the dividend. Reasoning is that dividend/divisor = ans.
   * This means ans * divisor = dividend (after taking math.ceil of floor). Each number can be represented as a power of
   * 2. So by doing this, we try to reconstruct ans using SUM(val * (i_th power of 2)) = val * SUM(i_the power of 2).
   */
  public static int divide(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE) {
      if (divisor == 1) return Integer.MIN_VALUE;
      if (divisor == -1) return Integer.MAX_VALUE;
    }
    if (divisor == 1) return dividend;

    int dividendCopy = dividend;
    int divisorCopy = divisor;
    boolean ansIsNeg = false;
    if (dividend < 0 && divisor >= 0) {
      ansIsNeg = true;
      divisorCopy = ~divisor + 1;
    }
    if (dividend >= 0 && divisor < 0) {
      ansIsNeg = true;
      dividendCopy = ~dividend + 1;
    }

    int[] arr = new int[32];
    int size = 31;

    for (int i = 0; i < 32; i++) {
      arr[i] = divisorCopy << i;
      if ((arr[i] < 0 && divisorCopy >= 0) || (arr[i] >= 0 && divisorCopy < 0)) {
        size = i;
        break;
      }
    }

    int ans = 0;
    for (int i = size - 1; i >= 0 && dividendCopy != 0; i--) {
      if (dividendCopy >= 0 && divisorCopy >= 0 && arr[i] <= dividendCopy) {
        dividendCopy -= arr[i];
        ans += (1 << i);
      } else if (dividendCopy < 0 && divisorCopy < 0 && arr[i] >= dividendCopy) {
        dividendCopy -= arr[i];
        ans += (1 << i);
      }
    }
    return ansIsNeg && ans > 0 ? (~ans + 1) : ans;
  }

  public static void main(String[] args) {
    int a = Integer.MIN_VALUE;
    int b = 2;
    System.out.printf("%d/%d = %d%n", a, b, divide(a, b));
  }
}
