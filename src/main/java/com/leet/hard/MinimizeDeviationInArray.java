package com.leet.hard;

import java.util.Arrays;

public class MinimizeDeviationInArray {
  static int log(int a, int b) {
    return (int) (Math.log(a) / Math.log(b));
  }
  public static int minimumDeviation(int[] nums) {
    Arrays.sort(nums);
    int median = nums[nums.length / 2];

    if (nums.length % 2 == 0) {
      median = (nums[nums.length / 2] + nums[nums.length / 2 + 1]) / 2;
    }

    int deviation = 0;
    for (int i = 0; i < nums.length; i++) {
      int diff = median - nums[i];
      if (nums[i] % 2 == 0) {
        if (nums[i] > median) {
          int temp = nums[i];
          while (temp > median && nums[i] % 2 == 0) {
            temp /= 2;
          }
          System.out.println("nums[i]=" + nums[i] + ";final_value=" + temp);
          deviation += Math.min((temp - median), 3);
        } else {
          deviation += (median - nums[i]);
        }
      } else {

        int b = nums[i] * (int) Math.pow(2, log(diff, 2));
        System.out.println("nums[i]=" + nums[i] + ";final_value=" + b);
        deviation += (median - b);
      }
    }
    System.out.println(deviation);
    return deviation;
  }

  public static void main(String[] args) {
    minimumDeviation(new int[] { 4,1,5,20,3 });
  }
}
