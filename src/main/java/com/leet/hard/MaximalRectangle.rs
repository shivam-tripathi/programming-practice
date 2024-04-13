struct Solution {}

impl Solution {
  pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
    if matrix.is_empty() {
      return 0;
    }
    let mut max_area = 0;
    let mut heights = vec![0; matrix[0].len()];
    for row in matrix {
      for (i, &c) in row.iter().enumerate() {
        heights[i] = if c == '1' { heights[i] + 1 } else { 0 };
      }
      max_area = max_area.max(Self::largest_rectangle_area(&heights));
    }
    max_area
  }

  fn largest_rectangle_area(heights: &Vec<i32>) -> i32 {
    let mut stack = vec![];
    let mut max_area = 0;
    for i in 0..heights.len() {
      while !stack.is_empty() && heights[i] < heights[*stack.last().unwrap()] {
        let h = heights[stack.pop().unwrap()];
        let w = stack.last().map_or(i, |&x| i - x - 1);
        max_area = max_area.max(h * w as i32);
      }
      stack.push(i);
    }
    while !stack.is_empty() {
      let h = heights[stack.pop().unwrap()];
      let w = stack
        .last()
        .map_or(heights.len(), |&x| heights.len() - x - 1);
      max_area = max_area.max(h * w as i32);
    }
    max_area
  }
}

fn main() {
  println!(
    "{}",
    Solution::maximal_rectangle(vec![
      vec!['1', '0', '1', '0', '0'],
      vec!['1', '0', '1', '1', '1'],
      vec!['1', '1', '1', '1', '1'],
      vec!['1', '0', '0', '1', '0']
    ])
  );
}
