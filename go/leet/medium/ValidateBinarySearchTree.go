package main

func util(node *TreeNode, left int, right int, lInf bool, rInf bool) bool {
  if node == nil {
    return true
  }
  if !lInf && node.Val <= left {
    return false
  }
  if !rInf && node.Val >= right {
    return false
  }
  return util(node.Left, left, node.Val, lInf, false) && util(node.Right, node.Val, right, false, rInf)
}

func isValidBST(root *TreeNode) bool {
  return util(root, 0, 0, true, true)
}
