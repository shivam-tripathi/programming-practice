package com.leet.medium;

/**
 * 856. Score of Parentheses
 * https://leetcode.com/problems/score-of-parentheses/
 * Medium
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 *     () has score 1
 *     AB has score A + B, where A and B are balanced parentheses strings.
 *     (A) has score 2 * A, where A is a balanced parentheses string.
 * Example 1:
 * Input: "()"
 * Output: 1
 * Example 2:
 * Input: "(())"
 * Output: 2
 * Example 3:
 * Input: "()()"
 * Output: 2
 * Example 4:
 * Input: "(()(()))"
 * Output: 6
 * Note:
 *     S is a balanced parentheses string, containing only ( and ).
 *     2 <= S.length <= 50
 */

import java.util.Stack;

public class ScoreOfParentheses {
  public int scoreOfParentheses(String S) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < S.length(); i++) {
      char ch = S.charAt(i);
      if (ch == '(') {
        stack.push(-1);
      } else if (ch == ')') {
        int toPush = 0;
        while (!stack.isEmpty()) {
          int item = stack.pop();
          if (item == -1) {
            stack.push(Math.max(1, 2 * toPush));
            break;
          } else {
            toPush += item;
          }
        }
      }
    }
    int ans = 0;
    while (!stack.isEmpty()) {
      ans += stack.pop();
    }
    return ans;
  }
}
