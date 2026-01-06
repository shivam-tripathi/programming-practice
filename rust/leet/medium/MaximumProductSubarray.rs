struct Solution {}

impl Solution {
  pub fn max_product(nums: Vec<i32>) -> i32 {
    let mut running_positive = 1;
    let mut running_negative = 1;
    let mut ans = nums[0];

    for &num in nums.iter() {
      if num > 0 {
        running_positive *= num;
        running_negative *= num;
        ans = std::cmp::max(ans, std::cmp::max(running_negative, running_positive));
      } else if num < 0 {
        let tmp = running_positive;
        running_positive = running_negative * num;
        running_negative = tmp * num;
        ans = std::cmp::max(ans, std::cmp::max(running_negative, running_positive));
      } else {
        running_positive = 1;
        running_negative = 1;
        ans = std::cmp::max(ans, 0);
      }

      running_positive = std::cmp::max(1, running_positive);
      if running_negative > 0 {
        running_negative = 1;
      }
    }

    std::cmp::max(ans, std::cmp::max(running_positive, running_negative))
  }
}

fn main() {
  let ans = Solution::max_product(vec![-3, 1, 4]);
  println!("{:?}", ans);
}
