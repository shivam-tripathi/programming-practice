/**
155. Min Stack
https://leetcode.com/problems/min-stack/
Easy

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:
		MinStack() initializes the stack object.
		void push(int val) pushes the element val onto the stack.
		void pop() removes the element on the top of the stack.
		int top() gets the top element of the stack.
		int getMin() retrieves the minimum element in the stack.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:
		-231 <= val <= 231 - 1
		Methods pop, top and getMin operations will always be called on non-empty stacks.
		At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */

class MinStack {
	stack: number[];
	// keep track of minimum and it's count in a separate stack to reduce space usage and return O(1)
	min: { val: number, count: number }[];

	constructor() {
		this.stack = [];
		this.min = [];
	}

	push(val: number): void {
		// simple push
		this.stack.push(val);
		if (this.min.length == 0) {
			this.min.push({ val, count: 1 });
		} else {
			// check if this number forms a new min, or is the existing min
			const min = this.min[this.min.length - 1];
			if (min.val > val) {
				this.min.push({ val, count: 1 });
			} else if (min.val === val) {
				min.count++;
			}
		}
	}

	pop(): void {
		const min = this.getMinTop();
		const top = this.top();
		if (min.val == top) {
			min.count--;
			if (min.count == 0) {
				this.min.pop();
			}
		}
		this.stack.pop();
	}

	top(): number {
		return this.stack[this.stack.length - 1];
	}

	private getMinTop() {
		return this.min[this.min.length - 1];
	}

	getMin(): number {
		return this.min[this.min.length - 1].val;
	}
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */