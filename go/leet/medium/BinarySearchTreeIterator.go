package main

import (
	"fmt"
	"strings"
)

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type StackNode struct {
	val  interface{}
	next *StackNode
}

type Stack struct {
	head *StackNode
	size int
}

func (stack *Stack) Push(val interface{}) {
	if stack.head == nil {
		stack.head = &StackNode{val, nil}
	} else {
		stack.head = &StackNode{val, stack.head}
	}
	stack.size += 1
}

func (stack *Stack) Pop() interface{} {
	if stack.head == nil {
		return nil
	} else {
		node := stack.head
		stack.head = node.next
		stack.size -= 1
		return node.val
	}
}

func (stack *Stack) Peek() interface{} {
	if stack.head == nil {
		return nil
	}
	return stack.head.val
}

func (stack *Stack) Size() int {
	return stack.size
}

func (stack *Stack) String(toStr func(interface{}) string) string {
	sb := strings.Builder{}
	node := stack.head
	for node != nil {
		sb.WriteString(fmt.Sprintf("(%s)", toStr(node.val)))
		node = node.next
	}
	return sb.String()
}

type BSTIterator struct {
	node  *TreeNode
	stack *Stack
}

func ConstructorBSTITIterator(root *TreeNode) BSTIterator {
	return BSTIterator{root, &Stack{nil, 0}}
}

func (this *BSTIterator) Next() int {
	if this.stack.Size() != 0 || this.node != nil {
		for this.node != nil {
			this.stack.Push(this.node)
			this.node = this.node.Left
		}
	}
	top := this.stack.Pop().(*TreeNode)
	this.node = top.Right
	return top.Val
}

func (this *BSTIterator) HasNext() bool {
	return this.node != nil || this.stack.Size() != 0
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * obj := Constructor(root);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */
