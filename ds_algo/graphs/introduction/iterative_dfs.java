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

    class StackTrace {
        int node;
        ListIterator<Integer> pointer;
        StackTrace(int node) {
            this.node = node;
            this.pointer = adj.get(node).listIterator();
        }
    }

    void DFSIter() {
        boolean visited[] = new boolean[this.V];
        for (int i = 0; i < this.V; i++) {
            if (!visited[i]) {
                Stack<StackTrace> stack = new Stack<StackTrace>();
                stack.add(new StackTrace(i));
                visited[i] = true;
                System.out.println(i);

                while (!stack.isEmpty()) {
                    StackTrace top = stack.peek();
                    if (!top.pointer.hasNext()) {
                        stack.pop();
                        continue;
                    }

                    int nextNode = top.pointer.next();
                    if (!visited[nextNode]) {
                        stack.add(new StackTrace(nextNode));
                        visited[nextNode] = true;
                        System.out.println(nextNode);
                    }
                }
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);

        g.DFSIter();
        g.DFSIter2();
    }
}