package com.leet.medium;

/**
 * 46. Permutations
 * https://leetcode.com/problems/permutations/
 * Medium
 *
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any
 * order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 *
 * Constraints:
 *     1 <= nums.length <= 6
 *     -10 <= nums[i] <= 10
 *     All the integers of nums are unique.
 */

import java.util.*;

public class Permutations {
  public List<List<Integer>> solve(int[] nums, int pos, int visited, Integer[] perm, List<List<Integer>> ans) {
    // If all elements have been inserted, save this permutation in the ans
    if (pos >= nums.length) {
      List<Integer> permutation = new ArrayList<>();
      Collections.addAll(permutation, perm); // Arrays.asList mutates if the underlying array mutates :(
      ans.add(permutation);
      return ans;
    }

    for (int i = 0; i < nums.length; i++) {
      // If the number has not been visited, add this number at this position and continue to fill other numbers
      if ((visited & (1 << i)) == 0) {
        perm[pos] = nums[i];
        solve(nums, pos + 1, visited | (1 << i), perm, ans);
      }
    }

    return ans;
  }

  public List<List<Integer>> permute(int[] nums) {
    return solve(nums, 0, 0, new Integer[nums.length], new ArrayList<>());
  }
}
