package com.leet.easy;

/**
 * 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

public class ValidAnagram{
  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] chars = new int[65536];
    for (int i = 0; i < s.length(); i++) {
      chars[s.charAt(i)]++;
      chars[t.charAt(i)]--;
    }
    for (int i = 0; i < 65536; i++) {
      if (chars[i] < 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(isAnagram("accaa", "acaca"));
  }
}
