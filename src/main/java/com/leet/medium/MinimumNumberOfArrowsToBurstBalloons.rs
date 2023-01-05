struct Solution {}

impl Solution {
  pub fn find_min_arrow_shots(points: Vec<Vec<i32>>) -> i32 {
    let mut points = points;
    points.sort_unstable_by(|p1, p2| p1[0].cmp(&p2[0]));
    let mut ans = 1;
    let mut prev_end = points[0][1];
    for point in points {
      if prev_end < point[0] {
        ans += 1;
        prev_end = point[1];
      } else {
        prev_end = prev_end.min(point[1]);
      }
    }
    ans
  }
}
