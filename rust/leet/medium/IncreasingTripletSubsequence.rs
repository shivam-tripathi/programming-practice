struct Solution {}

impl Solution {
  pub fn increasing_triplet(nums: Vec<i32>) -> bool {
    if nums.len() < 3 {
      return false;
    }

    let mut low = 0;
    let mut mid = None;

    for i in 1..nums.len() {
      if let Some(mid_idx) = mid {
        if nums[i] > nums[mid_idx] {
          return true;
        }
      }
      if nums[i] > nums[low] {
        mid = Some(i);
      } else {
        low = i;
      }
    }

    false
  }
}
