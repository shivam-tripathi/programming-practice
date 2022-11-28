use std::collections::BTreeMap;

struct Solution {}

impl Solution {
  pub fn find_winners(matches: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
    let mut losses = BTreeMap::default();

    matches.iter().for_each(|round| {
      *losses.entry(round[1]).or_insert(0) += 1;
      losses.entry(round[0]).or_insert(0);
    });

    let mut ans = vec![vec![], vec![]];
    for (player_id, loss_count) in losses {
      if loss_count <= 1 {
        ans[loss_count as usize].push(player_id);
      }
    }

    ans
  }
}

fn main() {
  let ans = Solution::find_winners(vec![
    vec![1, 3],
    vec![2, 3],
    vec![3, 6],
    vec![5, 6],
    vec![5, 7],
    vec![4, 5],
    vec![4, 8],
    vec![4, 9],
    vec![10, 4],
    vec![10, 9],
  ]);
  println!("{:?}", ans);
}
