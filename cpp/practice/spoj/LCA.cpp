#include <iostream>
#include <vector>

using namespace std;

struct Status
{
	bool matches_a;
	bool matches_b;
	int idx;
};

Status *_lca(vector<vector<int>> &tree, int cur_node, int a, int b, vector<Status> &status)
{
	status[cur_node].matches_a = a == cur_node;
	status[cur_node].matches_b = b == cur_node;
	status[cur_node].idx = -1;

	for (int child_idx = 0; child_idx < tree[cur_node].size(); child_idx++)
	{
		Status *cur_status = _lca(tree, tree[cur_node][child_idx], a, b, status);
		if (cur_status->matches_a && cur_status->matches_b)
		{
			return cur_status;
		}
		status[cur_node].matches_a = status[cur_node].matches_a || cur_status->matches_a;
		status[cur_node].matches_b = status[cur_node].matches_b || cur_status->matches_b;
		if (status[cur_node].matches_a && status[cur_node].matches_b)
		{
			status[cur_node].idx = cur_node;
			break;
		}
	}
	return &status[cur_node];
}

int lca(vector<vector<int>> &tree, int parent_node, int a, int b, vector<Status> &status)
{
	Status *ans = _lca(tree, parent_node, a, b, status);
	// cout << "DEBUG::" << ans->matches_a << " " << ans->matches_b << " " << ans->idx << endl;
	return ans->idx;
}

int main()
{
	int no_test;
	cin >> no_test;
	for (int test_case = 1; test_case <= no_test; test_case++)
	{
		int no_nodes;
		cin >> no_nodes;
		vector<int> parent_of_node(no_nodes + 1);
		vector<vector<int>> tree(no_nodes + 1);
		for (int cur_node = 1; cur_node <= no_nodes; cur_node++)
		{
			int no_children;
			cin >> no_children;
			for (int cur_child_idx = 0; cur_child_idx < no_children; cur_child_idx++)
			{
				int child;
				cin >> child;
				tree[cur_node].push_back(child);
				parent_of_node[child] = cur_node;
			}
		}
		int parent_node = -1;
		for (int cur_node = 1; cur_node <= no_nodes; cur_node++)
		{
			if (parent_of_node[cur_node] == 0)
			{
				parent_node = cur_node;
				break;
			}
		}
		int no_queries;
		cin >> no_queries;
		cout << "Case " << test_case << ":" << endl;
		for (int cur_query = 1; cur_query <= no_queries; cur_query++)
		{
			int a, b;
			vector<Status> status(no_nodes + 1);
			cin >> a >> b;
			cout << lca(tree, parent_node, a, b, status) << endl;
		}
	}
}