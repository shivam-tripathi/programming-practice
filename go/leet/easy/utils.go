package main

import "strings"

type ListNode struct {
	Val  int
	Next *ListNode
}

func NewListNode(arr []int) *ListNode {
	if len(arr) == 0 {
		return nil
	}
	head := &ListNode{arr[0], nil}
	node := head
	for i := 1; i < len(arr); i++ {
		node.Next = &ListNode{arr[i], nil}
		node = node.Next
	}
	return head
}

func (this *ListNode) String() string {
	node := this
	sb := strings.Builder{}
	for node != nil {
		sb.WriteString(string(node.Val))
	}
	return sb.String()
}
