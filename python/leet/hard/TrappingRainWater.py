"""
42. Trapping Rain Water
Hard
https://leetcode.com/problems/trapping-rain-water/

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105
"""


class Solution:
    def trap(self, hts: list[int]) -> int:
        left, right = 0, len(hts) - 1

        left_max, right_max = 0, 0
        ans = 0

        while left < right:
            if hts[left] < hts[right]:
                if hts[left] > left_max:
                    left_max = hts[left]
                else:
                    ans += left_max - hts[left]
                left += 1
            else:
                if hts[right] > right_max:
                    right_max = hts[right]
                else:
                    ans += right_max - hts[right]
                right -= 1

        return ans


if __name__ == "__main__":
    solution = Solution()
    print(solution.trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]))  # 6
    print(solution.trap([4, 2, 0, 3, 2, 5]))  # 9
    print(solution.trap([4, 2, 3, 5, 1, 2, 3]))  # 3
