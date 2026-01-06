package com.dsAlgo.graphs.cycles.directed;/*
 * DFS with colors (from CLRS): Find back edge
 * WHITE : Vertex is not processed yet. Initially all vertices are WHITE.
 * GRAY : Vertex is being processed (DFS for this vertex has started, but not finished which means
 * 		  that all descendants (ind DFS tree) of this vertex are not processed yet (or this vertex
 * 		  is in function call stack)
 * BLACK : Vertex and all its descendants are processed.
 * If an edge exist from current vertex to GRAY edge, then it is a BACK edge and hence there is a cycle.
 * */

import java.util.*;

public class Colors {
	static class Graph {
		private List<List<Integer>> adj;
		private int WHITE = 0;
		private int GRAY = 1;
		private int BLACK = 2;

		Graph(int verticesCount, int bidirectional, int startWithZero) {
			adj = new ArrayList<>();

		}
	}
}


