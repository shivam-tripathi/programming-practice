/**
 * AdjList implemented using set
 * Pros:
 *      Queries like whether there is an edge from vertex u to vertex v can be done in O(log V).
 * Cons:
 *      Adding an edge takes O(log V), as opposed to O(1) in vector implementation.
 *      Graphs containing parallel edge(s) cannot be implemented through this method.
 */

#include <bits/stdc++.h>
using namespace std;

// Replace set with unordered_set for performance optimisation
struct Graph {
    int V;
    set<int, greater<int> >* adjList;
};

Graph* createGraph (int v) {
    Graph* graph = new Graph;
    graph->V = v;
    graph->adjList = new set<int, greater<int> >[graph->V];
    return graph;
}

void addEdge(Graph* graph, int v, int w) {
    graph->adjList[v].insert(w);
    graph->adjList[w].insert(v);
}

void printGraph(Graph* graph) {
    for (int i = 0; i < graph->V; i++) {
        cout << "Node " << i << ": ";
        // Can use auto for iterator type
        for (set<int>::iterator iter = graph->adjList[i].begin(); iter != graph->adjList[i].end(); iter++) {
            cout << *iter << " ";
        }
        cout << endl;
    }
}

void searchEdge(Graph *graph, int v, int w) {
    set<int>::iterator iter = graph->adjList[v].find(w);
    if (iter != graph->adjList[v].end()) {
        cout << "Found edge " << v << "-" << w << endl;
    } else {
        cout << "Not found edge " << v << "-" << w << endl;
    }
}

int main() {
    Graph* graph = createGraph(5);
    addEdge(graph, 0, 1);
    addEdge(graph, 0, 4);
    addEdge(graph, 1, 2);
    addEdge(graph, 1, 3);
    addEdge(graph, 1, 4);
    addEdge(graph, 2, 3);
    addEdge(graph, 3, 4);

    printGraph(graph);

    searchEdge(graph, 0, 1);
    searchEdge(graph, 0, 4);
    searchEdge(graph, 1, 2);
    searchEdge(graph, 1, 3);
    searchEdge(graph, 1, 4);
    searchEdge(graph, 2, 3);
    searchEdge(graph, 3, 4);
    searchEdge(graph, 3, 10);
}
