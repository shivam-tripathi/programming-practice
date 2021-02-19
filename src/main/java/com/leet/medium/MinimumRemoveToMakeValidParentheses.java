package com.leet.medium;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 *     It is the empty string, contains only lowercase characters, or
 *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *     It can be written as (A), where A is a valid string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 10^5
 *     s[i] is one of  '(' , ')' and lowercase English letters.
 */

import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses {
  public String minRemoveToMakeValid(String s) {
    char[] chars = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    Stack<Integer> opening = new Stack<>(); // can use array to act as a rude stack to improve performance
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (ch == ')') {
        if (!opening.isEmpty()) {
          int j = opening.pop();
          chars[j] = 1;
          chars[i] = 2;
        }
      } else if (ch == '(') {
        opening.push(i);
      }
    }
    for (char ch: chars) {
      if (ch != ')' && ch != '(') {
        sb.append(ch == 1 ? '(' : ch == 2 ? ')' : ch);
      }
    }
    return sb.toString();
  }

  // Faster 98%
  public String minRemoveToMakeValid_2(String s) {
    char[] chars = s.toCharArray();
    int[] opening = new int[chars.length];
    int pointer = -1;
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (ch == ')') {
        if (pointer >= 0) {
          chars[opening[pointer--]] = '(';
        } else {
          chars[i] = '#';
        }
      } else if (ch == '(') {
        opening[++pointer] = i;
        chars[i] = '#';
      }
    }
    return new String(chars).replace("#", "");
  }

  public static void main(String[] args) {
    String out = new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("(a(b(c)d)");
    System.out.println(out);
  }
}
