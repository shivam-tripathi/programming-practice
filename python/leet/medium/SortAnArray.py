'''
912. Sort an Array
https://leetcode.com/problems/sort-an-array/description/
Medium

Given an array of integers nums, sort the array in ascending order and return it.

You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and
with the smallest space complexity possible.

Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example,
2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessairly unique.

Constraints:
    1 <= nums.length <= 5 * 104
    -5 * 104 <= nums[i] <= 5 * 104
'''

import random
from typing import List

class Solution:
    def qsort(self, nums: List[int], low: int, high: int):
        if (high-low) <= 1:
            return

        delta = high - low
        pivot_idx = int(random.random() * delta) + low
        pivot = nums[pivot_idx]
        less, more, idx = low, high-1, low
        pivot_count = 0

        while idx <= more:
            if nums[idx] == pivot:
                pivot_count += 1
                idx += 1
            elif nums[idx] < pivot:
                if idx != less:
                    nums[less], nums[idx] = nums[idx], nums[less]
                else:
                    idx += 1
                less += 1
            else:
                nums[more], nums[idx] = nums[idx], nums[more]
                more -= 1

        for i in range(less, more+1):
            nums[i] = pivot

        self.qsort(nums, low, less)
        self.qsort(nums, more+1, high)

    def sortArray(self, nums: List[int]) -> List[int]:
        self.qsort(nums, 0, len(nums))
        return nums