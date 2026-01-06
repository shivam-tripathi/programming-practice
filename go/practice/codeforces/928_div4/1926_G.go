package main

import "fmt"

/*
Vladislav has a son who really wanted to go to MIT. The college dormitory at MIT (Moldova Institute of Technology) can be represented as a tree with 𝑛 vertices, each vertex being a room with exactly one student. A tree is a connected undirected graph with 𝑛 vertices and 𝑛−1

edges.

Tonight, there are three types of students:

    students who want to party and play music (marked with 𝙿

),
students who wish to sleep and enjoy silence (marked with 𝚂
), and
students who don't care (marked with 𝙲

    ).

Initially, all the edges are thin walls which allow music to pass through, so when a partying student puts music on, it will be heard in every room. However, we can place some thick walls on any edges — thick walls don't allow music to pass through them.

The university wants to install some thick walls so that every partying student can play music, and no sleepy student can hear it.

Because the university lost a lot of money in a naming rights lawsuit, they ask you to find the minimum number of thick walls they will need to use.
Input

The first line contains a single integer 𝑡
(1≤𝑡≤1000

) — the number of test cases.

The first line of each test case contains an integer 𝑛
(2≤𝑛≤105

) — the number of vertices in the tree.

The second line of each test case contains 𝑛−1
integers 𝑎2,…,𝑎𝑛 (1≤𝑎𝑖<𝑖) — it means there is an edge between 𝑖 and 𝑎𝑖

in the tree.

The third line of each test case contains a string 𝑠
of length 𝑛 consisting of characters 𝙿, 𝚂, and 𝙲, denoting that student 𝑖 is of type 𝑠𝑖

.
The sum of 𝑛
over all test cases does not exceed 105

.
Output
For each test case, output a single integer — the minimum number of thick walls needed.
*/

var counter int

func traverse(edges [][]int, types string, node int, visited []bool) bool {
	if visited[node] {
		return false
	}

	visited[node] = true

	if len(edges[node]) == 0 {
		return false
	}

	for _, neighbour := range edges[node] {
		if traverse(edges, types, neighbour, visited) {
			counter++
		}
	}

	return types[node] == 'S'
}

func main() {
	var t int
	fmt.Scanln(&t)

	for i := 0; i < t; i++ {
		var n int
		fmt.Scanln(&n)

		adj := make([][]int, n)
		for j := 0; j < n-1; j++ {
			source := j
			var dest int
			fmt.Scan(&dest)
			adj[source] = append(adj[source], dest)
			adj[dest] = append(adj[dest], source)
		}

		var s string
		fmt.Scanln(&s)

		counter = 0
		visited := make([]bool, n)

		traverse(adj, s, 0, visited)

		fmt.Println(counter)
	}
}
