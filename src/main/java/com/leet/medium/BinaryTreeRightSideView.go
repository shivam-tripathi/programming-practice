package main

func util(node *TreeNode, view []int, curDepth int, maxDepth int) ([]int, int) {
  if node == nil {
    return view, curDepth-1;
  }

  if curDepth > maxDepth {
    view = append(view, node.Val)
  }

  view, right := util(node.Right, view, curDepth+1, maxDepth)
  if right > maxDepth {
    maxDepth = right
  }

  view, left := util(node.Left, view, curDepth+1, maxDepth)
  if left > maxDepth {
    maxDepth = left
  }

  return view, maxDepth
}

func rightSideView(root *TreeNode) []int {
  view, _ := util(root, []int{}, 1, 0)
  return view
}
