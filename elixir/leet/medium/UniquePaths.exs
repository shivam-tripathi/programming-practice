'''
62. Unique Paths
https://leetcode.com/problems/unique-paths/
Medium

There is a robot on an m x n grid. The robot is initially located at the top-left corner
(i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take
to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Constraints:
    1 <= m, n <= 100
'''

defmodule Solution do
  def unique_paths(x, y, m, n, dp) do
    cond do
      x >= m or y >= n or x < 0 or y < 0 ->
        {dp, 0}

      Map.has_key?(dp, {x, y}) ->
        {dp, Map.get(dp, {x, y})}

      x == m - 1 and y == n - 1 ->
        {dp, 1}

      true ->
        {dp, left} = unique_paths(x + 1, y, m, n, dp)
        {dp, right} = unique_paths(x, y + 1, m, n, dp)
        dp = Map.put(dp, {x, y}, left + right)
        {dp, left + right}
    end
  end

  @spec unique_paths(m :: integer, n :: integer) :: integer
  def unique_paths(m, n) do
    {_, ans} = unique_paths(0, 0, m, n, %{})
    ans
  end
end
