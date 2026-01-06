"""
Given an array coins[] of length n and a sum, where coins[i] is the value of the i_th coin.
Find the number of ways to make the sum using the
coins. You can use any coin any number of times.
"""


# CoinChange is a struct
class CoinChange:
    coins: list[int]
    sum: int

    def __init__(self, coins: list[int], sum: int):
        self.coins = sorted(coins)
        self.sum = sum


# _count_ways returns the number of ways to make the sum using the coins
def _count_ways(coins: list[int], target: int, cur_sum: int, dp: dict[int, int]) -> int:
    """
    coins: list of coins
    sum: sum to be made
    n: number of coins
    """
    if cur_sum > target:
        return 0

    if cur_sum == target:
        return 1

    ans = 0
    for coin in coins:
        for change in range(cur_sum, target + 1, coin):
            ans += _count_ways(coins, sum - change, dp)

    dp[sum] = ans
    return ans


# CountWays returns the number of ways to make the sum using the coins
def count_ways(c: CoinChange) -> int:
    return _count_ways(c.coins, c.sum, {})


def main():
    coin_change = CoinChange(coins=[1, 2, 3], sum=4)
    print(count_ways(coin_change))  # 4
    coin_change = CoinChange(coins=[2, 5, 3, 6], sum=10)
    print(count_ways(coin_change))  # 5


main()
