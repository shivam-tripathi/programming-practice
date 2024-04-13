struct Solution {}

impl Solution {
  pub fn trap(height: Vec<i32>) -> i32 {
    let mut left = 0;
    let mut right = height.len() - 1;
    let mut left_max = 0;
    let mut right_max = 0;
    let mut result = 0;

    while left < right {
      if height[left] < height[right] {
        if height[left] >= left_max {
          left_max = height[left];
        } else {
          result += left_max - height[left];
        }
        left += 1;
      } else {
        if height[right] >= right_max {
          right_max = height[right];
        } else {
          result += right_max - height[right];
        }
        right -= 1;
      }
    }

    result
  }
}

#[cfg(test)]
mod tests {
  use super::*;

  #[test]
  fn test_trapping_rainwater() {
    assert_eq!(Solution::trap(vec![0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]), 6);
    assert_eq!(Solution::trap(vec![4, 2, 0, 3, 2, 5]), 9);
    assert_eq!(Solution::trap(vec![4, 3, 2, 1]), 0);
  }
}

fn main() {
  println!(
    "{}",
    Solution::trap(vec![0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1])
  );
}
