package com.leet.medium;

/**
 * 137. Single Number II
 * Medium
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 * Constraints:
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each element in nums appears exactly three times except for one element which appears once.
 */

public class SingleNumberII {
  public int singleNumber(int[] nums) {
    int[] dig = new int[32];
    for (int num : nums) {
      int pos = 0;
      while (num != 0) {
        dig[pos++] += num & 1;
        num >>>= 1;
      }
    }
    int ans = 0;
    for (int i = 0; i < 32; i++) {
      ans += (dig[i] % 3) << i;
    }
    return ans;
  }

  public int singleNumberMethod2(int[] nums) {
    return 0;
  }
}
