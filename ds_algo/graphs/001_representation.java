import java.util.*;

class GraphAdj {
	public Boolean startsWithZero;
	public Boolean bidirectional;
	Integer verticesCount;
	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	public GraphAdj(int verticesCount, Boolean startsWithZero, Boolean bidirectional) {
		this.verticesCount = verticesCount;
		this.bidirectional = bidirectional;
		this.startsWithZero = startsWithZero;
		this.initGraph();
	}

	public void initGraph() {
		for(int i=0; i<this.verticesCount; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			this.graph.add(temp);
		}
	}
	public void addEdge(int a, int b) throws Exception {
		if (a > this.verticesCount || b > this.verticesCount) {
			throw new Exception("Vertices greater than graph size");
		}
		if (!this.startsWithZero) {
			if (a <= 0 || b <= 0) {
				throw new Exception("Vertices cannot be less than equal to zero");
			}
			a--; b--;
		}
		this.graph.get(a).add(b);
		if (this.bidirectional) {
			this.graph.get(b).add(a);
		}
	}

	public void printGraph() {
		for(int i=0; i<this.verticesCount; i++) {
			int vertex = this.startsWithZero ? i : i+1;
			System.out.println("Adjacency list of vertex " + vertex);
			System.out.print("head");
			for(int j=0; j<this.graph.get(i).size(); j++) {
				int conn = this.startsWithZero ? this.graph.get(i).get(j) : this.graph.get(i).get(j) + 1;
				System.out.print("-> " + conn);
			}
			System.out.println(";\n");
		}
	}
}

class Main {
	public static void main(String[] args) {
		try {
			GraphAdj graph = new GraphAdj(5, true, true); 
			graph.addEdge(0, 1); 
		    graph.addEdge(0, 4); 
		    graph.addEdge(1, 2); 
		    graph.addEdge(1, 3); 
		    graph.addEdge(1, 4); 
		    graph.addEdge(2, 3); 
		    graph.addEdge(3, 4); 
		    graph.printGraph(); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}