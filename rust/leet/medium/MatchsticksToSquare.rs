/**
473. Matchsticks to Square
https://leetcode.com/problems/matchsticks-to-square/
Medium

You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick.
You want to use all the matchsticks to make one square. You should not break any stick, but you can
link them up, and each matchstick must be used exactly one time.

Return true if you can make this square and false otherwise.



Example 1:

Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length
1.

Example 2:

Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.

Constraints:
    1 <= matchsticks.length <= 15
    1 <= matchsticks[i] <= 108
 */

struct Solution {}

impl Solution {
  fn backtrack(matchsticks: &Vec<i32>, matches: &mut Vec<i32>, match_index: usize) -> bool {
    if match_index == matchsticks.len() {
      return matches.iter().all(|m| *m == 0);
    }

    for j in 0..4 {
      if matchsticks[match_index] <= matches[j] {
        matches[j] -= matchsticks[match_index];
        if Solution::backtrack(matchsticks, matches, match_index + 1) {
          return true;
        }
        matches[j] += matchsticks[match_index];
      }
    }

    false
  }

  pub fn makesquare(matchsticks: Vec<i32>) -> bool {
    let sum = matchsticks.iter().sum::<i32>();
    if sum % 4 != 0 {
      return false;
    }
    let avg = sum / 4;
    let mut sorted = matchsticks.clone();
    sorted.sort_by(|a, b| b.cmp(a));
    let mut matches = vec![avg; 4];

    Solution::backtrack(&sorted, &mut matches, 0)
  }
}

fn main() {
  for matchsticks in vec![
    vec![3, 3, 3, 3, 4],
    vec![1, 1, 2, 2, 2],
    vec![1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 102],
  ] {
    let ans = Solution::makesquare(matchsticks.clone());
    println!("{:?} {}", matchsticks, ans)
  }
}
