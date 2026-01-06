package main

/*
399. Evaluate Division
https://leetcode.com/problems/evaluate-division/
Medium

You are given an array of variable pairs equations and an array of real numbers values, where
equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a
string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must
find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in
division by zero and that there is no contradiction.

Example 1:
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]

Example 2:
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0],
queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

Constraints:
	1 <= equations.length <= 20
	equations[i].length == 2
	1 <= Ai.length, Bi.length <= 5
	values.length == equations.length
	0.0 < values[i] <= 20.0
	1 <= queries.length <= 20
	queries[i].length == 2
	1 <= Cj.length, Dj.length <= 5
	Ai, Bi, Cj, Dj consist of lower case English letters and digits.
*/

func ins(graph map[int]map[int]float64, a, b int, value float64) {
	if graph[a] == nil {
		graph[a] = map[int]float64{}
	}
	graph[a][b] = value
}

func division_dfs(graph map[int]map[int]float64, node, target int, visited []bool) float64 {
	if node == -1 || target == -1 {
		return -1
	}

	if node == target {
		return 1
	}

	visited[node] = true

	for key, value := range graph[node] {
		if !visited[key] {
			res := division_dfs(graph, key, target, visited)
			if res != -1 {
				ans := res * value
				ins(graph, node, target, ans)
				ins(graph, target, node, 1/ans)
				return ans
			}
		}
	}

	return -1
}

func insOrGet(mapping map[string]int, node string) int {
	if id, ok := mapping[node]; ok {
		return id
	}
	size := len(mapping)
	mapping[node] = size
	return mapping[node]
}

func get(mapping map[string]int, node string) int {
	if id, ok := mapping[node]; ok {
		return id
	}
	return -1
}

func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	mapping := map[string]int{}
	graph := map[int]map[int]float64{}

	for idx, edge := range equations {
		a, b := insOrGet(mapping, edge[0]), insOrGet(mapping, edge[1])
		ins(graph, a, b, values[idx])
		ins(graph, b, a, 1/values[idx])
	}

	ans := []float64{}
	size := len(mapping)

	for _, query := range queries {
		a, b := query[0], query[1]
		res := division_dfs(graph, get(mapping, a), get(mapping, b), make([]bool, size))
		ans = append(ans, res)
	}

	return ans
}
