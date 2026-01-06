package com.leet.medium;

import java.util.Stack;

public class IncreasingTripletSubsequence {
  // Find and store the minimum number we have encountered a number with another number less than it
  // If the present number is less than this number, total subsequence becomes of size 3
  public static boolean increasingTriplet(int[] nums) {
    int n = nums.length;
    int min = nums[0];
    int minIdxWithOneLess = -1;
    for (int i = 1; i < n; i++) {
      if (nums[i] > min) {
        if (minIdxWithOneLess == -1) {
          minIdxWithOneLess = i;
        } else {
          if (nums[i] > nums[minIdxWithOneLess]) {
            return true;
          } else if (nums[i] < nums[minIdxWithOneLess]) {
            minIdxWithOneLess = i;
          }
        }
      } else {
        min = nums[i];
      }
    }
    return false;
  }

  public static boolean increasingTriplet2(int[] nums) {
    int min = nums[0], secondMin = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < min) { // if it is the minimum till now
        min = nums[i];
      } else if (nums[i] < secondMin) { // second minimum till now
        secondMin = nums[i];
      } else {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
  }
}
