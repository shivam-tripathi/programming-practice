/**
32. Longest Valid Parentheses
https://leetcode.com/problems/longest-valid-parentheses/
Hard

Given a string containing just the characters '(' and ')', find the length of the longest valid
(well-formed) parentheses substring.

Example 1:
Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".

Example 2:
Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".

Example 3:
Input: s = ""
Output: 0

Constraints:
		0 <= s.length <= 3 * 104
		s[i] is '(', or ')'.
 */

class FixedStack<T> {
	stack: T[];
	size: number = 0;
	constructor(capacity: number) {
		this.stack = new Array(capacity);
	}
	isEmpty() {
		return this.size === 0;
	}
	push(e: T) {
		this.stack[this.size++] = e;
	}
	peek(): T {
		return this.stack[this.size - 1];
	}
	pop(): T {
		return this.stack[--this.size];
	}
}

function longestValidParentheses(s: string): number {
	const stack = new FixedStack<string | number>(s.length);
	let ans = 0;

	for (const ch of s) {
		if (stack.size === 0 || ch === '(') {
			stack.push(ch);
		} else {
			let count = 0;
			if (typeof stack.peek() == 'number') {
				count = stack.pop() as number;
			}
			if (!stack.isEmpty() && stack.peek() == '(') {
				stack.pop();
				if (!stack.isEmpty() && typeof stack.peek() == 'number') {
					stack.push(stack.pop() as number + count + 2);
				} else {
					stack.push(count + 2);
				}
				ans = Math.max(ans, stack.peek() as number);
			} else {
				if (count > 0) {
					stack.push(count);
				}
				stack.push(ch);
			}
		}
	}
	return ans;
};
