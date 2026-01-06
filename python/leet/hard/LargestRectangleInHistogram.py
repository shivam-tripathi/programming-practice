"""
84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/
Hard

Given an array of integers heights representing the histogram's bar height where the width of each
bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4

Constraints:
	1 <= heights.length <= 105
	0 <= heights[i] <= 104
"""


class Solution:
    def largestRectangleArea(self, hts: List[int]) -> int:
        stack, ans = [], 0
        for i in range(0, len(hts)):
            ht, ht_idx = hts[i], i
            while stack and stack[-1][0] > ht:
                [item, pos] = stack.pop()
                ans = max(ans, item * (i - pos))
                ht_idx = pos
            stack.append([ht, ht_idx])

        while stack:
            [item, j] = stack.pop()
            ans = max(ans, item * (len(hts) - j))

        return ans
