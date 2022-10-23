/**
645. Set Mismatch
https://leetcode.com/problems/set-mismatch/
Easy
You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately,
due to some error, one of the numbers in s got duplicated to another number in the set, which
results in repetition of one number and loss of another number.
You are given an integer array nums representing the data status of this set after the error.
Find the number that occurs twice and the number that is missing and return them in the form of an
array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]

Example 2:
Input: nums = [1,1]
Output: [1,2]

Constraints:
	2 <= nums.length <= 104
	1 <= nums[i] <= 104
*/

export function findErrorNums(nums: number[]): number[] {
  let mix = 0;
  for (let i = 1; i <= nums.length; i++) {
    mix = mix ^ i ^ nums[i - 1];
  }

  // Find the bit set - because it is a xor all set bits are present exclusively in one of the
  // numbers
  const rsb = mix & -mix;

  // Create two buckets, one which has the set bit and the other which doesn't have it
  let a = 0, b = 0;
  for (let i = 1; i <= nums.length; i++) {
    const num = nums[i - 1];
    if (i & rsb) {
      a ^= i;
    } else {
      b ^= i;
    }
    if (num & rsb) {
      a ^= num;
    } else {
      b ^= num;
    }
  }

  for (const num of nums) {
    if (num == a) return [a, b];
    if (num == b) return [b, a];
  }

  return [];
}
