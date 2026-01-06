#include <bits/stdc++.h>
using namespace std;

/*
// Definition for a Node.
*/
class Node
{
public:
	int val;
	vector<Node *> neighbors;
	Node()
	{
		val = 0;
		neighbors = vector<Node *>();
	}
	Node(int _val)
	{
		val = _val;
		neighbors = vector<Node *>();
	}
	Node(int _val, vector<Node *> _neighbors)
	{
		val = _val;
		neighbors = _neighbors;
	}
};

class Solution
{
	Node *cloneGraph(Node *node, map<Node *, Node *> &ref)
	{
		if (node == nullptr)
		{
			return node;
		}
		if (ref[node])
		{
			return ref[node];
		}
		auto cpy = new Node(node->val);
		ref[node] = cpy;
		for (auto n : node->neighbors)
		{
			cpy->neighbors.push_back(cloneGraph(n, ref));
		}
		return cpy;
	}

public:
	Node *cloneGraph(Node *node)
	{
		auto ref = map<Node *, Node *>();
		return cloneGraph(node, ref);
	}
};
