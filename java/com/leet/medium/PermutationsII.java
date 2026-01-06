package com.leet.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 47.
 *
 * Permutations II
 *
 * https://leetcode.com/problems/permutations-ii/
 *
 * Medium
 *
 * Given a collection of numbers, nums, that might contain duplicates, return
 * all possible unique permutations in any order.
 *
 * Example 1: Input: nums = [1,1,2] Output: [[1,1,2], [1,2,1], [2,1,1]]
 *
 * Example 2: Input: nums = [1,2,3] Output:
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 8 -10 <= nums[i] <= 10
 */

public class PermutationsII {
  List<List<Integer>> ans = new ArrayList<>();

  public List<List<Integer>> solve(int[] nums, int pos, Map<Integer, Integer> countMap, List<Integer> state) {
    if (pos >= nums.length) {
      List<Integer> permutation = new ArrayList<>();
      permutation.addAll(state);
      ans.add(permutation);
      return ans;
    }

    for (int num : countMap.keySet()) {
      int c = countMap.get(num);
      if (c > 0) {
        state.set(pos, num);
        countMap.put(num, c - 1);
        solve(nums, pos + 1, countMap, state);
        countMap.put(num, c);
      }
    }

    return ans;
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    Map<Integer, Integer> countMap = new HashMap<>();
    var state = new ArrayList<Integer>();
    for (int num : nums) {
      countMap.put(num, countMap.getOrDefault(num, 0) + 1);
      state.add(num);
    }
    return solve(nums, 0, countMap, state);
  }
}
