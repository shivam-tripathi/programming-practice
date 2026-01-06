struct Solution {}

impl Solution {
  pub fn climb_stairs(n: i32) -> i32 {
    let (mut a, mut b) = (1, 1);
    for i in 1..n + 1 {
      let tmp = a + b;
      a = b;
      b = tmp;
    }
    a
  }
}
