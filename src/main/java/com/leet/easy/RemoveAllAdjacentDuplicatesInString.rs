struct Solution {}

impl Solution {
  pub fn remove_duplicates(s: String) -> String {
    let mut stack: Vec<char> = vec![];

    for ch in s.chars() {
      if let Some(elem) = stack.last() {
        if *elem == ch {
          stack.pop();
        } else {
          stack.push(ch);
        }
      } else {
        stack.push(ch);
      }
    }

    stack.iter().collect::<String>()
  }
}

fn main() {
  let s = String::from("abbaca");
  let ans = Solution::remove_duplicates(s.clone());
  println!("{} -> {}", s, ans);
}
