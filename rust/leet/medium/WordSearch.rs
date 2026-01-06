use std::collections::HashMap;

static OFFSETS: [i32; 5] = [1, 0, -1, 0, 1];

struct Solution {}

impl Solution {
  pub fn dfs(
    board: &Vec<Vec<char>>,
    word: &Vec<char>,
    row: usize,
    col: usize,
    mut idx: usize,
    done: &mut HashMap<usize, bool>,
  ) -> bool {
    if row >= board.len() || col >= board[0].len() || board[row][col] != word[idx] {
      return false;
    }

    if let Some(seen) = done.get(&(row + col * board.len())) {
      if *seen {
        return false;
      }
    }

    idx += 1;

    if idx == word.len() {
      return true;
    }

    done.insert(row + col * board.len(), true);

    for i in 0..(OFFSETS.len() - 1) {
      let next_row = (row as i32) + OFFSETS[i];
      let next_col = (col as i32) + OFFSETS[i + 1];
      println!("{},{} -> {},{}", row, col, next_row, next_col);
      if next_row >= 0 && next_col >= 0 {
        if Self::dfs(board, word, next_row as usize, next_col as usize, idx, done) {
          return true;
        }
      }
    }

    done
      .entry(row + col * board.len())
      .and_modify(|e| *e = false);

    false
  }

  pub fn exist(board: Vec<Vec<char>>, word: String) -> bool {
    for i in 0..board.len() {
      for j in 0..board[i].len() {
        if Self::dfs(
          &board,
          &word.chars().collect::<Vec<char>>(),
          i,
          j,
          0,
          &mut HashMap::new(),
        ) {
          return true;
        }
      }
    }
    false
  }
}

fn main() {
  let mut input = vec![
    vec!['A', 'B', 'C', 'E'],
    vec!['S', 'F', 'C', 'S'],
    vec!['A', 'D', 'E', 'E'],
  ];
  // println!("{}", Solution::exist(input, "ABCCED".to_string()));
  input = vec![
    vec!['A', 'B', 'C', 'E'],
    vec!['S', 'F', 'C', 'S'],
    vec!['A', 'D', 'E', 'E'],
  ];
  println!("{}", Solution::exist(input, "SEE".to_string()));
}
