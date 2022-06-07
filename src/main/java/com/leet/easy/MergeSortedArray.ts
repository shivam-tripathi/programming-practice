import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

function merge(nums1: number[], m: number, nums2: number[], n: number): void {
	let p1 = 0, p2 = 0, offset = m, size = m + n;
	while (p1 < m && p2 < n) {
		if (nums1[p1] < nums2[p2]) {
			nums1[offset % size] = nums1[p1++];
		} else {
			nums1[offset % size] = nums2[p2++];
		}
		offset++;
	}

	while (p1 < m) {
		nums1[offset % size] = nums1[p1++];
		offset++;
	}

	while (p2 < n) {
		nums1[offset % size] = nums2[p2++];
		offset++;
	}

	const gcd = (a: number, b: number): number => {
		if (b === 0) return a;
		return gcd(b, a % b);
	}

	const limit = gcd(size, n);

	// rotate the array to the right by the gcd of size and n
	for (let i = 0; i < limit; i++) {
		let start = i, iter = i, prev = nums1[(i - n + size) % size];
		do {
			let cur = nums1[iter];
			nums1[iter] = prev;
			prev = cur;
			iter = (iter + n) % size;
		} while (iter !== start);
	}
};

const data: [number[], number, number[], number, number[]][] = [
	[[1, 2, 3, 0, 0, 0, 0], 3, [-1, 2, 5, 6], 4, [-1, 1, 2, 2, 3, 5, 6]],
	[[1, 2, 3, 0, 0, 0], 3, [2, 5, 6], 3, [1, 2, 2, 3, 5, 6]],
	[[1], 1, [] as number[], 0, [1]],
	[[0], 0, [1], 1, [1]]
];

Deno.test({
	name: "MergeSortedArray",
	fn: () => {
		data.forEach(([nums1, m, nums2, n, result]) => {
			merge(nums1, m, nums2, n);
			assertEquals(nums1, result);
		});
	},
})

