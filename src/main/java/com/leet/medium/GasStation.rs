struct Solution {}

impl Solution {
  pub fn can_complete_circuit(gas: Vec<i32>, cost: Vec<i32>) -> i32 {
    let mut start = 0_usize;
    let mut end = 1_usize;
    let mut running = gas[0];
    let size = gas.len();

    let mut covered = 1;

    while (covered < size) {
      println!("{} -> {} = {}", start % size, end % size, running);
      if (running - cost[end % size]) < 0 {
        start += 1;
        if start == end {
          running = gas[end % size];
          end += 1;
        } else {
          running = running - gas[start % size] + cost[(start + 1) % size];
        }
      } else {
        end = end + 1;
        running += gas[end % size] - cost[end % size];
      }
    }

    start as i32
  }
}

fn main() {
  let gas = vec![1, 2, 3, 4, 5];
  let cost = vec![3, 4, 5, 1, 2];
  println!("{}", Solution::can_complete_circuit(gas, cost));
}
