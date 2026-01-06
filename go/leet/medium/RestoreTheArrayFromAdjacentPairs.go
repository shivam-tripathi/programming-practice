package main

import "fmt"

type Node struct {
	Children []*Node
	Val      int
}

func restoreFromTheArrayFromAdjacentPairs__dfs(parent int, node *Node, idx int, visited map[int]bool, values []int) int {
	if visited[node.Val] {
		return idx
	}
	values[idx] = node.Val
	idx += 1
	for _, next := range node.Children {
		if next.Val != parent {
			idx = restoreFromTheArrayFromAdjacentPairs__dfs(node.Val, next, idx, visited, values)
		}
	}
	return idx
}

func restoreArray(adjacentPairs [][]int) []int {
	cache := map[int]*Node{}
	ones := map[int]*Node{}

	for _, pairs := range adjacentPairs {
		a, b := pairs[0], pairs[1]
		if _, ok := cache[a]; !ok {
			cache[a] = &Node{Val: a}
		}
		if _, ok := cache[b]; !ok {
			cache[b] = &Node{Val: b}
		}
		cache[a].Children = append(cache[a].Children, cache[b])
		if len(cache[a].Children) > 1 {
			delete(ones, a)
		} else {
			ones[a] = cache[a]
		}
		cache[b].Children = append(cache[b].Children, cache[a])
		if len(cache[b].Children) > 1 {
			delete(ones, b)
		} else {
			ones[b] = cache[b]
		}
	}

	var root *Node

	for _, node := range ones {
		root = node
		break
	}

	// for value, node := range cache {
	// 	if len(node.Children) == 1 {
	// 		root = cache[value]
	// 	}
	// }

	fmt.Println(root.Val)
	values := make([]int, len(adjacentPairs)+1)
	restoreFromTheArrayFromAdjacentPairs__dfs(root.Val, root, 0, map[int]bool{}, values)

	return values
}

func main() {
	input := [][]int{{2, 1}, {3, 4}, {3, 2}}
	fmt.Println(restoreArray(input))
}
