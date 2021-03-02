package com.leet.easy;

/**
 * 645. Set Mismatch
 * https://leetcode.com/problems/set-mismatch/
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some
 * error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one
 * number and loss of another number.
 * You are given an integer array nums representing the data status of this set after the error.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Example 2:
 * Input: nums = [1,1]
 * Output: [1,2]
 * Constraints:
 * 2 <= nums.length <= 104
 * 1 <= nums[i] <= 104
 */

public class SetMismatch {
  // For some reason, looping twice is faster than looping once.
  public int[] findErrorNums(int[] nums) {
    int sum = 0, twice = -1, n = nums.length;
    int[] found = new int[n + 1];
    for (int num : nums) {
      found[num]++;
    }
    int[] res = new int[2];
    for (int i = 1; i <= n; i++) {
      if (found[i] == 2) res[0] = i;
      else if (found[i] == 0) res[1] = i;
    }
    // int missing = (n * (n + 1)) / 2 - sum;
    // return new int[]{twice, missing};
    return res;
  }
}
