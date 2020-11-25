import java.util.Iterator;

/**
 * AdjList implemented using set
 * Pros:
 *      Queries like whether there is an edge from vertex u to vertex v can be done in O(log V), compared to O(V)
 * Cons:
 *      Adding an edge takes O(log V), as opposed to O(1) in vector implementation.
 *      Graphs containing parallel edge(s) cannot be implemented through this method.
 *
 *  If you need a fast set, you should use HashSet;
 *  If you need a sorted set, then TreeSet should be used;
 *  If you need a set that can be store the insertion order, LinkedHashSet should be used.
 */

import java.util.*;

class Graph {
    List<Set<Integer>> adjList;
    int V;
    Graph(int v) {
        this.V = v;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < this.V; i++) {
            Set<Integer> set = new HashSet<>();
            this.adjList.add(set);
        }
    }

    void addEdge(int v, int w) {
        this.adjList.get(v).add(w);
        this.adjList.get(w).add(v);
    }

    void printGraph() {
        for (int i = 0; i < this.V; i++) {
            System.out.print(i);
            Iterator<Integer> iter = this.adjList.get(i).iterator();
            while(iter.hasNext()) {
                System.out.print(" -> " + iter.next());
            }
            System.out.println();
        }
    }

    void searchEdge(int v, int w) {
        if (this.adjList.get(v).contains(w)) {
            System.out.println("Found edge " + v + " " + w);
        } else {
            System.out.println("Not found edge " + v + " " + w);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printGraph();

        graph.searchEdge(0, 1);
        graph.searchEdge(0, 4);
        graph.searchEdge(1, 2);
        graph.searchEdge(1, 3);
        graph.searchEdge(1, 4);
        graph.searchEdge(2, 3);
        graph.searchEdge(3, 4);
        graph.searchEdge(4, 10);
    }
}