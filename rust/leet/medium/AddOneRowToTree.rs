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
}
use std::cell::RefCell;
use std::rc::Rc;

struct Solution {}
impl Solution {
  fn add_one_util(
    node: &Option<Rc<RefCell<TreeNode>>>,
    ins: i32,
    depth: i32,
    is_left: bool,
  ) -> Option<Rc<RefCell<TreeNode>>> {
    if depth == 1 {
      let mut tree_node = TreeNode::new(ins);
      let mut child: Option<Rc<RefCell<TreeNode>>> = None;
      if let Some(internal) = node {
        let mut cell = internal.as_ref().borrow_mut();
        let (val, left, right) = (cell.val, cell.left.take(), cell.right.take());
        child = Some(Rc::new(RefCell::new(TreeNode { val, left, right })));
      }
      if is_left {
        tree_node.left = child;
      } else {
        tree_node.right = child;
      }
      return Some(Rc::new(RefCell::new(tree_node)));
    }
    if let Some(internal) = node {
      let mut node = internal.as_ref().borrow_mut();
      let (val, left, right) = (node.val, node.left.take(), node.right.take());
      let mut tree_node = TreeNode::new(val);
      tree_node.left = Self::add_one_util(&left, val, depth, true);
      tree_node.right = Self::add_one_util(&right, val, depth, false);
      return Some(Rc::new(RefCell::new(tree_node)));
    }
    None
  }

  pub fn add_one_row(
    root: Option<Rc<RefCell<TreeNode>>>,
    val: i32,
    depth: i32,
  ) -> Option<Rc<RefCell<TreeNode>>> {
    Self::add_one_util(&root, val, depth, true)
  }
}

fn main() {}
