package com.practice.spoj;

/*
ABA12C - Buying Apples!
https://www.spoj.com/problems/ABA12C/
Harish went to a supermarket to buy exactly ‘k’ kilograms apples for his‘n’friends.
The supermarket was really weird. The pricing of items was very different.
He went to the Apples section and enquired about the prices.
The salesman gave him a card in which he found that the prices of apples were not per kg.
The apples were packed into covers, each containing ‘x’ kg of apples, x > 0 and ‘x’ is an integer.
An ‘x’ kg packet would be valued at ‘y’ rupees.
So, the placard contained a table with an entry ‘y’ denoting the price of an ‘x’ kg packet.
If ‘y’ is -1 it means that the corresponding packet is not available.
Now as apples are available only in packets, he decides to buy at most ‘n’ packets for his ‘n’ friends i.e
he will not buy more than n packets of apples.
Harish likes his friends a lot and so he does not want to disappoint his friends.
So now, he will tell you how many friends he has and you have to tell him the minimum amount of money he has to spend
for his friends.
 */

// Buy k kgs of apples for n friends
// Buy n packets with weight no more than k kgs
// Sum of total weight == k and number of packets <= n

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class ABA12C {
  static int n;
  static int k;
  static int[] prices;
  static int inf = Integer.MAX_VALUE - (int) 1e8;
  static int[][][] dp;

  static int solve(int pos, int target, int count) {
    if (pos >= k && target != 0) return inf;
    if (count >= n && target != 0) return inf;
    if (dp[target][count][pos] != 0) return dp[target][count][pos];
    int with = inf;
    if (prices[pos] != -1) {
      int newTarget = target - pos - 1;
      if (newTarget == 0) {
        with = prices[pos];
      } else if (newTarget >= pos + 1) {
        with = prices[pos] + solve(pos, newTarget, count + 1);
      }
    }
    int without = solve(pos + 1, target, count);
    dp[target][count][pos] = Math.min(with, without);
    return dp[target][count][pos];
  }

  static int solve() {
    dp = new int[k+1][n+1][k+1];
    return solve(0, k, 0);
  }

  static void test() {
    n = 5; k = 6; prices = new int[]{2,2,1,4,1,4};
    int ans = solve();
    System.out.println(ans >= inf ? -1 : ans);
  }

  public static void main(String[] args) throws IOException {
    test();
//    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
//    int tests = Integer.parseInt(obj.readLine());
//    for (int t = 0; t < tests; t++) {
//      String[] inp = obj.readLine().split(" ");
//      n = Integer.parseInt(inp[0]);
//      k = Integer.parseInt(inp[1]);
//      prices = new int[k];
//      inp = obj.readLine().split(" ");
//      for (int i = 0; i < k; i++) {
//        prices[i] = Integer.parseInt(inp[i]);
//      }
//      int ans = solve();
//      System.out.println(ans >= inf ? -1 : ans);
//    }
  }
}
/*
2
3 5
-1 -1 4 5 -1
5 5
1 2 3 4 5
 */
