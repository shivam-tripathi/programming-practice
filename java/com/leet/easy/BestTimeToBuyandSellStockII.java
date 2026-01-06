package com.leet.easy;

/**
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Easy
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one
 * share of the stock multiple times).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e., max profit = 0.
 *
 * Constraints:
 *     1 <= prices.length <= 3 * 104
 *     0 <= prices[i] <= 104
 */

public class BestTimeToBuyandSellStockII {
  int[] prices;

  public int maxProfit(int[] prices) {
    this.prices = prices;

    int ans = 0;
    int buy = prices[0];
    int sell = -1;
    for (int price : prices) {
      if (sell == -1) {
        if (price <= buy) {
          buy = price;
        } else {
          sell = price;
        }
      }
      if (sell != -1) {
        if (price >= sell) {
          sell = price;
        } else {
          ans += sell - buy;
          sell = -1;
          buy = price;
        }
      }
    }

    if (sell != -1) {
      ans += sell - buy;
    }

    return ans;
  }


  public int maxProfit2(int[] prices) {
    int profit = 0, n = prices.length;
    for (int i = 0; i < n; i++) {
      while (i < n - 1 && prices[i] >= prices[i + 1]) {
        i++;
      }
      int buy = i;
      while (i < n - 1 && prices[i] <= prices[i + 1]) {
        i++;
      }
      int sell = i;
      profit += prices[sell] - prices[buy];
    }

    return profit;
  }

  public int maxProfitDP(int[] prices) {
    if (prices.length == 0) return 0;
    int profitAfterLastBuy = -prices[0], profit = 0;
    for (int i = 1; i < prices.length; i++) {
      // if current elements causes lesser dent in profit, we skip old one and buy this one instead
      int curBuy = Math.max(profitAfterLastBuy, profit - prices[i]);
      // if after last buy and this sell we can make more profit then our existing amount, we sell
      profit = Math.max(profit, profitAfterLastBuy + prices[i]);
      profitAfterLastBuy = curBuy;
    }
    return profit;
  }
}
