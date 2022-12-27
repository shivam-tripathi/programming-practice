struct Solution {}

impl Solution {
  pub fn maximum_bags(capacity: Vec<i32>, rocks: Vec<i32>, additional_rocks: i32) -> i32 {
    let mut spaces: Vec<i32> = (0..capacity.len())
      .map(|idx| capacity[idx] - rocks[idx])
      .collect();
    spaces.sort_unstable();
    let mut ans = 0;
    let mut remaining = additional_rocks;
    for space in spaces {
      if space <= remaining {
        ans += 1;
        remaining = remaining - space;
      } else {
        break;
      }
    }
    ans
  }
}
