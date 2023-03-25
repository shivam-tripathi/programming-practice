package com.dsAlgo.dp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Time complexity for recursion sans dynamic programming - exponential T(n) = T(n-1) + T(n-2) (2^n)
 * Time Complexity for dynamic programming - O(n)
 *
 */

public class Fibonacci {
  static List<Integer> recurNums = new ArrayList<>(Arrays.asList(0, 1));

  static int recursionTopDown(int n) {
    // Base case
    if (n < 0) {
      return 0;
    }

    // Use cache
    if (n < recurNums.size()) {
      return recurNums.get(n);
    }

    // Compute and store. Call the smaller iteration first to maintain order of insertion
    int ans = recursionTopDown(n - 2) + recursionTopDown(n - 1);
    recurNums.add(ans);
    return ans;
  }

  static int loopBottomUp(int n) {
    List<Integer> nums = new ArrayList<>(Arrays.asList(0, 1)); // Make this global for dp to take effect
    // Start looping from last computed value till n. Assumption: n-1 and n-2 values exist
    for (int i = nums.size(); i <= n; i++) {
      nums.add(nums.get(i - 1) + nums.get(i - 2));
    }

    return nums.get(n);
  }

  static int twoAtATime(int n) {
    int a = 1, b = 1;
    for (int i = 3; i <= n; i++) {
      int temp = b;
      b = a + b;
      a = temp;
    }

    return b;
  }

  static int logNMatrix(int n) {
    return 0;
  }

  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    int n = sc.nextInt();
    int n = (int)(Math.random() * 10) + 1;
    n = 1;
    System.out.println("Number: " + n);
    try {
      System.out.println(recursionTopDown(n));
      System.out.println(loopBottomUp(n));
      System.out.println(twoAtATime(n));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
