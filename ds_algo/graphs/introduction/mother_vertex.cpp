/**
 * Mother vertex: DFS O(E + V)
 * Case 1:- Undirected Connected Graph : In this case, all the vertices are mother vertices as we can reach to all the other nodes in the graph.
 * Case 2:- Undirected/Directed Disconnected Graph : In this case, there is no mother vertices as we cannot reach to all the other nodes in the graph.
 * Case 3:- Directed Connected Graph : In this case, we have to find a vertex v in the graph such that we can reach to all the other nodes in the graph through a directed path.
 * Last node to finish dfs is the parent vertex. Another method could be using disjoint data structures.
 */

#include <bits/stdc++.h>
using namespace std;

class Graph {
    int V;
    list<int>* adj;
    public:
    Graph(int v) {
        this->V = v;
        adj = new list<int>[this->V];
    }

    void addEdge(int v, int w) {
        this->adj[v].push_back(w);
    }

    void DFS(int src, vector<bool> &visited) {
        visited[src] = true;
        for (list<int>::iterator iter = this->adj[src].begin(); iter != this->adj[src].end(); iter++) {
            if (!visited[*iter]) {
                DFS(*iter, visited);
            }
        }
    }

    int findMotherVertex() {
        vector<bool> visited(this->V, false);
        // Last vertex to be visited via DFS is mother vertex
        int motherVertex;
        for (int i = 0; i < this->V; i++) {
            if (!visited[i]) {
                this->DFS(i, visited);
                motherVertex = i;
            }
        }

        // Verify if the graph is not diconnected
        fill(visited.begin(), visited.end(), false);
        this->DFS(motherVertex, visited);
        for (int i = 0; i < this->V; i++) {
            if (!visited[i]) {
                return -1;
            }
        }

        return motherVertex;
    }
};

int main(int argc, char const *argv[]) {
    Graph* graph = new Graph(7);
    graph->addEdge(0, 1);
    graph->addEdge(0, 2);
    graph->addEdge(1, 3);
    graph->addEdge(4, 1);
    graph->addEdge(6, 4);
    graph->addEdge(5, 6);
    graph->addEdge(5, 2);
    graph->addEdge(6, 0);

    cout << graph->findMotherVertex() << endl;

    return 0;
}
