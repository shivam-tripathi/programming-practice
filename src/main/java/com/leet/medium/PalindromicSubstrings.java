package com.leet.medium;

/**
 * 647. Palindromic Substrings Medium
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * Given a string s, return the number of palindromic substrings in it. A string
 * is a palindrome when it reads the same backward as forward. A substring is a
 * contiguous sequence of characters within the string.
 *
 * Example 1: Input: s = "abc" Output: 3 Explanation: Three palindromic strings:
 * "a", "b", "c".
 *
 * Example 2: Input: s = "aaa" Output: 6 Explanation: Six palindromic strings:
 * "a", "a", "a", "aa", "aa", "aaa".
 *
 * Constraints: 1 <= s.length <= 1000 s consists of lowercase English letters.
 *
 */

/**
 * An alternate approach is to create a dp which stores if dp[i:j] is a
 * palindrome or not. It is obvious that base cases would be:
 *
 * 1. dp[i:i] is a palindrome (palindrome around a pivot)
 *
 * 2. dp[i:i+1] is a palindrome or not (palindrome around a virtual barrier)
 *
 * Complexity in either cases is O(n^2), with space complexity in second
 * approach also being O(n^2)
 */

public class PalindromicSubstrings {
	// Checks palindrome outwards from low -> 0 and high -> end of string
	private int countSubstringsOutwards(char[] chars, int low, int high) {
		int ans = 0;
		while (low >= 0 && high < chars.length) {
			if (chars[low] != chars[high])
				break;
			ans++;
			low--;
			high++;
		}
		return ans;
	}

	// Checks palindrome around a pivot
	private int countSubstringsAroundPivot(char[] chars, int pivot) {
		return countSubstringsOutwards(chars, pivot - 1, pivot + 1) + 1;
	}

	// Checks palindrome around a virtual barrier between mid and mid+1
	private int countSubstringsAroundBarrier(char[] chars, int mid) {
		return countSubstringsOutwards(chars, mid, mid + 1);
	}

	// Counts all substring palindromes in this string
	public int countSubstrings(String s) {
		int ans = 0;
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			ans += countSubstringsAroundPivot(chars, i);
			ans += countSubstringsAroundBarrier(chars, i);
		}
		return ans;
	}
}
