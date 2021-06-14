type Node struct {
  Val int
  Prev *Node
  Next *Node
  Child *Node
}


func flattenUtil(node *Node, tail *Node) *Node {
  if node == nil {
    return tail
  }
  tail = flattenUtil(node.Next, tail)
  tail = flattenUtil(node.Child, tail)
  node.Next = tail
  if tail != nil {
    tail.Prev = node
  }
  node.Child = nil
  return node
}

func flatten(node *Node) *Node {
  return flattenUtil(node, nil)
}
