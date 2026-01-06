package com.leet.medium;

/**
 * 991. Broken Calculator
 * https://leetcode.com/problems/broken-calculator/
 * Medium
 *
 * On a broken calculator that has a number showing on its display, we can perform two operations:
 *
 * Double: Multiply the number on the display by 2, or;
 * Decrement: Subtract 1 from the number on the display.
 *
 * Initially, the calculator is displaying the number X.
 *
 * Return the minimum number of operations needed to display the number Y.
 *
 *
 *
 * Example 1:
 *
 * Input: X = 2, Y = 3
 * Output: 2
 * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
 *
 * Example 2:
 *
 * Input: X = 5, Y = 8
 * Output: 2
 * Explanation: Use decrement and then double {5 -> 4 -> 8}.
 *
 * Example 3:
 *
 * Input: X = 3, Y = 10
 * Output: 3
 * Explanation:  Use double, decrement and double {3 -> 6 -> 5 -> 10}.
 *
 * Example 4:
 *
 * Input: X = 1024, Y = 1
 * Output: 1023
 * Explanation: Use decrement operations 1023 times.
 *
 *
 *
 * Note:
 *
 * 1 <= X <= 10^9
 * 1 <= Y <= 10^9
 */

public class BrokenCalculator {
  public static int brokenCalc(int X, int Y) {
    int ans = 0;
    while (Y > X) {
      if (Y % 2 == 1) Y++;
      else Y /= 2;
      ans++;
    }
    return ans + (X - Y);
  }

  public static void main(String[] args) {
    System.out.println(brokenCalc(1, 1000000000));
  }
}

/**
 * Approach 1: Work Backwards
 * Intuition
 * Instead of multiplying by 2 or subtracting 1 from X, we could divide by 2 (when Y is even) or add 1 to Y.
 * The motivation for this is that it turns out we always greedily divide by 2:
 * If say Y is even, then if we perform 2 additions and one division, we could instead perform one division and one
 * addition for less operations [(Y+2) / 2 vs Y/2 + 1].
 * If say Y is odd, then if we perform 3 additions and one division, we could instead perform 1 addition, 1
 * division, and 1 addition for less operations [(Y+3) / 2 vs (Y+1) / 2 + 1].
 *
 * Algorithm
 * While Y is larger than X, add 1 if it is odd, else divide by 2. After, we need to do X - Y additions to reach X.
 *
 * We can also think about it this way. From X to Y, the only way to go is either -1 or x2. Any such path leading to Y
 * also means that there exists a path from Y back to X through +1 or /2. The key is that going from X to Y is not
 * deterministic, because one can choose to x2, or -1 first and then x2. However, going from Y to X is deterministic,
 * because we cannot do /2 when Y is an odd number. Therefore, whenever Y is an odd number, the only thing we can do is
 * +1 and then /2. Therefore, going from Y to X is straightforward.
 */
