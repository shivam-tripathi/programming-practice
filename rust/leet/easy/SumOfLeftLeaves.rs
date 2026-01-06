use std::{cell::RefCell, rc::Rc};

// Definition for a binary tree node.
#[derive(Debug, PartialEq, Eq)]
pub struct TreeNode {
  pub val: i32,
  pub left: Option<Rc<RefCell<TreeNode>>>,
  pub right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
  #[inline]
  pub fn new(val: i32) -> Self {
    TreeNode {
      val,
      left: None,
      right: None,
    }
  }

  pub fn from_array(arr: &[Option<i32>]) -> Option<Rc<RefCell<TreeNode>>> {
    if arr.is_empty() {
      return None;
    }
    let root = Some(Rc::new(RefCell::new(TreeNode::new(arr[0].unwrap()))));
    let mut queue = vec![root.as_ref().unwrap().clone()];
    let mut i = 1;
    while i < arr.len() {
      let node = queue.remove(0);
      if let Some(val) = arr[i] {
        node.borrow_mut().left = Some(Rc::new(RefCell::new(TreeNode::new(val))));
        queue.push(node.borrow().left.as_ref().unwrap().clone());
      }
      i += 1;
      if i < arr.len() {
        if let Some(val) = arr[i] {
          node.borrow_mut().right = Some(Rc::new(RefCell::new(TreeNode::new(val))));
          queue.push(node.borrow().right.as_ref().unwrap().clone());
        }
        i += 1;
      }
    }
    root
  }
}

struct SumOfLeftLeaves {}

impl SumOfLeftLeaves {
  pub fn sum_of_left_leaves(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut sum = 0;
    Self::dfs(&root, &mut sum, false);
    sum
  }

  fn dfs(node: &Option<Rc<RefCell<TreeNode>>>, sum: &mut i32, is_left: bool) {
    if let Some(node) = node {
      let node = node.borrow();
      if node.left.is_none() && node.right.is_none() && is_left {
        *sum += node.val;
      }
      Self::dfs(&node.left, sum, true);
      Self::dfs(&node.right, sum, false);
    }
  }

  pub fn sum_of_left_leaves_iterative(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut sum = 0;
    let mut stack = vec![(root, false)];
    while !stack.is_empty() {
      if let Some((node, is_left)) = stack.pop() {
        if let Some(node) = node {
          let node = node.borrow();
          if node.left.is_none() && node.right.is_none() && is_left {
            sum += node.val;
          }
          stack.push((node.right.clone(), false));
          stack.push((node.left.clone(), true));
        }
      }
    }
    sum
  }

  pub fn sum_of_left_leaves_iterative_v2(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
    let mut sum = 0;
    let mut stack = vec![(root, false)];
    while !stack.is_empty() {
      if let Some((node, is_left)) = stack.pop() {
        if let Some(node) = node {
          let node = node.borrow();
          if node.left.is_none() && node.right.is_none() && is_left {
            sum += node.val;
          }
          stack.push((node.right.clone(), false));
          stack.push((node.left.clone(), true));
        }
      }
    }
    sum
  }
}

fn main() {
  let root = TreeNode::from_array(&[Some(3), Some(9), Some(20), None, None, Some(15), Some(7)]);
  println!("{}", SumOfLeftLeaves::sum_of_left_leaves(root));
  let root = TreeNode::from_array(&[Some(3), Some(9), Some(20), None, None, Some(15), Some(7)]);
  println!("{}", SumOfLeftLeaves::sum_of_left_leaves_iterative(root));
  let root = TreeNode::from_array(&[Some(3), Some(9), Some(20), None, None, Some(15), Some(7)]);
  println!("{}", SumOfLeftLeaves::sum_of_left_leaves_iterative_v2(root));
}
