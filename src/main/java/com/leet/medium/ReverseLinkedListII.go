package main

// OnePass
func onePassReverseBetween(head *ListNode, left int, right int) *ListNode {
  if left == right {
    return head
  }
  var leftNode, rightNode, leftPrev, rightNext *ListNode = nil, nil, nil, nil
  sentinel := &ListNode{Val: 0, Next: head}
  pos := 0
  var node, prev *ListNode = sentinel, nil

  mark := false
  var prevMark *ListNode

  for pos <= right {
    if pos == left {
      leftNode = node
      leftPrev = prev
      mark = true
      prevMark = nil
    }

    if pos == right {
      rightNode = node
      rightNext = node.Next
    }

    next := node.Next
    prev = node

    if mark {
      node.Next = prevMark
      prevMark = node
    }

    node = next
    pos++
  }

  leftPrev.Next = rightNode
  leftNode.Next = rightNext

  return sentinel.Next
}

func reverseBetween(head *ListNode, left int, right int) *ListNode {
  if left == right {
    return head
  }
  var leftNode, rightNode, leftPrev, rightNext *ListNode = nil, nil, nil, nil
  sentinel := &ListNode{Val: 0, Next: head}
  pos := 0
  var node, prev *ListNode = sentinel, nil

  for pos <= right {
    if pos == left {
      leftNode = node
      leftPrev = prev
    }
    if pos == right {
      rightNode = node
      rightNext = node.Next
    }
    prev = node
    node = node.Next
    pos++
  }

  prev, node = nil, leftNode
  for node != rightNext {
    next := node.Next
    node.Next = prev
    prev = node
    node = next
  }

  leftPrev.Next = rightNode
  leftNode.Next = rightNext

  return sentinel.Next
}
