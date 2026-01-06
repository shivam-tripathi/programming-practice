package main

/*
509. Fibonacci Number
https://leetcode.com/problems/fibonacci-number/
Easy

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such
that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

Example 1:
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.

Example 2:
Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.

Example 3:
Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.

Constraints:
    0 <= n <= 30
*/

/* Multiply two matrix */
func mult(arr [2][2]int, base [2][2]int) [2][2]int {
	a, b, c, d := arr[0][0], arr[0][1], arr[1][0], arr[1][1]
	w, x, y, z := base[0][0], base[0][1], base[1][0], base[1][1]
	return [2][2]int{
		{a*w + b*y, a*x + b*z},
		{c*w + d*y, c*x + d*z},
	}
}

/* Fibonacco */
func fib(n int) int {
	if n == 0 {
		return 0
	}

	if n <= 2 {
		return 1
	}

	mat := [2][2]int{{1, 1}, {1, 0}}
	ans := [2][2]int{{1, 1}, {1, 1}}

	n -= 2

	for n != 0 {
		if n%2 == 1 {
			ans = mult(ans, mat)
		}
		mat = mult(mat, mat)
		n /= 2
	}

	return ans[0][0]
}
