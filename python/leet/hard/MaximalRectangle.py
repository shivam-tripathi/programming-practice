"""
85. Maximal Rectangle
Hard
https://leetcode.com/problems/maximal-rectangle/

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing
only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:
Input: matrix = [["0"]]
Output: 0

Example 3:
Input: matrix = [["1"]]
Output: 1

Constraints:
    rows == matrix.length
    cols == matrix[i].length
    1 <= row, cols <= 200
    matrix[i][j] is '0' or '1'.
"""


class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        hts = [0 for i in range(len(matrix[0]))]
        ans = 0
        for row in matrix:
            for i in range(len(row)):
                if row[i] == "1":
                    hts[i] += 1
                else:
                    hts[i] = 0
            ans = max(ans, self.maximum_rectangle_in_histogram(hts))

        return ans

    def maximum_rectangle_in_histogram(self, hts):
        print(hts)
        ans, stack = 0, []
        for i in range(len(hts)):
            j = i
            while stack and stack[-1][0] > hts[i]:
                [item, pos] = stack.pop()
                ans = max(ans, item * (i - pos))
                j = pos
            stack.append([hts[i], j])
        while stack:
            [item, pos] = stack.pop()
            ans = max(ans, item * (len(hts) - pos))

        return ans
