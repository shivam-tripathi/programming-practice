package main

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type LinkedListNode struct {
	next *LinkedListNode
	prev *LinkedListNode
	val  interface{}
}

type Stack struct {
	top  *LinkedListNode
	size int
}

func (stack *Stack) Push(elem interface{}) {
	if stack.size == 0 {
		stack.top = &LinkedListNode{nil, nil, elem}
	} else {
		stack.top.next = &LinkedListNode{nil, stack.top, elem}
		stack.top = stack.top.next
	}
	stack.size++
}

func (stack *Stack) Pop() interface{} {
	top := stack.top
	stack.size--
	stack.top = stack.top.prev
	return top.val
}

func (stack *Stack) Size() int {
	return stack.size
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
