package main

var m int
var n int
var visited []int
var mat [][]int

func dfs(x int, y int) int {
	if x >= m || y >= n || mat[x][y] == 1 {
		return 0
	}

	if x == m-1 && y == n-1 {
		return 1
	}

	if visited[x*n+y] == -1 {
		visited[x*n+y] = dfs(x+1, y) + dfs(x, y+1)
	}

	return visited[x*n+y]
}

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	m, n = len(obstacleGrid), len(obstacleGrid[0])
	mat = obstacleGrid
	visited = make([]int, m*n)
	for i := 0; i < m*n; i++ {
		visited[i] = -1
	}
	return dfs(0, 0)
}
