package com.leet.medium;

/**
 * 1010. Pairs of Songs With Total Durations Divisible by 60 Medium
 *
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 *
 * You are given a list of songs where the ith song has a duration of time[i]
 * seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds
 * is divisible by 60. Formally, we want the number of indices i, j such that i
 * < j with (time[i] + time[j]) % 60 == 0.
 *
 * Example 1: Input: time = [30,20,150,100,40] Output: 3 Explanation: Three
 * pairs have a total duration divisible by 60: (time[0] = 30, time[2] = 150):
 * total duration 180 (time[1] = 20, time[3] = 100): total duration 120 (time[1]
 * = 20, time[4] = 40): total duration 60
 *
 * Example 2: Input: time = [60,60,60] Output: 3 Explanation: All three pairs
 * have a total duration of 120, which is divisible by 60.
 *
 * Constraints: 1 <= time.length <= 6 * 104 1 <= time[i] <= 500
 */

public class PairOfSongsWithTotalDurationDivisibleBy60 {
  public static int numPairsDivisibleBy60(int[] time) {
    int[] mod = new int[60];
    for (int j : time) {
      mod[j % 60]++;
    }
    int ans = (mod[0] * (mod[0] - 1) / 2) + (mod[30] * (mod[30] - 1) / 2);
    for (int idx = 1; idx < 30; idx++) {
      ans += mod[idx] * mod[60 - idx];
    }
    return ans;
  }

  public static int numPairsDivisibleBy60_2(int[] time) {
    int[] mod = new int[60];
    int ans = 0;
    for (int t : time) {
      if (t % 60 == 0) {
        ans += mod[0]; // what ever exists till now can make a pair with this element
      } else {
        ans += mod[60 - (t % 60)]; // what ever exists till now can make a pair with this element
      }
      mod[t % 60]++;
    }
    return ans;
  }

  public static void main(String[] args) {
    int ans = numPairsDivisibleBy60(new int[] { 418, 204, 77, 278, 239, 457, 284, 263, 372, 279, 476, 416, 360, 18 });
    System.out.println(ans);

    ans = numPairsDivisibleBy60_2(new int[] { 418, 204, 77, 278, 239, 457, 284, 263, 372, 279, 476, 416, 360, 18 });
    System.out.println(ans);
  }
}
