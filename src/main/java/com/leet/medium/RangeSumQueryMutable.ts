/**
307. Range Sum Query - Mutable
https://leetcode.com/problems/range-sum-query-mutable/
Medium

Given an integer array nums, handle multiple queries of the following types:
		Update the value of an element in nums.
		Calculate the sum of the elements of nums between indices left and right inclusive where
		left <= right.

Implement the NumArray class:
		NumArray(int[] nums) Initializes the object with the integer array nums.
		void update(int index, int val) Updates the value of nums[index] to be val.
		int sumRange(int left, int right) Returns the sum of the elements of nums between indices left
		and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).

Example 1:
Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8

Constraints:
		1 <= nums.length <= 3 * 104
		-100 <= nums[i] <= 100
		0 <= index < nums.length
		-100 <= val <= 100
		0 <= left <= right < nums.length
		At most 3 * 104 calls will be made to update and sumRange.
 */


class BinaryIndexedTree {
	private data: number[];

	constructor(private size: number) {
		this.data = new Array(size + 1).fill(0);
	}

	public update(index: number, value: number): void {
		while (index < this.data.length) {
			this.data[index] += value;
			index += index & -index;
		}
	}

	public query(index: number): number {
		let ans: number = 0;
		while (index > 0) {
			ans += this.data[index];
			index -= index & -index;
		}
		return ans;
	}
}



class NumArray {
	bit: BinaryIndexedTree;
	constructor(nums: number[]) {
		this.bit = new BinaryIndexedTree(nums.length);
		nums.forEach((num, idx) => this.bit.update(idx + 1, num));
	}

	update(index: number, val: number): void {
		const cur = this.bit.query(index + 1) - this.bit.query(index);
		this.bit.update(index + 1, val - cur);
	}

	sumRange(left: number, right: number): number {
		return this.bit.query(right + 1) - this.bit.query(left);
	}
}


/**
 * Your NumArray object will be instantiated and called as such:
 */

var obj = new NumArray([1, 3, 5])
console.log(obj.sumRange(0, 2));
obj.update(1, 2);
console.log(obj.sumRange(0, 2));
