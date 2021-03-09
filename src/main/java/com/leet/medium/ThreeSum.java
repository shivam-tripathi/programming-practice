package com.leet.medium;

/**
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 * Medium
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Notice that the solution set must not contain duplicate triplets.
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * Input: nums = []
 * Output: []
 * Example 3:
 * Input: nums = [0]
 * Output: []
 * Constraints:
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */

import java.util.*;

public class ThreeSum {
  public void twoSum(int[] nums, int idx, int sum, List<List<Integer>> ans) {
    int low = idx, high = nums.length - 1;
    int base = nums[idx - 1];
    while (low < high) {
      int iter = nums[low] + nums[high];
      if (iter == sum) {
        ans.add(List.of(base, nums[low], nums[high]));
        while (low < high && nums[low + 1] == nums[low]) low++;
        while (high > low && nums[high - 1] == nums[high]) high--;
        low++;
        high--;
      } else if (iter < sum) {
        while (low < high && nums[low + 1] == nums[low]) low++;
        low++;
      } else {
        while (high > low && nums[high - 1] == nums[high]) high--;
        high--;
      }
    }
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      twoSum(nums, i + 1, -num, ans);
      while (i < nums.length - 1 && nums[i + 1] == nums[i]) i++; // skip all same values
    }
    return ans;
  }
}
