/*
	Disjoint set data structure: Union Find (Union by rank and Path compression)
	Detect cycle in a graph
	Assumption: No self loops
*/
import java.util.*;
import java.io.*;

class Graph {
    int verticesCount;
    Subset[] subsets;
    List<Edge> edges;

    class Edge {
        int a, b;
        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    class Subset {
        int parent, rank;
        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
        Subset() {}
    }

    Graph(int v) {
        this.verticesCount = v;
        this.edges = new ArrayList<>();
        this.subsets = new Subset[this.verticesCount];
        for (int i = 0; i < this.verticesCount; i++) {
            this.subsets[i] = new Subset(i, 0);
        }
    }

    void addEdge(int a, int b) {
        Edge edge = new Edge(a, b);
        this.edges.add(edge);
        this.union(a, b);
    }

    int find(int node) {
        int temp = node;
        while(temp != this.subsets[temp].parent) {
            temp = this.subsets[temp].parent;
        }

        this.subsets[node].parent = temp;
        return temp;
    }

    void union(int a, int b) {
        int aRoot = this.find(a);
        int bRoot = this.find(b);
        if (this.subsets[aSet].rank < this.subsets[bSet].rank) {
            this.subsets[bSet].parent = aSet;
        } else {
            this.subsets[aSet].parent = bSet;
        }
    }

    boolean detectCycle() {
        for(int i = 0; i < this.edges.size(); i++) {
            Edge edge = this.edges.get(i);
            if (this.find(edge.src) == this.find(edge.dest)) {
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

        System.out.println("Has Cycle: " + graph.detectCycle());
    }
}