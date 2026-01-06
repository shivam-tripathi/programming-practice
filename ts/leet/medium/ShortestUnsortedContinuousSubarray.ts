function findUnsortedSubarray(nums: number[]): number {
	// step 1: find the first inversion on the left
	let left = -1;
	for (let i = 0; i < nums.length - 1; i++) {
		if (nums[i] > nums[i + 1]) {
			left = i;
			break;
		}
	}

	// no inversion: no answer
	if (left === -1) return 0;

	// step 2: find inversion on right
	let right = -1;
	for (let i = nums.length - 1; i > 0; i--) {
		if (nums[i] < nums[i - 1]) {
			right = i;
			break;
		}
	}

	// no inversion or beyon left (both cases are unlikely), no ans
	if (right === -1 || right < left) return 0;

	// step 3: find the min and max in the range of left and right
	let min = nums[left], max = nums[left];
	for (let i = left; i <= right; i++) {
		min = Math.min(min, nums[i]);
		max = Math.max(max, nums[i]);
	}

	// step 4: find the lowerbound and upperbound for min and max in remaining parts of the array
	let begin = left, end = right;
	for (let i = 0; i < left; i++) {
		if (nums[i] > min) {
			begin = i;
			break;
		}
	}
	for (let i = nums.length - 1; i > right; i--) {
		if (nums[i] < max) {
			end = i;
			break;
		}
	}

	// return ans
	return end - begin + 1;
};
