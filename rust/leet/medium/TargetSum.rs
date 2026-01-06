struct Solution {}

use std::collections::HashMap;

impl Solution {
  fn solve(
    nums: &Vec<i32>,
    pos: usize,
    target: i32,
    dp: &mut HashMap<usize, HashMap<i32, i32>>,
  ) -> i32 {
    if pos >= nums.len() {
      if target == 0 {
        return 1;
      } else {
        return 0;
      }
    }
    if dp.contains_key(&pos) && dp.get(&pos).unwrap().contains_key(&target) {
      let ans = dp.get(&pos).unwrap().get(&target).unwrap();
      return ans.clone();
    }
    let plus = Solution::solve(nums, pos + 1, target - nums[pos], dp);
    let minus = Solution::solve(nums, pos + 1, target + nums[pos], dp);
    if !dp.contains_key(&pos) {
      dp.insert(pos, HashMap::new());
    }
    dp.get_mut(&pos).unwrap().insert(target, plus + minus);
    return plus + minus;
  }

  pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
    let mut dp = HashMap::new();
    return Solution::solve(&nums, 0, target, &mut dp);
  }
}

fn main() {
  let nums = vec![1, 1, 1, 1, 1];
  let target = 3;
  let ans = Solution::find_target_sum_ways(nums, target);
  println!("{}", ans);
}
