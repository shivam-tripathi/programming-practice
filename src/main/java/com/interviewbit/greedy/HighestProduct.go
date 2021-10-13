package main

/**
Highest Product
https://www.interviewbit.com/old/problems/highest-product/

Given an array A, of N integers A.
Return the highest product possible by multiplying 3 numbers from the array.
NOTE: Solution will fit in a 32-bit signed integer.


Input Format:
The first and the only argument is an integer array A.

Output Format:
Return the highest possible product.

Constraints:
1 <= N <= 5e5

Example:
Input 1:
A = [1, 2, 3, 4]
Output 1:
24

Explanation 1:
2 * 3 * 4 = 24

Input 2:
A = [0, -1, 3, 100, 70, 50]
Output 2:
350000

Explanation 2:
70 * 50 * 100 = 350000
*/

import (
	"sort"
)

func maxp3(A []int) int {
	if len(A) < 3 {
		return 0
	}

	arr := []int{A[0], A[1], A[2]}
	sort.Ints(arr)
	// a, b, c represents the maximum three elements
	a, b, c := arr[0], arr[1], arr[2]
	// x, y represents minimum three elements
	x, y := arr[0], arr[1]

	for i := 3; i < len(A); i++ {
		num := A[i]

		if num > c {
			a = b
			b = c
			c = num
		} else if num > b {
			a = b
			b = num
		} else if num > a {
			a = num
		}

		if num < x {
			y = x
			x = num
		} else if num < y {
			y = num
		}
	}

	ans := x * y * c
	q := a * b * c

	if ans < q {
		ans = q
	}

	return ans
}
