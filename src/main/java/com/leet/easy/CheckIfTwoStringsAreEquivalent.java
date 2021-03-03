package com.leet.easy;

/**
 * 1662. Check If Two String Arrays are Equivalent
 * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 * Easy
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * A string is represented by an array if the array elements concatenated in order forms the string.
 * Example 1:
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * Example 2:
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Example 3:
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 * Constraints:
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] and word2[i] consist of lowercase letters.
 */

public class CheckIfTwoStringsAreEquivalent {
  public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
    int w1 = 0, w2 = 0, wc1 = 0, wc2 = 0;
    while (w1 < word1.length && w2 < word2.length) {
      String a = word1[w1], b = word2[w2];

      // while (wc1 < a.length() && wc2 < b.length()) {
      //   if (a.charAt(wc1++) != b.charAt(wc2++)) return false;
      // }
      // IndexOf is blazing fast, gives 100%. Makes it a little complex to figure out all the edge cases.
      if (a.length() - wc1 >= b.length() - wc2) {
        if (a.indexOf(b.substring(wc2), wc1) != wc1) return false;
        wc1 += b.length() - wc2;
        wc2 = b.length();
      } else {
        if (b.indexOf(a.substring(wc1), wc2) != wc2) return false;
        wc2 += a.length() - wc1;
        wc1 = a.length();
      }

      if (wc1 >= a.length()) {
        w1++;
        wc1 = 0;
      }
      if (wc2 >= b.length()) {
        w2++;
        wc2 = 0;
      }
    }
    return !(w1 < word1.length || w2 < word2.length);
  }

  // indexOf is still blazing fast even used with substr
  public boolean arrayStringsAreEqual_2(String[] word1, String[] word2) {
    int w1 = 0, w2 = 0, wc1 = 0, wc2 = 0;
    char[] a = word1[w1].toCharArray(), b = word2[w2].toCharArray();
    while (w1 < word1.length && w2 < word2.length) {
      while (wc1 < a.length && wc2 < b.length) {
        if (a[wc1++] != b[wc2++]) return false;
      }

      if (wc1 >= a.length) {
        w1++;
        if (w1 < word1.length) a = word1[w1++].toCharArray();
        a = null;
      }
      if (wc2 >= b.length) {
        w2++;
        if (w2 < word1.length) b = word2[w2++].toCharArray();
        b = null;
      }
    }

    return w1 >= word1.length && w2 >= word2.length;
  }
}
