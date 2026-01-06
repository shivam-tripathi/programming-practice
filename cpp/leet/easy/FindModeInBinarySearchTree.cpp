#include <vector>
#include <unordered_map>
using namespace std;

/**
 * Definition for a binary tree node.
 */

struct TreeNode
{
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode() : val(0), left(nullptr), right(nullptr) {}
	TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
	TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
	unordered_map<int, int> freq;

	void findModeUtil(TreeNode *node)
	{
		if (node == nullptr)
		{
			return;
		}
		if (freq.find(node->val) == freq.end())
		{
			freq[node->val] = 0;
		}
		freq[node->val] += 1;
		findModeUtil(node->left);
		findModeUtil(node->right);
	}

	vector<int> findMode(TreeNode *root)
	{
		findModeUtil(root);
		vector<int> ans;
		int ans_freq = 0;
		for (auto i : freq)
		{
			if (ans_freq < i.second)
			{
				ans_freq = i.second;
				ans = {i.first};
			}
			else if (ans_freq == i.second)
			{
				ans.push_back(i.first);
			}
		}
		return ans;
	}
};