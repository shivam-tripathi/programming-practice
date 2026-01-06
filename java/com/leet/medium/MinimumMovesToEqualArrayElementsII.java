package com.leet.medium;

/**
 * Minimum Moves to Equal Array Elements II
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 * Medium
 *
 * Given an integer array nums of size n, return the minimum number of moves
 * required to make all array elements equal. In one move, you can increment or
 * decrement an element of the array by 1.
 *
 * Example 1: Input: nums = [1,2,3] Output: 2
 *
 * Explanation: Only two moves are needed (remember each move increments or
 * decrements one element): [1,2,3] => [2,2,3] => [2,2,2]
 *
 * Example 2: Input: nums = [1,10,2,9] Output: 16
 *
 * Constraints:
 *
 * n == nums.length
 *
 * 1 <= nums.length <= 105
 *
 * -109 <= nums[i] <= 109
 */

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {
  public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int median;
    if (nums.length % 2 == 1) {
      median = nums[nums.length / 2];
    } else {
      median = (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2;
    }
    int ans = 0;
    for (int num : nums) {
      ans += Math.abs(num - median);
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 4, 5, 10, 15 };
    for (int i = arr[0]; i < arr[arr.length - 1]; i++) {
      int ans = 0;
    }
  }
}
