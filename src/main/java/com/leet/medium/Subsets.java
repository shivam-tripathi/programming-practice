package com.leet.medium;

/**
 * 78. Subsets
 * https://leetcode.com/problems/subsets/
 * Medium
 *
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 *     1 <= nums.length <= 10
 *     -10 <= nums[i] <= 10
 *     All the numbers of nums are unique.
 */

import java.util.*;

public class Subsets {
  public List<List<Integer>> solve(int[] nums, int pos, List<Integer> set, List<List<Integer>> ans) {
    if (pos == nums.length) {
      ans.add(new ArrayList<>(set));
      return ans;
    }
    solve(nums, pos + 1, set, ans);
    set.add(nums[pos]);
    solve(nums, pos + 1, set, ans);
    set.remove(set.size() - 1);
    return ans;
  }

  public List<List<Integer>> subsets(int[] nums) {
    return solve(nums, 0, new ArrayList<>(), new ArrayList<>());
  }
}
