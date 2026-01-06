package com.leet.medium;

/**
 * 22. Generate Parentheses
 * https://leetcode.com/problems/generate-parentheses/
 * Medium
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 * Constraints:
 * 1 <= n <= 8
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  // Backtrack method 1. Basically at every position - if applicable - add opening brace and closing brace till we hit
  // the end
  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    backtrack(list, new StringBuilder(), 0, 0, n);
    return list;
  }

  public void backtrack(List<String> list, StringBuilder sb, int open, int close, int max) {
    if (sb.length() == max * 2) {
      list.add(sb.toString());
      return;
    }
    int length = sb.length();
    if (open < max) backtrack(list, sb.append("("), open + 1, close, max);
    sb.setLength(length);
    if (close < open) backtrack(list, sb.append(")"), open, close + 1, max);
  }

  // Backtrack method 2 - Debt and savings
  public void solve(StringBuilder sb, int debt, int savings, List<String> ans) {
    if (savings == 0) {
      ans.add(sb.toString());
      return;
    }

    if (debt == savings) {
      sb.append(")".repeat(savings));
      ans.add(sb.toString());
      return;
    }

    int length = sb.length();

    // debt+1 - take debt
    sb.append("(");
    solve(sb, debt + 1, savings - 1, ans);

    sb.setLength(length);

    // debt-1 - pay back debt
    if (debt > 0) {
      sb.append(")");
      solve(sb, debt - 1, savings - 1, ans);
    }
  }

  public List<String> generateParenthesis_2(int n) {
    var ans = new ArrayList<String>();
    var sb = new StringBuilder();
    solve(sb, 0, n * 2, ans);
    return new ArrayList<>(ans);
  }
}
