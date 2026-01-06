'''
198. House Robber
https://leetcode.com/problems/house-robber/
Medium

You are a professional robber planning to rob houses along a street. Each house has a certain amount
of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
have security systems connected and it will automatically contact the police if two adjacent houses
were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum
amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 400
'''


class Solution:
    def __init__(self):
        self.dp = {}

    def rob_house(self, nums: list[int], cur: int) -> int:
        if cur >= len(nums):
            return 0

        if self.dp.get(cur) is not None:
            return self.dp.get(cur)

        # rob this house

        rob = self.rob_house(nums, cur+2) + nums[cur]

        # dont rob this house
        dont_rob = self.rob_house(nums, cur+1)

        ans = max(rob, dont_rob)

        self.dp[cur] = ans

        return ans

    def rob(self, nums: list[int]) -> int:
        return self.rob_house(nums, 0)
