use std::cmp::max;
use std::cmp::min;

pub fn maximal_square(matrix: Vec<Vec<char>>) -> i32 {
  if matrix.is_empty() {
    return 0;
  }

  let rows = matrix.len();
  let cols = matrix[0].len();

  let mut ans = 0;

  // Extra space: O(n)
  let mut running_col: Vec<i32> = vec![0; cols];
  let mut maximals: Vec<i32> = vec![0; cols];

  (0..rows).for_each(|r| {
    let mut running_row = 0;
    let mut diagonal_ans = 0;
    (0..cols).for_each(|c| {
      if matrix[r][c] == '1' {
        let maximal = 1 + min(diagonal_ans, min(running_row, running_col[c]));
        ans = max(maximal, ans);
        running_row += 1;
        running_col[c] += 1;
        diagonal_ans = maximals[c];
        maximals[c] = maximal;
      } else {
        running_row = 0;
        running_col[c] = 0;
        maximals[c] = 0;
      }
    });
  });

  ans * ans
}

pub fn main() {
  let ans = maximal_square(vec![
    vec!['1', '1', '1', '0', '0'],
    vec!['1', '1', '1', '1', '1'],
    vec!['1', '1', '1', '1', '1'],
    vec!['1', '0', '0', '1', '0'],
  ]);

  println!("{}", ans)
}
