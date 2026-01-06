package com.leet.easy;

import java.util.Arrays;

/**
 * 594. Longest Harmonious Subsequence
 * https://leetcode.com/problems/longest-harmonious-subsequence/
 * Easy
 *
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is
 * exactly 1.
 *
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible
 * subsequences.
 *
 * A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [1,1,1,1]
 * Output: 0
 *
 * Constraints:
 *     1 <= nums.length <= 2 * 104
 *     -109 <= nums[i] <= 109
 */

public class LongestHarmoniousSubsequence {
  public int findLHS(int[] nums) {
    if (nums.length == 0) return 0;
    Arrays.sort(nums);
    int prev = -1, prevCount = -1, cur = nums[0], curCount = 0, ans = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == cur) {
        curCount++;
      } else {
        if (prevCount != -1 && prev == cur-1 && ans < (curCount + prevCount)) {
          ans = prevCount + curCount;
        }
        prev = cur;
        prevCount = curCount;
        cur = nums[i];
        curCount = 1;
      }
    }
    if (prevCount != -1 && prev == cur-1 && ans < (prevCount + curCount)) {
      ans = prevCount + curCount;
    }
    return ans;
  }
}
