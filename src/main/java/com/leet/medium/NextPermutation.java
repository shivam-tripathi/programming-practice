package com.leet.medium;

/**
 * 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
 * Medium
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 *
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 100
 *     0 <= nums[i] <= 100
 */

import java.util.Arrays;
import java.util.Map;

public class NextPermutation {
  public static void nextPermutation(int[] nums) {
    if (nums.length <= 1) return;

    int pos = -1, size = nums.length;
    for (int i = size - 2; i >= 0; i--) {
      if (nums[i] < nums[i+1]) {
        pos = i;
        break;
      }
    }

    if (pos == -1) {
      Arrays.sort(nums);
      return;
    }

    int minPos = pos;
    for (int i = pos + 1; i < size; i++) {
      if (nums[i] > nums[pos] && nums[i] < nums[minPos]) {
        minPos = i;
      }
    }

    int tmp = nums[pos];
    nums[pos] = nums[minPos];
    nums[minPos] = tmp;

    Arrays.sort(nums, pos+1, size);
  }
}


