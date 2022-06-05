struct Solution {}

impl Solution {
  pub fn solve_n_queens(n: i32) -> Vec<Vec<String>> {
    fn render(board: &Vec<usize>, size: usize) -> Vec<String> {
      board
        .iter()
        .map(|&pos| ".".repeat(pos) + "Q" + &".".repeat(size - pos - 1))
        .collect()
    }

    fn is_valid(board: &Vec<usize>, row: usize, col: usize) -> bool {
      for i in 0..row {
        let diff: usize;
        if col < board[i] {
          diff = board[i] - col;
        } else {
          diff = col - board[i];
        }
        if board[i] == col || (row - i) == diff {
          return false;
        }
      }
      true
    }

    fn back_track(size: usize, row: usize, board: &mut Vec<usize>, res: &mut Vec<Vec<String>>) {
      if row >= size {
        res.push(render(board, size));
      } else {
        for col in 0..size {
          if is_valid(board, row, col) {
            board[row] = col;
            back_track(size, row + 1, board, res);
          }
        }
      }
    }

    let mut board = vec![0 as usize; n as usize];
    println!("{:?}", board);
    let mut res = vec![];
    back_track(n as usize, 0, &mut board, &mut res);
    res
  }
}

fn main() {
  let res = Solution::solve_n_queens(4);
  println!("{:?}", res);
}
