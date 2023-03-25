package com.dsAlgo.greedy;

import java.util.Arrays;

/**
 * Buy Maximum Stocks if i stocks can be bought on i-th day
 * https://www.geeksforgeeks.org/buy-maximum-stocks-stocks-can-bought-th-day/
 * Difficulty Level : Easy In a stock market, there is a product with its
 * infinite stocks. The stock prices are given for N days, where arr[i] denotes
 * the price of the stock on the ith day. There is a rule that a customer can
 * buy at most i stock on the ith day. If the customer has an amount of k amount
 * of money initially, find out the maximum number of stocks a customer can buy.
 * <p>
 * For example, for 3 days the price of a stock is given as 7, 10, 4. You can
 * buy 1 stock worth 7 rs on day 1, 2 stocks worth 10 rs each on day 2 and 3
 * stock worth 4 rs each on day 3.
 */

public class MaximumStocksI {
  int buyStocks(int[] prices, int money) {
    int[][] priceIdx = new int[prices.length][2];
    for (int i = 0; i < prices.length; i++) {
      priceIdx[i][0] = prices[i];
      priceIdx[i][1] = i;
    }
    Arrays.sort(priceIdx, (a, b) -> {
      if (a[0] != b[0])
        return a[0] - b[0];
      return b[1] - a[1];
    });
    int stocks = 0;
    for (int[] pIdx : priceIdx) {
      int price = pIdx[0], count = pIdx[1];
      int bought = Math.min(money / price, count);
      money -= bought * price;
      stocks += bought;
      if (money <= 0)
        break;
    }
    return stocks;
  }

  public static void main(String[] args) {
    var solve = new MaximumStocksI();
    solve.buyStocks(new int[] { 10, 7, 19, }, 45);
  }
}
