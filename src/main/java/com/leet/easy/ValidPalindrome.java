package com.leet.easy;

/**
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 * Easy
 *
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Constraints:
 *     1 <= s.length <= 2 * 105
 *     s consists only of printable ASCII characters.
 */

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    char[] chars = s.toCharArray();
    int low = 0, high = chars.length - 1;
    while (low < high) {
      char lch = chars[low];
      char hch = chars[high];
      if (!Character.isLetterOrDigit(lch)) low++;
      else if (!Character.isLetterOrDigit(hch)) high--;
      else if (Character.toLowerCase(lch) == Character.toLowerCase(hch)) {
        low++;
        high--;
      } else {
        return false;
      }
    }
    return true;
  }
}
