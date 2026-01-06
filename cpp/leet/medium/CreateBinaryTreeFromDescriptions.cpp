#include <unordered_map>
#include <vector>
#include <iostream>
using namespace std;

class TreeNode
{
public:
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
};

class Solution
{
public:
	TreeNode *createBinaryTree(vector<vector<int>> &descriptions)
	{
		unordered_map<int, TreeNode *> nodes;
		unordered_map<int, TreeNode *> parents;

		for (auto desc : descriptions)
		{
			int parent = desc[0], node = desc[1], is_left = desc[2];

			TreeNode *parent_node;
			if (nodes.find(parent) == nodes.end())
			{
				parent_node = new TreeNode(parent);
				nodes[parent] = parent_node;
			}
			else
			{
				parent_node = nodes[parent];
			}

			TreeNode *cur_node;
			if (nodes.find(node) == nodes.end())
			{
				cur_node = new TreeNode(node);
				nodes[node] = cur_node;
			}
			else
			{
				cur_node = nodes[node];
			}

			parents[node] = parent_node;
			if (parents.find(parent) == parents.end())
			{
				parents[parent] = nullptr;
			}

			if (is_left)
			{
				parent_node->left = cur_node;
			}
			else
			{
				parent_node->right = cur_node;
			}
		}

		TreeNode *head = nullptr;

		for (auto &it : parents)
		{
			if (it.second == nullptr)
			{
				head = nodes[it.first];
				break;
			}
		}

		return head;
	}
};

void in_order(TreeNode *node)
{
	if (node == nullptr)
	{
		return;
	}
	cout << node->val << " ";
	in_order(node->left);
	in_order(node->right);
}

int main()
{
	vector<vector<int>> descriptions = {{1, 2, 0}, {2, 3, 0}, {3, 4, 1}, {4, 5, 0}};
	Solution s;
	TreeNode *head = s.createBinaryTree(descriptions);
	in_order(head);
	return 0;
}