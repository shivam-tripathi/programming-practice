package com.leet.easy;

/**
 * 1678. Goal Parser Interpretation
 * https://leetcode.com/problems/goal-parser-interpretation/
 * Easy
 * You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or
 * "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as
 * the string "al". The interpreted strings are then concatenated in the original order.
 * Given the string command, return the Goal Parser's interpretation of command.
 * Example 1:
 * Input: command = "G()(al)"
 * Output: "Goal"
 * Explanation: The Goal Parser interprets the command as follows:
 * G -> G
 * () -> o
 * (al) -> al
 * The final concatenated result is "Goal".
 * Example 2:
 * Input: command = "G()()()()(al)"
 * Output: "Gooooal"
 * Example 3:
 * Input: command = "(al)G(al)()()G"
 * Output: "alGalooG"
 * Constraints:
 *     1 <= command.length <= 100
 *     command consists of "G", "()", and/or "(al)" in some order.
 */

public class GoalParserInterpretation {
  public String interpret(String command) {
    var sb = new StringBuilder();
    char prev = 0;
    for (char ch : command.toCharArray()) {
      if (ch == 'G') sb.append(ch);
      if (ch == ')' && prev == '(') sb.append('o');
      if (ch == ')' && prev == 'l') sb.append("al");
      prev = ch;
    }
    return sb.toString();
  }
}
