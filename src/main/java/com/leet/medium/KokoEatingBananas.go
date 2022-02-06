package main

import "math"

/*
875. Koko Eating Bananas
https://leetcode.com/problems/koko-eating-bananas/
Medium

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The
guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas
and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead
and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:
    1 <= piles.length <= 104
    piles.length <= h <= 109
    1 <= piles[i] <= 109
*/

func minEatingSpeed(piles []int, h int) int {
	// first find maximum we can consumer in one go
	// it is guaranteed that h > len(piles), else it would not have been possible
	max := piles[0]
	for _, pile := range piles {
		if max < pile {
			max = pile
		}
	}

	// now we prepare for binary search.
	// please be careful we lower bound and upper bounds when running a binary search
	// low is 1, because you atleast consume 1 if you ever want to finish anything
	// high is maximum of all piles
	low, high, ans := 1, max, max
	for low <= high {
		// decide the current value
		mid := low + (high-low)/2

		// calculate time taken by current value
		time := 0
		for _, pile := range piles {
			cur := int(math.Ceil(float64(pile) / float64(mid)))
			time += cur
		}

		// if we are able to finish within bounds
		if time <= h {
			if mid < ans {
				ans = mid
			}
			// maybe if we consume a little less - we can still succeed
			high = mid - 1
		} else {
			// if we took more time than allowed, we need to consumer more
			// therefore we need to bump low to mid+1. As mid is not the answer, we can skip it
			low = mid + 1
		}
	}

	return ans
}
