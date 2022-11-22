use std::collections::HashMap;

#[derive(Default)]
struct TimeMap {
  keys: HashMap<String, Vec<(i32, String)>>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl TimeMap {
  fn new() -> Self {
    Self::default()
  }

  fn set(&mut self, key: String, value: String, timestamp: i32) {
    self
      .keys
      .entry(key.clone())
      .or_default()
      .push((timestamp, value));
  }

  fn get(&self, key: String, timestamp: i32) -> String {
    if let Some(values) = self.keys.get(&key) {
      return match values.binary_search_by_key(&timestamp, |e| e.0) {
        Ok(i) => values[i].1.clone(),
        Err(i) if i > 0 => values[i - 1].1.clone(),
        _ => String::new(),
      };
    }
    String::new()
  }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * let obj = TimeMap::new();
 * obj.set(key, value, timestamp);
 * let ret_2: String = obj.get(key, timestamp);
 */
fn main() {
  let mut time_map = TimeMap::new();
  time_map.set("hello".to_string(), "world".to_string(), 1000);
  println!("{}", time_map.get("hello".to_string(), 1000));
}
