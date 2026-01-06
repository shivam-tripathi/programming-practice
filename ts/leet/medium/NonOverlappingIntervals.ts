// https://leetcode.com/problems/non-overlapping-intervals/
function eraseOverlapIntervals(intervals: number[][]): number {
	intervals.sort((a, b) => a[0] - b[0]);
	const merged = intervals.reduce((acc, cur) => {
		if (acc.length === 0) {
			acc.push(cur);
		} else {
			const prev = acc[acc.length - 1];
			if (prev[1] <= cur[0]) {
				acc.push(cur);
			} else {
				if (prev[1] > cur[1]) {
					acc[acc.length - 1] = cur;
				}
			}
		}
		return acc;
	}, [] as number[][]);

	return intervals.length - merged.length;
};
