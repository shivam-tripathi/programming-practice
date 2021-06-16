package com.leet.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. Gray Code
 * https://leetcode.com/problems/gray-code/
 * Medium
 *
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 *     Every integer is in the inclusive range [0, 2n - 1],
 *     The first integer is 0,
 *     An integer appears no more than once in the sequence,
 *     The binary representation of every pair of adjacent integers differs by exactly one bit, and
 *     The binary representation of the first and last integers differs by exactly one bit.
 *
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: [0,1]
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 16
 */

/**
 *   1
 *   0 0
 *   ---
 *   1 1
 *
 *   2
 *   00 0
 *   01 0
 *   ---
 *   11 0
 *   10 0
 *
 *   3
 *   000 0
 *   001 1
 *   011 3
 *   010 2
 *   ---
 *   110 6
 *   111 7
 *   101 5
 *   100 4
 */
public class GrayCode {
  public List<Integer> grayCode(int n) {
    var base = List.of(0, 1);
    for (int i = 1; i <= n; i++) {
      if (i >= n) {
        return base;
      }
      var next = new ArrayList<>(base);
      for (int j = base.size() - 1; j >= 0; j--) {
        next.add((1 << i) | base.get(j));
      }
      base = next;
    }
    return base;
  }
}

