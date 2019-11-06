/*
	Disjoint set data structure: Union Find
	Detect cycle in a graph
	Assumption: No self loops
*/


import java.util.*;
import java.io.*;

class Graph {
	int V, E;
	List<Edge> edges;
	int[] parent;

	class Edge {
		int a, b;
		Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	};

	Graph(int v) {
		this.V = v;
		this.edges = new ArrayList<>();
		this.parent = new int[V];
		for (int i = 0; i < V; i++) {
			parent[i] = i;
		}
	}

	public void addEdge(int a, int b) {
		Edge edge = new Edge(a, b);
		edges.add(edge);
		this.union(a, b);
	}

	int find(int node) {
		int temp = node;
		while(temp != parent[temp]) {
			temp = parent[temp];
		}
		return temp;
	}

	void union(int setA, int setB) {
		int parentA = this.find(setA);
		int parentB = this.find(setB);

		this.parent[parentA] = parentB;
	}

	boolean detectCycle() {
		for (int i = 0; i < this.edges.size(); i++) {
			Edge edge = this.edges.get(i);
			if (this.parent[edge.a] == this.parent[edge.b]) {
				return true;
			}
		}
		return false;
	}
}

class Main {
	public static void main(String[] args) {
		Graph graph = new Graph(3);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(0, 2);

		System.out.println("Has cycle: " + graph.detectCycle());
	}
}
