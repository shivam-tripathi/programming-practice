package com.leet.hard;

/**
 * 87. Scramble String
 * https://leetcode.com/problems/scramble-string/
 * Hard
 * We can scramble a string s to get a string t using the following algorithm:
 *     If the length of the string is 1, stop.
 *     If the length of the string is > 1, do the following:
 *         Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x
 *         and y where s = x + y.
 *         Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may
 *         become s = x + y or s = y + x.
 *         Apply step 1 recursively on each of the two substrings x and y.
 *
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 *
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now and the result string is "rgeat" which is s2.
 * As there is one possible scenario that led s1 to be scrambled to s2, we return true.
 *
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 *
 * Example 3:
 * Input: s1 = "a", s2 = "a"
 * Output: true
 *
 * Constraints:
 *     s1.length == s2.length
 *     1 <= s1.length <= 30
 *     s1 and s2 consist of lower-case English letters.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * This is a very interesting question which merges divide & conquer and dynamic programming into one. What's more,
 * it also requires lots of good optimisations to be added in place as well.
 *
 * Algorithm for scrambling is simple: divide string into parts and then scramble them. These two parts then are merged
 * either in original order or in reverse order.
 * So if we split the current string into two parts s = a.b - the final string f(s) = f(a)f(b) or f(b)f(a)
 * where f is the scrambling function. Armed with this knowledge and applying dynamic programming as well we are able to
 * cut the string into two parts recursively - removing all invalid combinations by checking on length and char freq.
 */
public class ScrambleString {
    Map<String, Boolean> dp = new HashMap<>();
    public boolean solve(String s1, String s2) {
      if (s1.length() != s2.length()) return false;

      if (s1.equals(s2)) return true;

      // If last unit of comparison doesn't match
      if (s1.length() == 1) return false;

      // All chars count must also match. Without this, it doesn't work. Apply all optimisation or loose out.
      int[] chars = new int[26];
      for (int i = 0; i < s1.length(); i++)  {
        chars[s1.charAt(i)-'a']++;
        chars[s2.charAt(i)-'a']--;
      }
      for (int i = 0; i < 26; i++) {
        if (chars[i] != 0) return false;
      }

      // Now as it's divide and merge - at every stage, a portion of string is divided, further
      // scrambled and merged. This merging can be in reverse or straightaway.
      // That means that final string is just two scrambled string which have been merged.
      // Scrambling operation is just reversing and merging. So we will try to reverse every
      // substring and try to compare it with target string (which also will be split).
      // It is essentially a divide and conquer + dynamic programming.
      String key1 = new StringBuilder().append(s1).append(".").append(s2).toString();
      if (dp.containsKey(key1)) return dp.get(key1);
      String key2 = new StringBuilder().append(s2).append(".").append(s1).toString();
      if (dp.containsKey(key2)) return dp.get(key2);

      for (int i = 1; i < s1.length(); i++) {

        String aleft = s1.substring(0, i), aright = s1.substring(i);
        String bleft = s2.substring(0, i), bright = s2.substring(i);

        var left  = solve(aleft, bleft);
        var right = solve(aright, bright);
        if (left && right) {
          dp.put(key1, true);
          dp.put(key2, true);
          return true;
        }

        bleft = s2.substring(s2.length() - i);
        bright = s2.substring(0, s2.length() - i);

        left = solve(aleft, bleft);
        right = solve(aright, bright);
        if (left && right) {
          dp.put(key1, true);
          dp.put(key2, true);
          return true;
        }
      }

      dp.put(key1, false);
      dp.put(key2, false);
      return false;
    }

    public boolean isScramble(String s1, String s2) {
      return solve(s1, s2);
    }
}
