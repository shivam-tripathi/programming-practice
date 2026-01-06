package com.leet.medium;

/**
 * 1680. Concatenation of Consecutive Binary Numbers
 * https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
 * Medium
 *
 * Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations
 * of 1 to n in order, modulo 109 + 7.
 * Example 1:
 * Input: n = 1
 * Output: 1
 * Explanation: "1" in binary corresponds to the decimal value 1.
 * Example 2:
 * Input: n = 3
 * Output: 27
 * Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds to the decimal value 27.
 * Example 3:
 * Input: n = 12
 * Output: 505379714
 * Explanation: The concatenation results in "1101110010111011110001001101010111100".
 * The decimal value of that is 118505380540.
 * After modulo 109 + 7, the result is 505379714.
 * Constraints:
 * 1 <= n <= 105
 */

public class ConcatenationOfConsecutiveBinaryNumbers {
  public static int concatenatedBinary(int n) {
    int ans = 0, offset = 1;
    int mod = (int) 1e9 + 7;
    while (n != 0) {
      int copy = n;
      while (copy != 0) {
        if ((copy & 1) == 1) {
          ans = (ans + offset) % mod;
        }
        copy = copy >> 1;
        offset = (offset << 1) % mod;
      }
      n--;
    }
    return ans % mod;
  }

  public static int concatenatedBinary2(int n) {
    int ans = 0;
    int mod = (int) (1e9 + 7);
    for (int i = 1; i <= n; i++) {
      int bits = (int) (Math.log(i) / Math.log(2)) + 1;
      ans = ((ans << bits) + i) % mod;
    }
    return ans % mod;
  }

  public static void main(String[] args) {
    System.out.println(concatenatedBinary(12));
    System.out.println(concatenatedBinary2(12));
  }
}
