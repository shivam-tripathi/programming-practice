package com.leet.medium;

/**
 * 567.
 *
 * Permutation in String Medium
 *
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1,
 * or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of
 * s2.
 *
 * Example 1: Input: s1 = "ab", s2 = "eidbaooo" Output: true Explanation: s2
 * contains one permutation of s1 ("ba").
 *
 * Example 2: Input: s1 = "ab", s2 = "eidboaoo" Output: false
 *
 * Constraints: 1 <= s1.length, s2.length <= 104 s1 and s2 consist of lowercase
 * English letters.
 */

public class PermutationInString {
	public boolean checkInclusion(String s1, String s2) {
		// Frequency map
		int[] freq = new int[26];
		for (char ch : s1.toCharArray()) {
			freq[ch - 'a']++;
		}

		// Sliding window to map the characters against the frequencies
		char[] chars = s2.toCharArray();
		// Begin (inclusive) is start of window, end (not inclusive) is the end of the
		// window
		int begin = 0, end = 0;
		for (int pos = 0; pos < chars.length; pos++) {
			// If the current character does not match and we can shrink the window, we
			// continue to do so either we can match the character or window size is zero
			while (freq[chars[pos] - 'a'] == 0 && begin < end) {
				freq[chars[begin] - 'a']++;
				begin++;
			}
			// If window size is zero, and the even if include the current character we
			// still cannot have window of size 1 - we will have to skip this character and
			// hence break the window
			if (begin == end && freq[chars[pos] - 'a'] == 0) {
				begin = end = begin + 1;
			} else {
				// If we can match the character, we add it to the window and extend the window
				end++;
				freq[chars[pos] - 'a']--;
			}
			if ((end - begin) == s1.length()) {
				return true;
			}
		}
		return false;
	}
}
