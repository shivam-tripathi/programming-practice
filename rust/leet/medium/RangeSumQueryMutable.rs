use std::vec;

pub struct BinaryIndexedTree {
  data: Vec<i32>,
}

impl BinaryIndexedTree {
  pub fn new(n: usize) -> Self {
    Self {
      data: vec![0; n + 1],
    }
  }

  fn query(&mut self, mut i: usize) -> i32 {
    let mut sum = 0;
    while i > 0 {
      sum += self.data[i];
      i = i - Self::lsb(i);
    }
    sum
  }

  fn update(&mut self, mut i: usize, delta: i32) {
    while i < self.data.len() {
      self.data[i] += delta;
      i += Self::lsb(i);
    }
  }

  fn lsb(num: usize) -> usize {
    let num = num as isize;
    (num & -num) as usize
  }
}

struct NumArray {
  bit: BinaryIndexedTree,
}

impl NumArray {
  fn new(nums: vec::Vec<i32>) -> Self {
    let mut bit = BinaryIndexedTree::new(nums.len());
    for (i, &num) in nums.iter().enumerate() {
      bit.update(i + 1, num);
    }
    Self { bit }
  }

  fn update(&mut self, i: i32, val: i32) {
    let i = i as usize;
    let cur = self.bit.query(i + 1) - self.bit.query(i);
    self.bit.update(i + 1, val - cur);
  }

  fn sum_range(&mut self, i: i32, j: i32) -> i32 {
    self.bit.query(j as usize + 1) - self.bit.query(i as usize)
  }
}

fn main() {}
