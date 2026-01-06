package com.practice.spoj;

/**
 * FARIDA - Princess Farida
 * https://www.spoj.com/problems/FARIDA/
 * #dynamic-programming
 *
 * Once upon time there was a cute princess called Farida living in a castle with her father, mother and uncle. On the
 * way to the castle there lived many monsters. Each one of them had some gold coins. Although they are monsters they will not hurt. Instead they will give you the gold coins, but if and only if you didn't take any coins from the monster directly before the current one. To marry princess Farida you have to pass all the monsters and collect as many coins as possible. Given the number of gold coins each monster has, calculate the maximum number of coins you can collect on your way to the castle.
 * Input
 *
 * The first line of input contains the number of test cases. Each test case starts with a number N, the number of
 * monsters, 0 <= N <= 10^4. The next line will have N numbers, number of coins each monster has, 0 <= The number of
 * coins with each monster <= 10^9. Monsters described in the order they are encountered on the way to the castle.
 * Output
 *
 * For each test case print “Case C: X” without quotes. C is the case number, starting with 1. X is the maximum number
 * of coins you can collect.
 * Example
 *
 * Input:
 * 2
 * 5
 * 1 2 3 4 5
 * 1
 * 10
 *
 * Output:
 * Case 1: 9
 * Case 2: 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FARIDA {
  static int n;
  static int[] coins;
  static double[] dp;

  static double solve(int pos) {
    if (pos >= coins.length) return 0;
    if (dp[pos] != 0) return dp[pos];
    double with = coins[pos] + solve(pos + 2);
    double without = solve(pos + 1);
    dp[pos] = Math.max(with, without);
    return dp[pos];
  }

  static double solve() {
    dp = new double[coins.length];
    return solve(0);
  }

  public static void test() {
    coins = new int[]{2, 5, 5, 4, 1};
    System.out.printf("Case %d: %.0f\n", solve());
  }

  public static void main(String[] args) throws IOException {
    var obj = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(obj.readLine());
    for (int t = 0; t < tests; t++) {
      n = Integer.parseInt(obj.readLine());
      coins = new int[n];
      String[] inp = obj.readLine().split(" ");
      for (int i = 0; i < n; i++) {
        coins[i] = Integer.parseInt(inp[i]);
      }
      System.out.printf("Case %d: %.0f\n", t + 1, solve());
    }
  }
}
