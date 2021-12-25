package com.leet.medium;

/**
 * 56. Merge Intervals Medium
 *
 * https://leetcode.com/problems/merge-intervals/
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 *
 *
 * Example 1: Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output:
 * [[1,6],[8,10],[15,18]] Explanation: Since intervals [1,3] and [2,6] overlaps,
 * merge them into [1,6].
 *
 * Example 2: Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation:
 * Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints: 1 <= intervals.length <= 104 intervals[i].length == 2 0 <=
 * starti <= endi <= 104
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeIntervalsSolution {
	int[][] merge(int[][] intervals) { // Review this
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		int first = intervals[0][0], last = intervals[0][1];
		List<int[]> ans = new ArrayList<>();
		for (int i = 1; i < intervals.length; i++) {
			int begin = intervals[i][0], end = intervals[i][1];
			if (last < begin) { // if range end is before current begin, start a new range
				ans.add(new int[] { first, last });
				first = begin;
				last = end;
			} else {
				if (last < end) { // if range end is less than current end, merge it
					last = end;
				}
			}
		}
		ans.add(new int[] { first, last });
		return ans.toArray(new int[ans.size()][]);
	}
}
