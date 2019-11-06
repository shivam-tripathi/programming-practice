/*
 * Detect back edge: https://en.wikipedia.org/wiki/Depth-first_search#Output_of_a_depth-first_search
 * Complexity O(V+E)
 */

import java.io.*;
import java.util.*;

class Graph {
	public Boolean startsWithZero;
	public Boolean bidirectional;
	Integer verticesCount;
	List<List<Integer>> graph = new ArrayList<>();

	public Graph (Integer verticesCount, Boolean bidirectional, Boolean startsWithZero) {
		this.verticesCount = verticesCount;
		this.bidirectional = bidirectional;
		this.startsWithZero = startsWithZero;
		for(int i=0; i<this.verticesCount; i++) {
			List<Integer> temp = new ArrayList<Integer>();
			this.graph.add(temp);
		}
	}

	private int getIndexFromVertex(int _a) throws Exception {
		if (_a > this.verticesCount || _a < 0 || (!this.startsWithZero && _a == 0)) {
			throw new Exception("Vertex " + _a + " invalid");
		}
		return this.startsWithZero ? _a : _a - 1;
	}

	private int getVertexFromIndex(int _i) {
		return this.startsWithZero ? _i : _i + 1;
	}

	public void addEdge(int _a, int _b) throws Exception {
		int a = this.getIndexFromVertex(_a);
		int b = this.getIndexFromVertex(_b);
		this.graph.get(a).add(b);
		if (this.bidirectional) {
			this.graph.get(b).add(a);
		}
	}

	public Boolean dfsCycle(Integer src, Integer visited[], Integer base) {
		if (visited[src] == base) {
			return true;
		}

		visited[src] = base;
		for(int i = 0; i < this.graph.get(src).size(); i++) {
			Integer child = this.graph.get(src).get(i);
			if (dfsCycle(child, visited, base)) {
				return true;
			}
		}

		return false;
	}

	public Boolean hasCycle() {
		Integer visited[] = new Integer[this.verticesCount];
		for (int i = 0; i < this.verticesCount; i++) {
			visited[i] = -1;
		}

		for (int i = 0; i < this.verticesCount; i++) {
			if (visited[i] < 0 && dfsCycle(i, visited, i)) {
				return true;
			}
		}
		return false;
	}


	// Utility function for isCyclic base function
	private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack) {
		// Is this node part of present recursion stack?
		if (recStack[i]) {
			return true;
		}

		// Is this node already visited?
		if (visited[i]) {
			return false;
		}

		visited[i] = true;
		recStack[i] = true;

		// Recur over children
		List<Integer> children = this.graph.get(i);
		for (Integer c: children) {
			if (isCyclicUtil(c, visited, recStack)) {
				return true;
			}
		}

		// Pull the node out of present recursion
		recStack[i] = false;
		return false;
	}

	private boolean isCyclic() {
		boolean[] visited = new boolean[this.verticesCount];
		boolean[] recStack = new boolean[this.verticesCount];

		for (int i = 0; i < this.verticesCount; i++) {
			if (isCyclicUtil(i, visited, recStack)) {
				return true;
			}
		}

		return false;
	}
}

class Main {
	public static void main(String args[]) throws Exception {
		Graph graph = new Graph(10, false, true);
		graph.addEdge(0, 1);
	        graph.addEdge(0, 2);
        	graph.addEdge(1, 2);
	        graph.addEdge(2, 0);
        	graph.addEdge(2, 3);
	        graph.addEdge(3, 3);

		if (graph.hasCycle()) {
			System.out.println("Graph has cycle");
		} else {
			System.out.println("Graph doesn't have a cycle");
		}
	}
}

