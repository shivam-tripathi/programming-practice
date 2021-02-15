package com.leet.easy;

/**
 * 1342. Number of Steps to Reduce a Number to Zero
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * Given a non-negative integer num, return the number of steps to reduce it to zero.
 * If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 */

public class NumberOfStepsToReduceANumberToZero {
  public static int numberOfSteps(int num) {
    int count = 0;
    while (num != 0) {
      count += num % 2 == 1 ? 2 : 1;
      num >>= 1;
    }
    return Math.max(0, count - 1);
  }

  public static void main(String[] args) {
    System.out.println(numberOfSteps(14));
  }
}
