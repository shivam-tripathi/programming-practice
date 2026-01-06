#include <iostream>
#include <map>
#include <vector>
#include <queue>
using namespace std;

/**
TDBFS - Searching the Graph
https://www.spoj.com/problems/TDBFS/
For a given list of adjacent vertices of a graph and a chosen vertex v write down in the Depth First Search (DFS) or Breadth First Search (BFS) order all the vertices from the connected component of the graph containing v. Assume that the number of vertices of the graph is at most 1000.
Input
t [the number of graphs <= 100]
Graph:
n [1 <= n <= 1000 the number of graph vertices]
i m a b c ... [the list of m adjacent vertices to vertex i]
Any query is as follows: [not more than n queries]
v i
where 1 <= v <= n is the beginning vertex and i = 0 for DFS order and i = 1 for BFS order.
0 0 [at the end of the serie]
The list for isolated vertex a is a 0.
Output
graph i [test case, word graph is necessary]
a b c ... [the DFS or BFS order of all vertices]
 */

void print_vector(vector<int> &vec)
{
	for (int i = 0; i < vec.size(); i++)
	{
		if (i == vec.size() - 1)
		{
			cout << vec[i];
		}
		else
		{
			cout << vec[i] << " ";
		}
	}
}

void _dfs(map<int, vector<int>> &adj, int src, vector<bool> &visited, vector<int> &traversal)
{
	if (visited[src - 1])
	{
		return;
	}
	traversal.push_back(src);
	visited[src - 1] = true;
	for (int i = 0; i < adj[src].size(); i++)
	{
		_dfs(adj, adj[src][i], visited, traversal);
	}
}

void dfs(int no_vertices, map<int, vector<int>> &adj, int src, vector<int> &traversal)
{
	vector<bool> visited(no_vertices + 1);
	_dfs(adj, src, visited, traversal);
}

void bfs(int no_vertices, map<int, vector<int>> &adj, int src, vector<int> &traversal)
{
	queue<int> q;
	q.push(src);
	vector<bool> visited(no_vertices + 1);
	visited[src - 1] = true;

	while (!q.empty())
	{
		int node = q.front();
		q.pop();
		traversal.push_back(node);
		for (int i = 0; i < adj[node].size(); i++)
		{
			int next_node = adj[node][i];
			if (!visited[next_node - 1])
			{
				visited[next_node - 1] = true;
				q.push(next_node);
			}
		}
	}
}

int main()
{
	int no_test;
	cin >> no_test;
	for (int test_case = 1; test_case <= no_test; test_case++)
	{
		int no_vertices;
		cin >> no_vertices;
		map<int, vector<int>> adj;
		for (int i = 0; i < no_vertices; i++)
		{
			int node, no_children;
			cin >> node >> no_children;
			for (int j = 0; j < no_children; j++)
			{
				int child;
				cin >> child;
				adj[node].push_back(child);
			}
		}

		cout << "graph " << test_case << endl;
		while (true)
		{
			int source, run_bfs;
			cin >> source >> run_bfs;
			if (source == 0 && run_bfs == 0)
			{
				break;
			}
			vector<int> traversal;
			if (run_bfs)
			{
				bfs(no_vertices, adj, source, traversal);
			}
			else
			{
				dfs(no_vertices, adj, source, traversal);
			}
			print_vector(traversal);
			if (test_case != no_test)
			{
				cout << endl;
			}
		}
	}
	return 0;
}
