/**
 * For only DAGs
 * Use stack, push when all children are done
 */

import java.util.*;

class Graph {
    int V;
    List<List<Integer>> adj;
    Graph(int v) {
        this.V = v;
        this.adj = new ArrayList<>();
        for (int i = 0; i < this.V; i++) {
            List<Integer> list = new LinkedList<>();
            this.adj.add(list);
        }
    }

    void addEdge(int v, int w) {
        this.adj.get(v).add(w);
    }

    void DFSReverse(int src, boolean[] visited, Stack<Integer> stack) {
        visited[src] = true;
        ListIterator<Integer> iter = this.adj.get(src).listIterator();
        while(iter.hasNext()) {
            int nextNode = iter.next();
            if (!visited[nextNode]) {
                this.DFSReverse(nextNode, visited, stack);
            }
        }

        stack.push(src);
    }

    void topologicalSort() {
        boolean[] visited = new boolean[this.V];
        Stack<Integer> stack = new Stack<>();
        for (int i = this.V - 1; i >= 0; i--) {
            if (!visited[i]) {
                this.DFSReverse(i, visited, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.topologicalSort();
    }
}