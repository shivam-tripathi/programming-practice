package main

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

func sumNumbersUtil(node *TreeNode, prev int) int {
  if node == nil {
    return 0;
  }
  prev = prev * 10 + node.Val
  sum := sumNumbersUtil(node.Left, prev) + sumNumbersUtil(node.Right, prev)
  if sum == 0 {
    return prev
  }
  return sum
}

func sumNumbers(root *TreeNode) int {
  return sumNumbersUtil(root, 0)
}
