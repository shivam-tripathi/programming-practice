/**
 * Only for lowerbound do we need to add additional +1 while calculating mid, because it can for
 * ever be stuck on low
 */

function lowerBound(nums: number[], target: number): number {
	let low = 0, high = nums.length - 1;
	if (nums[0] === target) return -1;
	while (low != high) {
		const mid = low + Math.floor((high - low + 1) / 2);
		if (nums[mid] < target) {
			low = mid;
		} else {
			high = mid - 1;
		}
	}
	return low;
}

function upperBound(nums: number[], target: number): number {
	let low = 0, high = nums.length - 1;
	if (nums[nums.length - 1] == target) return nums.length;
	while (low != high) {
		const mid = low + Math.floor((high - low) / 2);
		if (nums[mid] > target) {
			high = mid;
		} else {
			low = mid + 1;
		}
	}
	return low;
}

function searchRange(nums: number[], target: number): number[] {
	if (nums.length == 0) return [-1, -1];
	const upper = upperBound(nums, target);
	const lower = lowerBound(nums, target);
	if (nums[lower + 1] !== target || nums[upper - 1] !== target) {
		return [-1, -1];
	}
	return [lower + 1, upper - 1];
};