package main

/**
 * Definition for a binary tree node.
 */
type TreeNode struct {
  Val int
  Left *TreeNode
  Right *TreeNode
}

var forest []*TreeNode

func delNodesUtil(node *TreeNode, toDel map[int]bool, addCur bool) *TreeNode {
  if node == nil {
    return nil
  }
  ret := node
  addNext := false
  if _, ok := toDel[node.Val]; ok {
    ret = nil
    addNext = true
  } else if addCur {
    forest = append(forest, node)
  }

  node.Left = delNodesUtil(node.Left, toDel, addNext)
  node.Right = delNodesUtil(node.Right, toDel, addNext)

  return ret
}

func delNodes(root *TreeNode, to_delete []int) []*TreeNode {
  toDel := map[int]bool{}
  for _, delVal := range to_delete {
    toDel[delVal] = true
  }
  forest = []*TreeNode{}
  delNodesUtil(root, toDel, true)
  return forest
}
