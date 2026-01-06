use std::collections::HashMap;

use rand::{rngs::ThreadRng, Rng};

#[derive(Default)]
struct RandomizedSet {
  elems: HashMap<i32, usize>,
  idxs: HashMap<usize, i32>,
  size: usize,
  rng: ThreadRng,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl RandomizedSet {
  fn new() -> Self {
    Self::default()
  }

  fn insert(&mut self, val: i32) -> bool {
    if self.elems.contains_key(&val) {
      return false;
    }
    self.elems.insert(val, self.size.clone());
    self.idxs.insert(self.size.clone(), val);
    self.size += 1;
    true
  }

  fn remove(&mut self, val: i32) -> bool {
    if !self.elems.contains_key(&val) {
      return false;
    }

    // get location and remove the elem
    let pos = self.elems.remove(&val).unwrap();

    // update the last elem index
    let last_pos = self.size - 1;
    let last_elem = self.idxs.get(&last_pos).unwrap().clone();
    self.elems.entry(last_elem).and_modify(|e| *e = pos);
    self.idxs.entry(pos).and_modify(|e| *e = last_elem);

    self.size -= 1;

    true
  }

  fn get_random(&mut self) -> i32 {
    let idx = self.rng.gen_range(0..self.size);
    (*self.idxs.get(&idx).unwrap()).clone()
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 *
 */

fn main() {
  let mut obj = RandomizedSet::new();
  println!("ins 1 {}", obj.insert(1));
  println!("ins 2 {}", obj.insert(2));
  println!("rem 1 {}", obj.remove(1));
  println!("rand  {}", obj.get_random());
}
