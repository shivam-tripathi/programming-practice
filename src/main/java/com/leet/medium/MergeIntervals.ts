// https://leetcode.com/problems/merge-intervals/

function merge(intervals: number[][]): number[][] {
	intervals.sort((a, b) => a[0] - b[0]);
	return intervals.reduce((acc, cur) => {
		if (acc.length === 0) {
			acc.push(cur);
		} else {
			const prev = acc[acc.length - 1];
			if (prev[1] < cur[0]) {
				acc.push(cur);
			} else {
				if (prev[1] < cur[1]) {
					prev[1] = cur[1];
				}
			}
		}
		return acc;
	}, [] as number[][]);
};
