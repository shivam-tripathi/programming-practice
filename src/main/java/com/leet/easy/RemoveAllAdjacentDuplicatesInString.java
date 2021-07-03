package com.leet.easy;

/**
 * 1047. Remove All Adjacent Duplicates In String Easy
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists
 * of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven
 * that the answer is unique.
 *
 * Example 1:
 * Input: s = "abbaca" Output: "ca" Explanation: For example, in "abbaca" we could remove "bb"
 * since the letters are adjacent and equal, and this is the only possible move. The result of this
 * move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 * Example 2:
 * Input: s = "azxxzy" Output: "ay"
 *
 * Constraints:
 * 1 <= s.length <= 105 s consists of lowercase English letters.
 */
public class RemoveAllAdjacentDuplicatesInString {
  public String removeDuplicates(String s) {
    char[] stack = new char[s.length()];
    int size = 0;

    for (char ch : s.toCharArray()) {
      if (size == 0 || stack[size - 1] != ch) {
        stack[size] = ch;
        size++;
      } else {
        while (size != 0 && stack[size - 1] == ch) {
          size--;
        }
      }
    }

    char[] chars = new char[size];
    int pos = chars.length - 1;
    while (size != 0) {
      char ch = stack[--size];
      chars[pos--] = ch;
    }

    return new String(chars); // No need for string builder
  }
}
