package main

/*
In this problem we establish that the courses from a DAG (i.e. there is no cycle). Topological
sorting is an easy way to establish if this is true. Here we are going to implement Kahn's
algorithm.

Kahn's algorithm:
If the graph is a DAG, then there must be atleast one node which has zero indegree and one node
with zero outdegree.
We repeatedly check for nodes with indegree == 0, remove all connections from the node (and hence
reduce indegree of connected nodes) and if the new indegree value == 0, we add them to nodes list
with indegree == 0. We repeat this process till there are no more nodes with indegree == 0 left.

At the end if we have processed all the nodes, this is a DAG with no cycles else this graph has
cycle(s).
*/

func canFinish(numCourses int, prerequisites [][]int) bool {
	graph := make([][]int, numCourses)
	indegrees := make([]int, numCourses)

	for i := 0; i < numCourses; i++ {
		graph[i] = []int{}
	}

	for _, prerequisite := range prerequisites {
		a, b := prerequisite[0], prerequisite[1]
		graph[b] = append(graph[b], a)
		indegrees[a]++
	}

	roots := []int{}
	for i, indegree := range indegrees {
		if indegree == 0 {
			roots = append(roots, i)
		}
	}

	pos := 0
	for pos < len(roots) {
		root := roots[pos]
		for _, node := range graph[root] {
			indegrees[node]--
			if indegrees[node] == 0 {
				roots = append(roots, node)
			}
		}
		pos++
	}

	return pos == numCourses
}
