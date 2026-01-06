package main

type TreeNode struct {
   Val int
   Left *TreeNode
   Right *TreeNode
}

func flattenUtil(node *TreeNode, tail *TreeNode) *TreeNode {
  if node == nil {
    return tail
  }
  right := flatten_2(node.Right, tail)
  left := flatten_2(node.Left, right)
  node.Left = nil
  node.Right = left
  return node
}

func flatten(root *TreeNode)  {
  flattenUtil(root, nil)
}
