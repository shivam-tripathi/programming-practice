package com.leet.easy;

/**
 * 746. Min Cost Climbing Stairs
 * https://leetcode.com/problems/min-cost-climbing-stairs/ Easy You are given an
 * integer array cost where cost[i] is the cost of ith step on a staircase. Once
 * you pay the cost, you can either climb one or two steps. You can either start
 * from the step with index 0, or the step with index 1. Return the minimum cost
 * to reach the top of the floor. Example 1: Input: cost = [10,15,20] Output: 15
 * Explanation: Cheapest is: start on cost[1], pay that cost, and go to the top.
 * Example 2: Input: cost = [1,100,1,1,1,100,1,1,100,1] Output: 6 Explanation:
 * Cheapest is: start on cost[0], and only step on 1s, skipping cost[3].
 *
 *
 *
 * Constraints:
 *
 * 2 <= cost.length <= 1000 0 <= cost[i] <= 999
 */

public class MinCostClimbingStairs {
  // Fastest, dp in just two variables
  public int minCostClimbingStairs(int[] cost) {
    int a = 0, b = 0;
    for (int i = cost.length - 1; i >= 0; i--) {
      int tmp = cost[i] + Math.min(a, b);
      b = a;
      a = tmp;
    }
    return Math.min(a, b);
  }

  // Slower, dp in an array
  public int minCostClimbingStairs2(int[] cost) {
    int[] cache = new int[cost.length + 5];
    for (int i = cost.length - 1; i >= 0; i--) {
      cache[i] = cost[i] + Math.min(cache[i + 1], cache[i + 2]);
    }
    return Math.min(cache[0], cache[1]);
  }

  // Slower, messy. Recursion
  int solve(int step, int[] cost, int[] dp) {
    if (step >= cost.length)
      return 0;
    if (dp[step] != 0)
      return dp[step + 1];
    dp[step] = cost[step] + Math.min(solve(step + 1, cost, dp), solve(step + 2, cost, dp));
    return dp[step];
  }

  public int minCostClimbingStairs3(int[] cost) {
    var dp = new int[cost.length + 1];
    return Math.min(solve(0, cost, dp), solve(1, cost, dp));
  }
}
