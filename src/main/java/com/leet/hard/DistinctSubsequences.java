package com.leet.hard;

import java.util.Arrays;

public class DistinctSubsequences {
  int[][] dp;

  int solve(char[] s, char[] t, int pos, int idx) {
    if (pos == t.length) {
      return 1;
    }

    if (idx == s.length) {
      return 0;
    }

    if (dp[pos][idx] != -1) return dp[pos][idx];

    int ans = 0;

    if (s[idx] == t[pos]) {
      ans = solve(s, t, pos + 1, idx + 1);
    }

    ans += solve(s, t, pos, idx + 1);

    dp[pos][idx] = ans;

    return ans;
  }

  public int numDistinct(String s, String t) {
    dp = new int[t.length()][s.length()];
    for (int i = 0; i < t.length(); i++) {
      Arrays.fill(dp[i], -1);
    }
    return solve(s.toCharArray(), t.toCharArray(), 0, 0);
  }
}
