package main

/*
1013. Partition Array Into Three Parts With Equal Sum

https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/

Easy

Given an array of integers arr, return true if we can partition the array into three non-empty parts
with equal sums.

Formally, we can partition the array if we can find indexes i + 1 < j with
(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1]
	+ ... + arr[arr.length - 1])

Example 1:
Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1

Example 2:
Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false

Example 3:
Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

Constraints:
    3 <= arr.length <= 5 * 104
    -104 <= arr[i] <= 104
*/

import "fmt"

func canThreePartsEqualSum(arr []int) bool {
	sum := 0
	for _, num := range arr {
		sum += num
	}
	if sum%3 != 0 {
		return false
	}
	desired := sum / 3
	running, pos, chunk := 0, 0, 0
	for pos < len(arr) {
		running += arr[pos]
		pos++
		if running == desired {
			chunk++
			running = 0
		}
		if chunk == 3 {
			break
		}
	}

	if chunk == 3 {
		if pos == len(arr) {
			return true
		}
		running = 0
		for pos < len(arr) {
			running += arr[pos]
			pos++
		}
		return running == 0
	}

	return false
}

func main() {
	arr := []int{12, -4, 16, -5, 9, -3, 3, 8, 0}
	fmt.Println(arr)
	fmt.Println(canThreePartsEqualSum(arr))
}
