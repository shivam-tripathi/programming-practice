package main

/*
67. Add Binary

https://leetcode.com/problems/add-binary/

Easy
Given two binary strings a and b, return their sum as a binary string.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"

Constraints:
    1 <= a.length, b.length <= 104
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself.
*/

func addBit(a, b, carry byte) (byte, byte) {
	if a == '0' && b == '0' {
		return carry, '0'
	} else if (a == '1' && b == '0') || (a == '0' && b == '1') {
		if carry == '1' {
			return '0', carry
		} else {
			return '1', carry
		}
	} else {
		return carry, '1'
	}
}

func addBinary(a string, b string) string {
	l1, l2, carry, sum := len(a)-1, len(b)-1, byte('0'), byte('0')
	ans := []byte{}
	for l1 >= 0 && l2 >= 0 {
		sum, carry = addBit(a[l1], b[l2], carry)
		ans = append(ans, sum)
		l1--
		l2--
	}
	for l1 >= 0 {
		sum, carry = addBit(a[l1], '0', carry)
		ans = append(ans, sum)
		l1--
	}
	for l2 >= 0 {
		sum, carry = addBit('0', b[l2], carry)
		ans = append(ans, sum)
		l2--
	}
	if carry == '1' {
		ans = append(ans, carry)
	}
	for i := 0; i < len(ans)/2; i++ {
		ans[i], ans[len(ans)-i-1] = ans[len(ans)-i-1], ans[i]
	}
	return string(ans)
}
