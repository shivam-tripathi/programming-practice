/*
295. Find Median from Data Stream
https://leetcode.com/problems/find-median-from-data-stream/
Hard

The median is the middle value in an ordered integer list. If the size of the list is even, there is
no middle value, and the median is the mean of the two middle values.
  For example, for arr = [2,3,4], the median is 3.
  For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.

Implement the MedianFinder class:
  - MedianFinder() initializes the MedianFinder object.
  - void addNum(int num) adds the integer num from the data stream to the data structure.
  - double findMedian() returns the median of all elements so far. Answers within 10-5 of the
  actual answer will be accepted.

Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

Constraints:
    -105 <= num <= 105
    There will be at least one element in the data structure before calling findMedian.
    At most 5 * 104 calls will be made to addNum and findMedian.

Follow up:
  - If all integer numbers from the stream are in the range [0, 100], how would you optimize your
  solution?
  - If 99% of all integer numbers from the stream are in the range [0, 100], how would you
  optimize your solution?
 */

use std::cmp::Reverse;
use std::collections::BinaryHeap;

#[derive(Default)]
struct MedianFinder {
  small: BinaryHeap<i32>,
  large: BinaryHeap<Reverse<i32>>,
  odd: bool,
}

impl MedianFinder {
  fn new() -> Self {
    MedianFinder::default()
  }

  fn add_num(&mut self, item: i32) {
    if self.odd {
      self.large.push(Reverse(item));
      if let Some(Reverse(smallest)) = self.large.pop() {
        self.small.push(smallest);
      }
    } else {
      self.small.push(item);
      if let Some(largest) = self.small.pop() {
        self.large.push(Reverse(largest));
      }
    }
    self.odd = !self.odd
  }

  fn find_median(&self) -> f64 {
    if self.odd {
      return self.large.peek().unwrap().0 as f64;
    }
    let mid_left = *self.small.peek().unwrap();
    let mid_right = self.large.peek().unwrap().0;
    (mid_left + mid_right) as f64 / 2.0
  }
}

fn main() {
  println!("starting...");
  let mut median_finder = MedianFinder::new();
  median_finder.add_num(1);
  println!("add num 1");
  median_finder.add_num(2);
  println!("add num 2");
  println!(">> {}", median_finder.find_median());
  median_finder.add_num(3);
  println!("add num 3");
  println!(">> {}", median_finder.find_median());
  println!("done");
}
