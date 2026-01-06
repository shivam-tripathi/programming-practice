struct Solution {}

impl Solution {
  pub fn sorted_squares(nums: Vec<i32>) -> Vec<i32> {
    let mut low = 0;
    let mut high = nums.len() - 1;
    let mut ans = vec![0; nums.len()];
    let mut pos = nums.len() - 1;

    while low < high {
      if nums[low].abs() > nums[high].abs() {
        ans[pos] = nums[low] * nums[low];
        low += 1;
      } else {
        ans[pos] = nums[high] * nums[high];
        high -= 1;
      }
      pos -= 1;
    }
    ans[pos] = nums[low] * nums[low];
    ans
  }
}

fn main() {
  let ans = Solution::sorted_squares(vec![-7, -3, 2, 3, 11]);
  println!("{:?}", ans);
}
