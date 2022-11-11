struct Solution {}

impl Solution {
  pub fn remove_duplicates(nums: &mut Vec<i32>) -> i32 {
    let mut next: usize = 1;
    let mut prev: usize = 0;
    while next < nums.len() {
      if nums[next] == nums[prev] {
        next += 1;
      } else {
        if nums[prev + 1] != nums[next] {
          nums[prev + 1] = nums[next];
        }
        prev += 1;
        next += 1;
      }
    }
    (prev + 1) as i32
  }
}

fn main() {}
