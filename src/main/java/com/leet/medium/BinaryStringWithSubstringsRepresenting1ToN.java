package com.leet.medium;

/**
 * 1016.
 *
 * Binary String With Substrings Representing 1 To N
 *
 * https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/
 *
 * Medium
 *
 * Given a binary string s and a positive integer n, return true if the binary
 * representation of all the integers in the range [1, n] are substrings of s,
 * or false otherwise.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1: Input: s = "0110", n = 3 Output: true
 *
 * Example 2: Input: s = "0110", n = 4 Output: false
 *
 * Constraints: 1 <= s.length <= 1000 s[i] is either '0' or '1'. 1 <= n <= 109
 *
 */

/*
 * Notes: Since the length of s is at most 1000, we know that there is "AT MOST"
 * ~1000 * 1000 = 10^6 substrings.
 *
 * Conclusion 1: for N = 10^9, 10^8, 10^7, ... the answer should be always
 * False, no matter what s is, because you can never construct 10^9 different
 * substrings from a string with length only at most 1000 (pigeon hole rule?).
 *
 * Conclusion 2: we only need to care about the substrings with length = logN
 * (or make it easy log10^9 ~ 30).
 *
 * We can use a set to store all found integers. Then, we iterate all starting
 * index by i, expand the substring and calculate the integer value, until it is
 * greater than N. This expansion should complete in O(logN) time.
 *
 * So the time complexity is O(len(s) * logN). Also the space complexity is
 * O(len(s)^2), which is at most 10^6, given that there are only AT MOST
 * O(len(s)^2) substrings.
 */

public class BinaryStringWithSubstringsRepresenting1ToN {
	public boolean queryString(String s, int n) {
		for (int i = 1; i <= n; i++) {
			if (s.indexOf(Integer.toBinaryString(i)) == -1) {
				return false;
			}
		}
		return true;
	}
}
