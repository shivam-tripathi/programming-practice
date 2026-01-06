'''
121. Best Time to Buy and Sell Stock
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Easy

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a
different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot
achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6),
profit = 6-1 = 5. Note that buying on day 2 and selling on day 1 is not allowed
because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

Constraints:
    1 <= prices.length <= 105
    0 <= prices[i] <= 104
'''

defmodule Solution do
  @spec solve(prices :: [integer], running :: integer, ans :: integer) :: integer
  def solve([head | tail], running, ans) do
    if head >= running do
      solve(tail, running, max(head - running, ans))
    else
      solve(tail, head, ans)
    end
  end

  @spec solve(prices :: [integer], running :: integer, ans :: integer) :: integer
  def solve([], _running, ans) do
    ans
  end

  @spec max_profit(prices :: [integer]) :: integer
  def max_profit(prices) do
    if prices == [] do
      0
    else
      [head | tail] = prices
      solve(tail, head, 0)
    end
  end
end

IO.puts(Solution.max_profit([7, 1, 5, 3, 6, 4]))
IO.puts(Solution.max_profit([7, 6, 4, 3, 1]))
