package main

type TreeNode struct {
  Val int
  Left *TreeNode
  Right *TreeNode
}

func bfs(root *TreeNode) int {
  queue := []*TreeNode{root}
  var ans int
  for len(queue) != 0 {
    cur := []*TreeNode{}
    ans = 0
    for _, node := range queue {
      if node.Left == nil && node.Right == nil {
        ans += node.Val
      } else {
        if node.Left != nil {
          cur = append(cur, node.Left)
        }
        if node.Right != nil {
          cur = append(cur, node.Right)
        }
      }
    }
    queue = cur
  }
  return ans
}

type Status struct {
  level int
  sum int
}

func dfs(root *TreeNode, status *Status, level int) {
  if root == nil {
    return
  }
  if root.Left == nil && root.Right == nil {
    if level == status.level {
      status.sum += root.Val
    }
    if level > status.level {
      status.level = level
      status.sum = root.Val
    }
  }
  dfs(root.Left, status, level+1)
  dfs(root.Right, status, level+1)
}

