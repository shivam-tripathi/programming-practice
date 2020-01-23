import java.util.*;
import java.io.*;
import java.lang.*;

class Pair {
    int maxAns;
    int maxDepth;
    Pair(int a, int b) {
	maxAns = a;
	maxDepth = b;
    }
}

class Graph {
    ArrayList<ArrayList<Integer>> adj;
    Graph(ArrayList<ArrayList<Integer>> adj) {
	this.adj = adj;
    }

    Pair dfs(int index, boolean[] visited) {
	visited[index] = true;

	int maxAns = 0, maxDepth = 0, secondMaxDepth = 0;
	for (Integer node: adj.get(index)) {
	    if (visited[node] == true) {
		continue;
	    }
	    Pair sub = dfs(node, visited);
	    maxAns = Math.max(sub.maxAns, maxAns);
	    if (sub.maxDepth > maxDepth) {
		secondMaxDepth = maxDepth;
		maxDepth = sub.maxDepth;
	    } else if (sub.maxDepth > secondMaxDepth) {
		secondMaxDepth = sub.maxDepth;
	    }
	}

	int ans = Math.max(maxAns, maxDepth + secondMaxDepth + 1);
	return new Pair(ans, maxDepth + 1);
    }

    int solve(int beginIndex) {
	boolean[] visited = new boolean[adj.size()];
	Pair deepest = dfs(beginIndex, visited);
	return deepest.maxAns;
    }

}

class Main {
    public static void main(String[] args) throws IOException {
	int t;
	BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
	t = Integer.parseInt(obj.readLine());
	while (t-- > 0) {
	    String[] read = obj.readLine().split(" ");
	    int c = Integer.parseInt(read[0]);
	    int r = Integer.parseInt(read[1]);
	    ArrayList<String> arr = new ArrayList<>();
	    for (int i = 0; i < r; i++) {
		arr.add(obj.readLine());
	    }

	    int startNode = -1;

	    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	    for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
		    int node = c * i + j;
		    adj.add(new ArrayList<Integer>());
		    if (arr.get(i).charAt(j) == '#') {
			continue;
		    }

		    if (i - 1 >= 0 && arr.get(i - 1).charAt(j) == '.') {
			adj.get(node).add(c * (i - 1) + j);
		    }

		    if (i + 1 < r && arr.get(i + 1).charAt(j) == '.') {
			adj.get(node).add(c * (i + 1) + j);
		    }

		    if (j - 1 >= 0 && arr.get(i).charAt(j - 1) == '.') {
			adj.get(node).add(c * i + (j - 1));
		    }

		    if (j + 1 < c && arr.get(i).charAt(j + 1) == '.') {
			adj.get(node).add(c * i + (j + 1));
		    }

		    if (adj.get(node).size() > 0) {
			startNode = node;
		    }
		}
	    }

	    int ans = 0;
	    if (startNode != -1) {
		Graph graph = new Graph(adj);
		ans = graph.solve(startNode);
	    }
	    System.out.printf("Maximum rope length is %d.%n", Math.max(0, ans - 1));
	}
    }
}

