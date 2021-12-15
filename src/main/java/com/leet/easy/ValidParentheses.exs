'''
20. Valid Parentheses
https://leetcode.com/problems/valid-parentheses/
Easy

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([)]"
Output: false

Example 5:
Input: s = "{[]}"
Output: true

Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.
'''

defmodule Solution do
  def solve([], str) do
    if str == [] do
      true
    else
      [first | remaining] = str
      solve([first], remaining)
    end
  end

  def solve([head | tail], str) do
    if str == [] do
      false
    else
      [first | remaining] = str

      cond do
        first == "}" and head == "{" ->
          solve(tail, remaining)

        first == ")" and head == "(" ->
          solve(tail, remaining)

        first == "]" and head == "[" ->
          solve(tail, remaining)

        true ->
          solve([first | [head | tail]], remaining)
      end
    end
  end

  @spec is_valid(s :: String.t()) :: boolean
  def is_valid(s) do
    solve([], String.graphemes(s))
  end
end

IO.puts(Solution.is_valid("()"))
IO.puts(Solution.is_valid("()[]{}"))
IO.puts(Solution.is_valid("(]"))
IO.puts(Solution.is_valid("([)]"))
IO.puts(Solution.is_valid("{[]}"))
