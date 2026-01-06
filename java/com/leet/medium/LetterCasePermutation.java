package com.leet.medium;

/**
 * 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. You can return the output in any order.
 * Example 1:
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 * Example 3:
 * Input: S = "12345"
 * Output: ["12345"]
 * Example 4:
 * Input: S = "0"
 * Output: ["0"]
 *
 * Constraints:
 *     S will be a string with length between 1 and 12.
 *     S will consist only of letters or digits.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterCasePermutation {
  public List<String> letterCasePermutation(String S) {
    List<StringBuilder> ans = new ArrayList<>();
    for (int i = 0; i < S.length(); i++) {
      char ch = S.charAt(i);
      List<Character> toAppend = new ArrayList<>();
      toAppend.add(ch);
      if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
        toAppend.add((char) (ch >= 'a' ? ch - 32 : ch + 32));
      }
      if (ans.size() == 0) {
        for (char c : toAppend) {
          ans.add(new StringBuilder().append(c));
        }
      } else {
        var ll = new ArrayList<StringBuilder>();
        for (StringBuilder builder : ans) {
          int length = builder.length();
          for (char c : toAppend) {
            ll.add(new StringBuilder(builder).append(c));
          }
        }
        ans = ll;
      }
    }
    return ans.stream().map(StringBuilder::toString).collect(Collectors.toList());
  }

  // Reuse the same string builder for all the strings
  void solve(String s, int idx, StringBuilder sb, List<String> ans) {
    if (idx >= s.length()) {
      ans.add(sb.toString());
      return;
    }
    char ch = s.charAt(idx);
    sb.append(ch);
    int length = sb.length();

    solve(s, idx+1, sb, ans);
    sb.setLength(length - 1);

//    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
    if (Character.isLetter(ch)) {
//      sb.append((char) (ch >= 'a' ? ch - 32 : ch + 32));
      sb.append((char)(ch ^ 32)); // XOR with 32 will flip character
      solve(s, idx+1, sb, ans);
    }
  }

  // Similar to method one in implementation and speed, instead of string builder we can use char array
  void solve2(char[] chars, int idx, List<String> ans) {
    if (idx >= chars.length) {
      ans.add(new String(chars));
      return;
    }
    char ch = chars[idx];
    solve2(chars, idx+1, ans);
    if (Character.isLetter(ch)) {
      chars[idx] = (char)(ch ^ 32);
      solve2(chars, idx+1, ans);
    }
  }

  public List<String> letterCasePermutation_2(String s) {
    var ans = new ArrayList<String>();
    solve(s, 0, new StringBuilder(), ans);
    return ans;
  }

  public static void main(String[] args) {
    String S = "a1b2";
    System.out.println(new LetterCasePermutation().letterCasePermutation_2(S));
  }
}
