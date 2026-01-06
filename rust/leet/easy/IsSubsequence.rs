struct Solution {}

impl Solution {
  pub fn is_subsequence(s: String, t: String) -> bool {
    let sv: Vec<char> = s.chars().collect();
    let tv: Vec<char> = t.chars().collect();

    let mut sp = 0;
    let mut tp = 0;

    while sp < s.len() && tp < t.len() {
      if sv[sp] == tv[tp] {
        sp += 1;
      }
      tp += 1;
    }

    sp == s.len()
  }
}
