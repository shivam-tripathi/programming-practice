package main

/*
415. Add Strings
https://leetcode.com/problems/add-strings/
Easy

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and
num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as
BigInteger). You must also not convert the inputs to integers directly.

Example 1:
Input: num1 = "11", num2 = "123"
Output: "134"

Example 2:
Input: num1 = "456", num2 = "77"
Output: "533"

Example 3:
Input: num1 = "0", num2 = "0"
Output: "0"

Constraints:
	1 <= num1.length, num2.length <= 104
	num1 and num2 consist of only digits.
	num1 and num2 don't have any leading zeros except for the zero itself.
*/

func add(digit1, digit2 byte, carry int) (byte, int) {
	sum := int(digit1) + int(digit2) + carry
	return byte('0' + sum%10), sum / 10
}

func addStrings(num1 string, num2 string) string {
	pos1, pos2 := len(num1)-1, len(num2)-1
	res := []byte{}
	carry := 0
	for pos1 >= 0 && pos2 >= 0 {
		sum, _carry := add(num1[pos1]-'0', num2[pos2]-'0', carry)
		carry = _carry
		res = append(res, sum)
		pos1--
		pos2--
	}

	for pos1 >= 0 {
		sum, _carry := add(num1[pos1]-'0', 0, carry)
		carry = _carry
		res = append(res, sum)
		pos1--
	}

	for pos2 >= 0 {
		sum, _carry := add(num2[pos2]-'0', 0, carry)
		carry = _carry
		res = append(res, sum)
		pos2--
	}

	if carry != 0 {
		res = append(res, byte('0'+carry))
	}

	for i := 0; i < len(res)/2; i++ {
		res[len(res)-i-1], res[i] = res[i], res[len(res)-i-1]
	}

	return string(res)
}
