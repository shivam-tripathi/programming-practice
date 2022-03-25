struct Solution {}

impl Solution {
  pub fn num_rescue_boats(people: Vec<i32>, limit: i32) -> i32 {
    let mut a = people.clone();
    // unstable sort is fasterrrrr
    a.sort_unstable();
    let (mut begin, mut end, mut ans) = (0 as i32, (a.len() - 1) as i32, 0);

    while begin <= end {
      if (a[begin as usize] + a[end as usize]) <= limit {
        begin += 1;
        end -= 1;
      } else {
        end -= 1;
      }
      ans += 1;
    }

    ans
  }
}
