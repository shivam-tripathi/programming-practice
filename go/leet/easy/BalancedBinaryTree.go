package main

func depth(node *TreeNode) (int, bool) {
  if node == nil {
    return 0, true
  }
  left, lvalid := depth(node.Left)
  if !lvalid {
    return 0, lvalid
  }
  right, rvalid := depth(node.Right)
  if !rvalid {
    return 0, rvalid
  }
  if right - left > 1 || right - left < -1 {
    return 0, false
  }
  d := left
  if d < right {
    d = right
  }
  return 1+d, true
}

func isBalanced(root *TreeNode) bool {
  _, valid := depth(root)
  return valid
}
