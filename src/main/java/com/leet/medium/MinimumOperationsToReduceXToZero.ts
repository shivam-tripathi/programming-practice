
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

function min_value(a: number, b: number): number {
	if (a === -1) return b;
	return a < b ? a : b;
}

function minOperations(nums: number[], x: number): number {
	const size = nums.length;
	let ans = -1;

	if (size === 0) return ans;
	if (size === 1) return nums[0] === x ? 1 : -1;

	let left_sum = nums[0], right_sum = 0;

	for (let i = size - 1; i > 0; i--) {
		right_sum += nums[i];
		if (right_sum === x) ans = min_value(ans, size - i);
	}

	if (left_sum === x) ans = min_value(ans, 1);

	let left = 0, right = 1;
	while (left < right && right < size) {
		while (right < size && (left_sum + right_sum) > x) {
			right_sum -= nums[right++];
		}

		if (right < size && (left_sum + right_sum) === x) {
			ans = min_value(ans, left + 1 + size - right);
		}

		left_sum += nums[++left];

		if (left_sum === x) ans = min_value(ans, left + 1);
	}

	return ans;
}

const test_cases = [
	{ nums: [2753, 778, 2454, 3049, 8148, 6899, 2088, 2526, 9979, 5771, 7687, 8419, 2551, 183, 7646, 8682, 3514, 7777, 848, 4469, 5774, 8952, 9631, 4123, 1015, 258, 9195, 5763, 4802, 4819, 4889, 1345, 9357, 9422, 7958, 3801, 1798, 3411, 3981, 4658, 3124, 1406, 8671, 6907, 4384, 1009, 5628, 4557, 4436, 9882, 3638, 6921, 4107, 3016, 6969, 474, 4755, 8080, 2859, 568, 7929, 7496, 647, 5659, 4706, 3416, 4712, 9487, 892, 3338, 2983, 3754, 3389, 5601, 8286, 3958, 7611, 4978, 5029, 9205, 9359, 1255, 2686, 1657, 980, 841, 6938, 9534, 6885, 6899, 298, 5906, 8561, 2858, 1384, 7987, 7044, 6390, 1984, 2244, 2982, 4155, 3121, 6332, 4672, 5346, 1654, 2862, 5947, 1181, 6207, 8546, 7376, 6393, 1873, 9584, 7373, 6265, 964, 2457, 4355, 8884, 4655, 4361, 5814, 2065, 2280, 8608, 9077, 7931, 3256, 3749, 2359, 5248, 9358, 3541, 9808, 7796, 9208, 7416, 735, 4142, 9955, 2592, 2163, 6383, 7545, 6832, 988, 1179, 5738, 8941, 8446, 1556, 1977, 9267, 1276, 905, 9349, 1936, 4837, 608, 8771, 7047, 5150, 3732, 3743, 4555, 1927, 9249, 1908, 7834, 8828, 5859, 1133, 6816, 4688, 359, 6816, 3232, 4264, 3335, 5691, 4051, 4857, 9467, 2382, 3613, 61, 1214, 4539, 4829, 3451, 3450, 7422, 6076, 5356, 659, 3059, 9223, 5950, 9004, 7290, 3580, 10000, 725, 6730, 2327, 1055, 5342, 3856, 6218, 9008, 9184, 9290, 234, 5918, 6305, 5383, 806, 4, 8479, 3612, 956, 8322, 2517, 9533, 8600, 8549, 9108, 6855, 7148, 3038, 8679, 5771, 4107, 2568, 2931, 7027, 8841, 5318, 2524, 8628, 5086, 6885, 6460, 1296, 5634, 9107, 2681, 1029, 2777, 8767, 3413, 1821, 119, 5682, 9330, 948, 9374, 4219, 6159, 9464, 7880, 7608, 2492, 8889, 28, 6031, 4111, 8630, 7197, 296, 2419, 7920, 1381], x: 1140836, ans: 226 },
	{ nums: [10, 1, 1, 1, 1, 1], x: 5, ans: 5 },
	{ nums: [1, 1, 4, 2, 3], x: 5, ans: 2 },
	{ nums: [5, 6, 7, 8, 9], x: 4, ans: -1 },
	{ nums: [3, 2, 20, 1, 1, 3], x: 10, ans: 5 },
	{ nums: [1, 1, 3, 2, 5], x: 5, ans: 1 },
	{ nums: [1, 1, 3, 2, 5], x: 12, ans: 5 },
	{ nums: [5, 2, 3, 1, 1], x: 5, ans: 1 }
];

Deno.test('MinimumOperationsToReduceXToZero',
	() => {
		test_cases.forEach((test_case) => {
			const { nums, x, ans: expected } = test_case;
			const ans = minOperations(nums, x);
			console.log({ ans, expected });
			assertEquals(expected, ans);
		});
	}
);
