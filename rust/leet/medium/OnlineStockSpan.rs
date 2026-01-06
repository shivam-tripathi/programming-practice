/*
901. Online Stock Span
https://leetcode.com/problems/online-stock-span/description/
Medium

Design an algorithm that collects daily price quotes for some stock and returns the span of that
stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting
  from today and going backward) for which the stock price was less than or equal to today's price.

    For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85], then the
    stock spans would be [1,1,1,2,1,4,6].

Implement the StockSpanner class:

    StockSpanner() Initializes the object of the class.
    int next(int price) Returns the span of the stock's price given that today's price is price.

Example 1:
Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6

Constraints:
  1 <= price <= 105
  At most 104 calls will be made to next.
 */

struct StockSpanner {
  prices: Vec<(i32, usize)>,
  size: usize,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl StockSpanner {
  fn new() -> Self {
    StockSpanner {
      prices: vec![],
      size: 0,
    }
  }

  fn next(&mut self, price: i32) -> i32 {
    while let Some((value, _)) = self.prices.last() {
      if *value <= price {
        self.prices.pop();
      } else {
        break;
      }
    }
    let ans;
    if let Some((_, idx)) = self.prices.last() {
      ans = self.size - idx;
    } else {
      ans = self.size + 1;
    }
    self.prices.push((price, self.size));
    self.size = self.size + 1;
    ans as i32
  }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * let obj = StockSpanner::new();
 * let ret_1: i32 = obj.next(price);
 */

fn main() {
  let inp = vec![100, 80, 60, 70, 60, 75, 85];
  let mut sol = StockSpanner::new();
  for price in inp {
    let ans = sol.next(price);
    println!("{}", ans);
  }
}

// [null, 1, 1, 1, 2, 1, 4, 6]
