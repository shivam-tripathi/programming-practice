package main

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
type NestedInteger struct {
	isInt bool
	val   int
	list  []*NestedInteger
}

// Return true if this NestedInteger holds a single integer, rather than a nested list.
func (this *NestedInteger) IsInteger() bool {
	return this.isInt
}

// Return the single integer that this NestedInteger holds, if it holds a single integer
// The result is undefined if this NestedInteger holds a nested list
// So before calling this method, you should have a check
func (this *NestedInteger) GetInteger() int {
	return this.val
}

// Set this NestedInteger to hold a single integer.
func (this *NestedInteger) SetInteger(value int) {
	this.isInt = true
	this.val = value
}

// Set this NestedInteger to hold a nested list and adds a nested integer to it.
func (this *NestedInteger) Add(elem NestedInteger) {
	if this.list == nil {
		this.list = []*NestedInteger{}
	}
	this.list = append(this.list, &elem)
}

// Return the nested list that this NestedInteger holds, if it holds a nested list
// The list length is zero if this NestedInteger holds a single integer
// You can access NestedInteger's List element directly if you want to modify it
func (this NestedInteger) GetList() []*NestedInteger {
	return this.list
}

type StackElement struct {
	nested *NestedInteger
	pos    int
}

type NestedIterator struct {
	stack []*StackElement
}

func Constructor(nestedList []*NestedInteger) *NestedIterator {
	stack := make([]*StackElement, len(nestedList))
	for i := len(nestedList) - 1; i >= 0; i-- {
		elem := &StackElement{nestedList[i], 0}
		stack[len(nestedList)-i-1] = elem
	}
	return &NestedIterator{stack}
}

func (this *NestedIterator) HasNext() bool {
	for len(this.stack) != 0 {
		top := this.stack[len(this.stack)-1]
		cur := top.nested
		if cur.IsInteger() {
			return true
		}
		list := cur.GetList()
		pos := top.pos
		if pos >= len(list) {
			this.stack = this.stack[:len(this.stack)-1]
		} else {
			next := list[pos]
			top.pos++
			this.stack = append(this.stack, &StackElement{next, 0})
		}
	}

	return false
}

func (this *NestedIterator) Next() int {
	top := this.stack[len(this.stack)-1]
	this.stack = this.stack[:len(this.stack)-1]
	return top.nested.GetInteger()
}
