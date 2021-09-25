package com.leet.easy;

/*
1137. N-th Tribonacci Number
https://leetcode.com/problems/n-th-tribonacci-number/
Easy

The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

Example 1:
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4

Example 2:
Input: n = 25
Output: 1389537

Constraints:
    0 <= n <= 37
    The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
* */

public class NthTribonacciNumber {
  int tribonacci(int n) {
    if (n < 3) return (n + 1) / 2;
    int[] seq = new int[] {0, 1, 1};
    for (int i = 3; i <= n; i++) {
      seq[i%3] = seq[0] + seq[1] + seq[2];
    }
    return seq[n%3];
  }
}
