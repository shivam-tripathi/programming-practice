package main

func levelOrder(root *TreeNode) [][]int {
  if root == nil {
    return [][]int{}
  }
  level := []*TreeNode{root}
  var ans [][]int = [][]int{}
  for len(level) != 0 {
    cur := []int{}
    nextLevel := []*TreeNode{}
    for _, node := range level {
      cur = append(cur, node.Val)
      if node.Left != nil {
        nextLevel = append(nextLevel, node.Left)
      }
      if node.Right != nil {
        nextLevel = append(nextLevel, node.Right)
      }
    }
    ans = append(ans, cur)
    level = nextLevel
  }
  return ans
}
