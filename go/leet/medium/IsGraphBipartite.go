package main

const UNCOLORED = 0
const RED = 1
const BLUE = 2

type ColorBipartite struct {
	graph  [][]int
	colors []int
}

func NewColorBipartite(graph [][]int) *ColorBipartite {
	return &ColorBipartite{
		graph:  graph,
		colors: make([]int, len(graph)),
	}
}

func (this *ColorBipartite) isColored(node int) bool {
	return this.colors[node] != 0
}

func (this *ColorBipartite) color(node int, expected int) bool {
	if this.colors[node] == UNCOLORED {
		this.colors[node] = expected
		compl := RED
		if this.colors[node] == RED {
			compl = BLUE
		}
		for _, sub := range this.graph[node] {
			if !this.color(sub, compl) {
				return false
			}
		}
		return true
	}
	return this.colors[node] == expected
}

func isBipartite(graph [][]int) bool {
	colorBipartite := NewColorBipartite(graph)
	for node := 0; node < len(graph); node++ {
		if !colorBipartite.isColored(node) {
			if !colorBipartite.color(node, RED) {
				return false
			}
		}
	}
	return true
}
