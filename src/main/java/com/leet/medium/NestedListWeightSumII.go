package main

/*
Nested List Weight Sum II
https://leetcode.com/problems/nested-list-weight-sum-ii/
Medium 364.

Given a nested list of integers, return the sum of all integers in the list weighted by
their depth. Each element is either an integer, or a list — whose elements may also be
integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now
the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and
the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1’s at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at
	depth 1; 1*3 + 4*2 + 6*1 = 17)

*/

import (
	"fmt"
	"reflect"
)

func maxInts(args ...int) int {
	ans := args[0]
	for _, num := range args {
		if ans < num {
			ans = num
		}
	}
	return ans
}

func nestedListWeightSum2Solution(elems []interface{}, pos int) (int, int) {
	if pos >= len(elems) {
		return 0, 0
	}
	switch reflect.TypeOf(elems[pos]).Kind() {
	case reflect.Int:
		ans, depth := nestedListWeightSum2Solution(elems, pos+1)
		return ans + maxInts(depth, 1)*elems[pos].(int), maxInts(depth, 1)
	case reflect.Slice:
		ansElem, depthElem := nestedListWeightSum2Solution(elems[pos].([]interface{}), 0)
		ansTail, depthTail := nestedListWeightSum2Solution(elems, pos+1)
		return ansElem + ansTail, maxInts(depthElem, depthTail)
	}
	return 0, 0
}

func nestedListWeightSum2Runner() {
	// func main() {
	elems := []interface{}{[]interface{}{1, 1}, 2, []interface{}{1, 1}}
	fmt.Println(nestedListWeightSum2Solution(elems, 0))
	elems = []interface{}{1, []interface{}{4, []interface{}{6}}}
	fmt.Println(nestedListWeightSum2Solution(elems, 0))
}
