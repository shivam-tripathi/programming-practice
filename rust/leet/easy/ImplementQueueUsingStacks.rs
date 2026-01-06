#[derive(Default)]
struct MyQueue {
  input: Vec<i32>,
  output: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl MyQueue {
  fn new() -> Self {
    Self::default()
  }

  fn push(&mut self, x: i32) {
    self.input.push(x);
  }

  fn flush(&mut self) {
    while let Some(value) = self.input.pop() {
      self.output.push(value);
    }
  }

  fn pop(&mut self) -> i32 {
    if self.output.len() == 0 {
      self.flush();
    }
    self.output.pop().unwrap()
  }

  fn peek(&mut self) -> i32 {
    if self.output.len() == 0 {
      self.flush();
    }
    self.output[self.output.len() - 1]
  }

  fn empty(&self) -> bool {
    self.input.len() == 0 && self.output.len() == 0
  }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 */

fn main() {
  let mut obj = MyQueue::new();
  obj.push(1);
  let ret_3: i32 = obj.peek();
  println!("peek {}", ret_3);
  let ret_2: i32 = obj.pop();
  println!("pop {}", ret_2);
  let ret_4: bool = obj.empty();
  println!("empty {}", ret_4);
}
