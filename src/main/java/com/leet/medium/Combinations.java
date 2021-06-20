package com.leet.medium;

import java.util.*;

/**
 * 77. Combinations
 * https://leetcode.com/problems/combinations/submissions/
 * Medium
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Constraints:
 * 1 <= n <= 20
 * 1 <= k <= n
 */

public class Combinations {
  public List<List<Integer>> solve(
          int n, int k, int pos, int start, Integer[] comb, List<List<Integer>> ans
  ) {

    if (pos == k) {
      List<Integer> combination = new ArrayList<>(Arrays.asList(comb));
      ans.add(combination);
      return ans;
    }

    // Optimisation to make it reach 100%
    // If we are inserting at pos, we need atleast k-pos elements remaining
    // So the end = n - (k-pos) + 1
    // eg k = 5 n = 10 and pos = 3
    // So next start must atleast be 9, so we can 3 items + (9, 10) = items
    // 9 = 10-5+3+1
    // eg k = 6 n = 11 pos = 2
    // Next start must atleast be 11 - (6-2) + 1 = 8 so that 2 items + (8,9,10,11) = 6 items
    for (int i = start; i <= n - k + pos + 1; i++) {
      comb[pos] = i;
      solve(n, k, pos + 1, i + 1, comb, ans);
    }

    return ans;
  }

  public List<List<Integer>> combine(int n, int k) {
    return solve(n, k, 0, 1, new Integer[k], new ArrayList<>());
  }
}
