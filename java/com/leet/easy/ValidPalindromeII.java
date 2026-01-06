package com.leet.easy;

/**
 * 680. Valid Palindrome II Easy
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Given a string s, return true if the s can be palindrome after deleting at
 * most one character from it.
 *
 *
 *
 * Example 1: Input: s = "aba" Output: true
 *
 * Example 2: Input: s = "abca" Output: true Explanation: You could delete the
 * character 'c'.
 *
 * Example 3: Input: s = "abc" Output: false
 *
 * Constraints: 1 <= s.length <= 105 s consists of lowercase English letters.
 *
 */

/**
 * Where there is a mismatch between characters, you have to take a call which
 * one to skip. Skipping either of them can actually lead to success state. Eg:
 * aa baba ba -> here we can skip either a or b still reach success state
 * Sometimes it can be one of them or none of them. What we need to do here is
 * try out all combinations. Using recursion, this can be extended to k
 * eliminations.
 */

public class ValidPalindromeII {
	private boolean validPalindrome(char[] chars, int start, int end, boolean allow) {
		while (start < end) {
			if (chars[start] != chars[end]) {
				if (!allow)
					return false;
				// Verify both cases, succeed fast
				return validPalindrome(chars, start + 1, end, false) || validPalindrome(chars, start, end - 1, false);
			}
			start++;
			end--;
		}
		return true;
	}

	public boolean validPalindrome(String s) {
		char[] chars = s.toCharArray();
		int start = 0, end = chars.length - 1;
		return validPalindrome(chars, start, end, true);
	}
}
