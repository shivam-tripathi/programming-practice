use std::collections::HashMap;

struct Allocator {
  map: HashMap<i32, Vec<(i32, i32)>>,
  data: Vec<i32>,
}

impl Allocator {
  fn new(n: i32) -> Self {
    Allocator {
      map: HashMap::new(),
      data: vec![0; n as usize],
    }
  }

  fn allocate(&mut self, size: i32, m_id: i32) -> i32 {
    let (mut start, mut count) = (-1, 0);
    for i in 0..self.data.len() {
      if self.data[i] == 1 {
        count = 0;
      } else {
        count += 1;
      }
      if count >= size {
        start = i as i32 - count + 1;
        break;
      }
    }
    if start == -1 {
      return -1;
    }
    for k in 0..size {
      self.data[start as usize + k as usize] = 1;
    }
    self.map.entry(m_id).or_insert(vec![]).push((start, size));
    start
  }
  fn free(&mut self, m_id: i32) -> i32 {
    let mut freed = 0;
    if let Some(mappings) = self.map.get(&m_id) {
      for (start, size) in mappings {
        for i in 0..*size {
          self.data[*start as usize + i as usize] = 0;
          freed += 1;
        }
      }
    }
    self.map.remove(&m_id);
    freed
  }
}

fn main() {}
