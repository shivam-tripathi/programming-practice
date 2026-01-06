// https://leetcode.com/problems/add-binary/

struct Solution {}

impl Solution {
  pub fn add_binary(a: String, b: String) -> String {
    let (mut ai, mut bi) = (a.len() - 1, b.len() - 1);
    let avec = a.chars().collect::<Vec<char>>();
    let bvec = b.chars().collect::<Vec<char>>();
    let (mut sum, mut carry) = (0_i32, 0_i32);
    let mut ans = vec![];

    while ai < avec.len() || bi < bvec.len() {
      let mut count = carry;
      if ai < avec.len() && ai >= 0 && avec[ai] == '1' {
        count += 1;
      }
      if bi < bvec.len() && bi >= 0 && bvec[bi] == '1' {
        count += 1;
      }
      let res = match count {
        0 => (0, 0),
        1 => (1, 0),
        2 => (0, 1),
        3 => (1, 1),
        _ => (0, 0),
      };
      ai -= 1;
      bi -= 1;
      ans.push(res.0);
      carry = res.1;
    }
    if carry == 1 {
      ans.push(1);
    }
    ans.reverse();
    let rev = ans.iter().map(|e| e.to_string()).collect::<String>();
    rev
  }
}
