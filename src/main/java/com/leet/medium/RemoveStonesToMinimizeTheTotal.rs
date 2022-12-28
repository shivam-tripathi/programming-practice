use std::collections::BinaryHeap;

impl Solution {
  pub fn min_stone_sum(piles: Vec<i32>, k: i32) -> i32 {
    let mut heap: BinaryHeap<i32> = BinaryHeap::new();
    piles.iter().for_each(|pile| heap.push(*pile));
    let mut turns = 0;
    while turns < k {
      let top = heap.pop().unwrap();
      if top == 0 {
        break;
      }
      heap.push(top - (top / 2));
      turns += 1;
    }
    heap.into_iter().reduce(|acc, e| acc + e).unwrap()
  }
}
