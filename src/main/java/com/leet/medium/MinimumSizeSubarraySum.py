from typing import List


class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        left, right, running, ans = 0, 0, 0, len(nums)+1
        while right < len(nums):
            # add to right of the window till it is invalid
            while running < target and right < len(nums):
                running += nums[right]
                right += 1

            # remove from left of the window till it is valid
            while running >= target and left <= right:
                ans = min(ans, right-left)
                running -= nums[left]
                left += 1
        return 0 if ans > len(nums) else ans
