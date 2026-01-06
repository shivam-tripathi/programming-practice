struct Solution {}

impl Solution {
  pub fn title_to_number(column_title: String) -> i32 {
    let mut base = 26_i32.pow((column_title.len() - 1) as u32);
    let mut ans: i32 = 0;
    for ch in column_title.chars() {
      ans += (ch as i32 - 'A' as i32 + 1) * base;
      base /= 26;
    }
    ans
  }
}
