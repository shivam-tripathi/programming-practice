package main

/*
539. Minimum Time Difference

https://leetcode.com/problems/minimum-time-difference/

Medium
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference
between any two time-points in the list.

Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:
    2 <= timePoints.length <= 2 * 104
    timePoints[i] is in the format "HH:MM".
*/

import "sort"

// Converts HH:MM to number of minutes
func toMinutes(timepoint string) int64 {
	var mins int64 = 0
	for idx, char := range timepoint {
		switch idx {
		case 0:
			mins += 600 * int64(char-'0')
		case 1:
			mins += 60 * int64(char-'0')
		case 3:
			mins += 10 * int64(char-'0')
		case 4:
			mins += int64(char - '0')
		}
	}
	return mins
}

func findMinDifference(timepoints []string) int {
	minutes := []int64{}

	// collect all minutes
	for _, timepoint := range timepoints {
		minute := toMinutes(timepoint)
		minutes = append(minutes, minute)
	}

	// sort the timepoints to get closest timepoints next to each other.
	// Bucket sort can be used here as range of numbers is just [0, 24*60)
	sort.Slice(minutes, func(a, b int) bool {
		return minutes[a] < minutes[b]
	})

	// time is circular in nature, so last and first can be compared
	ans := 24*60 + minutes[0] - minutes[len(minutes)-1]

	for i := 1; i < len(minutes); i++ {
		if ans > (minutes[i] - minutes[i-1]) {
			ans = minutes[i] - minutes[i-1]
		}
	}

	return int(ans)
}
