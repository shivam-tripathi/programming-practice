package com.dsAlgo.search;

/*
You are given a list of n-1 integers and these integers are in the range of 1 to n. 
There are no duplicates in the list. One of the integers is missing in the list. 
Write an efficient code to find the missing integer.

Solutions:
  1. Mathematical solution (summation of n numbers) // O(1)
    i. In case of overflow - add natural number and remove a number in list; finally add the last natural number // O(n)
  2. XOR solution
    i. XOR property: Assume a1 ^ a2 ^ a3 ^ …^ an = a and a1 ^ a2 ^ a3 ^ …^ an-1 = b. Then a ^ b = an. Because
    a ^ a = 0 and 0 ^ a = a.
    ii. XOR all nums, XOR all natural numbers and then xor these values
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FindTheMissingNumberSolution {
  public int summation(int[] nums) {
    int n = nums.length + 1; // total expected nums
    int expectedSum = n * (n + 1) / 2;
    int sum = 0;
    for (Integer num : nums) {
      sum += num;
    }
    return expectedSum - sum;
  }

  public int summationOverflowHandler(int[] nums) {
    int total = 0;
    for (int i = 1; i <= nums.length; i++) {
      total = total + i - nums[i - 1];
    }

    // Add last number
    total += nums.length + 1;

    return total;
  }

  public int xorBased(int[] nums) {
    int xorTotal = 0, xorRunning = 0;
    for (int i = 1; i <= nums.length; i++) {
      xorTotal ^= i;
      xorRunning ^= nums[i - 1];
    }

    return xorTotal ^ xorRunning ^ (nums.length + 1);
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
    System.out.println(sol.xorBased(nums));
  }
}
