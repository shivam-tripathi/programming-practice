struct Solution {}

impl Solution {
  pub fn rob(nums: Vec<i32>) -> i32 {
    let (mut prev, mut prev_prev) = (0, 0);
    for num in nums {
      let cur = prev.max(prev_prev + num);
      prev_prev = prev;
      prev = cur;
    }
    prev.max(prev_prev)
  }
}
