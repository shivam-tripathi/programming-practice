package main

/*
5. Longest Palindromic Substring
https://leetcode.com/problems/longest-palindromic-substring/
Medium

Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
    1 <= s.length <= 1000
    s consist of only digits and English letters.
*/

func matchAt(s string, l, r int) (int, int) {
	count := 0
	for l >= 0 && r < len(s) && s[l] == s[r] {
		count += 2
		l--
		r++
	}

	if r-l-1 < 0 {
		return 0, 0
	}
	return l + 1, r - 1
}

func longestPalindrome(s string) string {
	if len(s) == 0 {
		return ""
	}

	ans, lans, rans := 1, 0, 0
	for i := 0; i < len(s); i++ {
		l, r := matchAt(s, i-1, i+1)
		if ans < (r - l + 2) {
			ans, lans, rans = (r - l + 2), l, r
		}

		l, r = matchAt(s, i-1, i)
		if ans < (r - l + 2) {
			ans, lans, rans = (r - l + 1), l, r
		}
	}
	return s[lans : rans+1]
}
