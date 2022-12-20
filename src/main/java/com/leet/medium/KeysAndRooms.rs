struct Solution {}

impl Solution {
  fn solve(visited: &mut Vec<bool>, rooms: &Vec<Vec<i32>>, idx: usize) -> usize {
    if visited[idx] {
      return 0_usize;
    }
    let mut distinct = 1_usize;
    visited[idx] = true;
    for next in 0..rooms[idx].len() {
      distinct += Solution::solve(visited, rooms, rooms[idx][next] as usize);
    }
    distinct
  }

  pub fn can_visit_all_rooms(rooms: Vec<Vec<i32>>) -> bool {
    let mut visited: Vec<bool> = vec![false; rooms.len()];
    Solution::solve(&mut visited, &rooms, 0_usize) == rooms.len()
  }
}
