from typing import List


class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        less_idx, more_idx = 0, len(nums)-1
        for num in nums:
            if num < pivot:
                less_idx += 1
            elif num > pivot:
                more_idx -= 1

        ans = [0] * len(nums)
        begin, center, end = 0, less_idx, more_idx+1
        for num in nums:
            if num < pivot:
                ans[begin] = num
                begin += 1
            elif num > pivot:
                ans[end] = num
                end += 1
            else:
                ans[center] = num
                center += 1

        return ans
