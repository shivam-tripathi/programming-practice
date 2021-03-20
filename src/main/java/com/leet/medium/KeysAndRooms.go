package main

import "fmt"

func canVisitAllRoomsBFS(rooms [][]int) bool {
	if len(rooms) == 0 {
		return true
	}

	visited := make([]bool, len(rooms))
	queue := make([]int, 0) // empty queue
	done := 1
	queue = append(queue, rooms[0]...)
	visited[0] = true
	for len(queue) > 0 {
		next := queue[0]
		queue = queue[1:]
		if visited[next] {
			continue
		}
		visited[next] = true
		done++
		queue = append(queue, rooms[next]...)
	}
	return done == len(rooms)
}

func dfs(room int, rooms [][]int, visited []bool, count int) int {
	for _, next := range rooms[room] {
		if !visited[next] {
			visited[next] = true
			count++
			count = dfs(next, rooms, visited, count)
		}
	}
	return count
}

func canVisitAllRoomsDFS(rooms [][]int) bool {
	if len(rooms) == 0 {
		return true
	}
	visited := make([]bool, len(rooms))
	visited[0] = true
	return dfs(0, rooms, make([]bool, len(rooms)), 1) == len(rooms)
}

func main() {
	rooms := [][]int{{1}, {2}, {3}, {}}
	fmt.Println(canVisitAllRoomsBFS(rooms))
	fmt.Println(canVisitAllRoomsDFS(rooms))
}
