package main

/*
926. Flip String to Monotone Increasing
https://leetcode.com/problems/flip-string-to-monotone-increasing/
Medium

A binary string is monotone increasing if it consists of some number of 0's (possibly none),
followed by some number of 1's (also possibly none). You are given a binary string s. You can flip
s[i] changing it from 0 to 1 or from 1 to 0. Return the minimum number of flips to make s monotone
increasing.

Example 1:
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.

Example 2:
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
Input: s = "00011000"
Output: 2
Explanation: We flip to get 00000000.

Constraints:
    1 <= s.length <= 105
    s[i] is either '0' or '1'.
*/

func minFlipsMonoIncr(s string) int {
	// Get the size
	size := len(s)
	// Arrays to store:
	// 1. Count of ones from left hand side
	// 2. Count of zeros from right hand side
	zeros, ones := make([]int, size+1), make([]int, size+1)

	// Utility function acting as a ternary op which returns 1 if the bytes match else 0
	matches := func(toMatch, matchTo byte) int {
		if toMatch == matchTo {
			return 1
		}
		return 0
	}

	// Iteratate over the string to calculate number of ones leftwards of a particular partition and
	// number of zeros rightwards of a particular partition
	for i := 0; i < size; i++ {
		zeros[i+1] = zeros[i] + matches(s[size-i-1], '0')
		ones[i+1] = ones[i] + matches(s[i], '1')
	}

	ans := size

	// Iterate over partition counts.
	// Current answer = number of ones leftwards of the partition point +
	// number of zeros rightwards of the partition point
	for i := 0; i <= size; i++ {
		cur := ones[i] + zeros[size-i]
		if cur < ans {
			ans = cur
		}
	}

	return ans
}
