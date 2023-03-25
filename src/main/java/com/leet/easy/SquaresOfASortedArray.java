package com.leet.easy;

import java.util.Arrays;

public class SquaresOfASortedArray {
  public static int[] sortedSquares(int[] nums) {
    int[] squares = new int[nums.length];
    int index = nums.length - 1;

    int low = 0, high = nums.length - 1;

    while (low <= high) {
      int lowItemSquare = nums[low] * nums[low];
      int highItemSquare = nums[high] * nums[high];
      if (lowItemSquare > highItemSquare) {
        squares[index--] = lowItemSquare;
        low++;
      } else {
        squares[index--] = highItemSquare;
        high--;
      }
    }
    return squares;
  }

  public static void main(String[] args) {
    int[] nums = new int[] { -4, -1, 0, 3, 10 };
    System.out.println(Arrays.toString(sortedSquares(nums)));
  }
}
