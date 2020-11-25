package com.dsAlgo.search;

/*
You are given a list of n-1 integers and these integers are in the range of 1 to n. 
There are no duplicates in the list. One of the integers is missing in the list. 
Write an efficient code to find the missing integer.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FindTheMissingNumberSolution {
  public int summation(int[] nums) {
    int n = nums.length + 1; // n is one more than nums
    int expectedSum = (n + 1) * (n + 2) / 2;
    int sum = 0;
    for (Integer num : nums) {
      sum += num;
    }
    return expectedSum - sum;
  }

  public int summationOverflowHandler(int[] nums) {
    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      total += i;
      total -= nums[i];
    }

    return total;
  }
}

public class FindTheMissingNumber {
  public static void main(String[] args) {
    List<Integer> numsList = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      if (i == 15) {
        continue;
      }
      numsList.add(i);
    }
    Collections.shuffle(numsList);

    int[] nums = numsList.stream().mapToInt(i -> i).toArray();

    FindTheMissingNumberSolution sol = new FindTheMissingNumberSolution();
    System.out.println(sol.summation(nums));
    System.out.println(sol.summationOverflowHandler(nums));
  }
}

/*
Method 1: Summation 
  - Handle overflow by adding one of natural number and deleting the element at
  the number position
  -
*/