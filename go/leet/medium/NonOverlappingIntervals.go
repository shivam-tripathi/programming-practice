package main

/**
435. Non-overlapping Intervals

https://leetcode.com/problems/non-overlapping-intervals/

Medium

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number
of intervals you need to remove to make the rest of the intervals non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
    1 <= intervals.length <= 105
    intervals[i].length == 2
    -5 * 104 <= starti < endi <= 5 * 104
*/

import "sort"

func eraseOverlapIntervals(intervals [][]int) int {
	ans := 0

	if len(intervals) == 0 {
		return ans
	}

	// sort the intervals by start value
	sort.Slice(intervals, func(a, b int) bool {
		return intervals[a][0] < intervals[b][0]
	})

	// last points to the last position of current interval
	last := intervals[0][1]

	for i := 1; i < len(intervals); i++ {
		point := intervals[i]
		// there is an overlap
		if last > point[0] {
			// we remove the interval with bigger range, as it can intersect with a number of other intervals
			if last > point[1] {
				last = point[1]
			}
			ans++
		} else {
			last = point[1]
		}
	}

	return ans
}
