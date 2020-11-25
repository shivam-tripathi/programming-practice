/*
 * Detect back edge: https://en.wikipedia.org/wiki/Depth-first_search#Output_of_a_depth-first_search
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
			ArrayList<Integer> temp = new ArrayList<Integer>();
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

	public Boolean bfsCycle(Integer src, Integer visited[], Integer base) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);

		while(queue.size() > 0) {
			int node = queue.peek();
			if (visited[node] == base) {
				return true;
			}

			visited[node] = base;
			for (int i = 0; i < this.graph.get(node).size(); i++) {
				int childNode = this.graph.get(node).get(i);
				queue.add(childNode);
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
			if (visited[i] < 0 && bfsCycle(i, visited, i)) {
				return true;
			}
		}
		return false;
	}
}

class Main {
	public static void main(String args[]) throws IOException {
		Graph graph = new Graph(10, false, true);
	}
}

