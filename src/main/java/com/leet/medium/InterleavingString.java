package com.leet.medium;

/**
 * 97. Interleaving String
 * https://leetcode.com/problems/interleaving-string/
 * Medium
 *
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 *     s = s1 + s2 + ... + sn
 *     t = t1 + t2 + ... + tm
 *     |n - m| <= 1
 *     The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 *
 * Note: a + b is the concatenation of strings a and b.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 * Example 3:
 *
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 */

public class InterleavingString {
  String s1;
  String s2;
  String s3;
  int[][] dp;

  public boolean match(int p1, int p2, int p3) {
    if (p3 == s3.length() && p2 == s2.length() && p1 == s1.length()) return true;

    if (dp[p1][p2] != 0) return dp[p1][p2] == 1;

    boolean ans = false;

    if (p1 < s1.length() && p3 < s3.length() && s1.charAt(p1) == s3.charAt(p3)) {
      ans = match(p1 + 1, p2, p3 + 1);
    }

    if (!ans && p2 < s2.length() && p3 < s3.length() && s2.charAt(p2) == s3.charAt(p3)) {
      ans = match(p1, p2 + 1, p3 + 1);
    }

    dp[p1][p2] = ans ? 1 : -1;

    return ans;
  }

  public boolean isInterleave(String str1, String str2, String str3) {
    s1 = str1;
    s2 = str2;
    s3 = str3;
    dp = new int[s1.length() + 1][s2.length() + 1];
    return match(0, 0, 0);
  }
}
