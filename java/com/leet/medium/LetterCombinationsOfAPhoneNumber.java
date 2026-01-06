package com.leet.medium;

/**
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Medium
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
 * represent. Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any
 * letters.
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * Input: digits = ""
 * Output: []
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * Constraints:
 *     0 <= digits.length <= 4
 *     digits[i] is a digit in the range ['2', '9'].
 */

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
  List<List<Character>> items = List.of(
          List.of(),
          List.of(),
          List.of('a', 'b', 'c'),
          List.of('d', 'e', 'f'),
          List.of('g', 'h', 'i'),
          List.of('j', 'k', 'l'),
          List.of('m', 'n', 'o'),
          List.of('p', 'q', 'r', 's'),
          List.of('t', 'u', 'v'),
          List.of('w', 'x', 'y', 'z')
  );

  // Recursive DFS using StringBuilder smartly. Can use char array as well as final output is of fixed size.
  void formVal(char[] digits, int idx, StringBuilder sb, List<String> ans) {
    if (idx >= digits.length) return;
    for (char c : items.get(digits[idx] - '0')) {
      sb.append(c);
      formVal(digits, idx + 1, sb, ans);
      if (idx == digits.length - 1) {
        ans.add(sb.toString());
      }
      sb.setLength(idx);
    }
  }

  public List<String> letterCombinations(String digits) {
    char[] d = digits.toCharArray();
    var ans = new ArrayList<String>();
    StringBuilder sb = new StringBuilder();
    formVal(d, 0, sb, ans);
    return ans;
  }
}
