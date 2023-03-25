use std::{cell::RefCell, collections::HashMap, rc::Rc};

struct ListNode {
  key: i32,
  prev: Option<Rc<RefCell<ListNode>>>,
  next: Option<Rc<RefCell<ListNode>>>,
}

impl ListNode {
  fn new(key: i32) -> Self {
    Self {
      key,
      prev: None,
      next: None,
    }
  }

  fn pluck(&mut self) {
    if let Some(node) = &self.prev {
      node.borrow_mut().next = self.next.as_ref().map(|e| e.clone())
    }
    if let Some(node) = &self.next {
      node.borrow_mut().prev = self.prev.as_ref().map(|e| e.clone());
    }
    self.next = None;
    self.prev = None;
  }
}

struct FreqBin {
  val: i32,
  count: i32,
  map: HashMap<i32, Rc<RefCell<ListNode>>>,
  head: Option<Rc<RefCell<ListNode>>>,
  tail: Option<Rc<RefCell<ListNode>>>,
}

impl FreqBin {
  fn new(val: i32) -> Self {
    Self {
      val,
      count: 0,
      map: HashMap::default(),
      head: None,
      tail: None,
    }
  }

  fn push_back(&mut self, key: i32) {
    let new_node = Rc::new(RefCell::new(ListNode::new(val)));
    if let Some(node) = &self.tail {
      node.borrow_mut().next = Some(new_node.clone());
      self.tail = Some(new_node.clone());
    } else {
      self.head = Some(new_node.clone());
      self.tail = Some(new_node.clone());
    }
    self.map.insert(key, new_node.clone());
    self.count += 1;
  }

  fn remove_key(&mut self, key: i32) {
    if let Some(node) = self.map.get(&key) {
      // if self.tail.
    }
  }

  fn pop_front(&mut self) {
    if let Some(node) = &self.head {
      self.head = node.borrow_mut().next;
      node.borrow_mut().next = None;
    }
  }
}

fn main() {}
