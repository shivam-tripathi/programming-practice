package com.leet.medium;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * Medium
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 * Return the shortest such subarray and output its length.
 * Example 1:
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 0
 * Example 3:
 * Input: nums = [1]
 * Output: 0
 * Constraints:
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * Follow up: Can you solve it in O(n) time complexity?
 */

public class ShortestUnsortedContinuousSubarray {
  // O(n) solution
  // Algorithm:
  // 1. First find where ascending sorting breaks up from the front traversal (lets say b1)
  // 2. If it changes indeed, find where descending sorting breaks up from reverse traversal (lets say b2)
  // 3. Now find max and min number between the two break points
  // 4. Now in the range 0->b1 find if any number is greater than the minimal between b1 and b2. As remaining numbers are
  // already sorted in range 0->b1, we can then extend b1 to point to this number
  // 5. Now in the range b2<-len-1 find if any number is less than the maximum between b1 and b2. As remaining numbers are
  // already sorted sorted in range b2<-len-1, we can extend b2 to point to this number
  // 6. return b2-b1+1 if b1,b2 != -1. Else 0.
  public int findUnsortedSubarray(int[] nums) {
    int b1 = -1, b2 = -1;
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] > nums[i + 1]) {
        b1 = i;
        break;
      }
    }
    // 0123456
    // 1246357
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] < nums[i - 1]) {
        b2 = i;
        break;
      }
    }
    if (b1 != -1 && b2 != -1) {
      int minB = Integer.MAX_VALUE, maxB = Integer.MIN_VALUE;
      for (int i = b1; i <= b2; i++) {
        minB = Math.min(minB, nums[i]);
        maxB = Math.max(maxB, nums[i]);
      }
      for (int i = 0; i < b1; i++) {
        if (nums[i] > minB) {
          b1 = i;
          break;
        }
      }
      for (int i = nums.length - 1; i > b2; i--) {
        if (nums[i] < maxB) {
          b2 = i;
          break;
        }
      }
    }

    return b2 == -1 && b1 == -1 ? 0 : b2 - b1 + 1;
  }

  public static void main(String[] args) {

  }
}
