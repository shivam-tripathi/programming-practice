#include <vector>
using namespace std;

/**
 * Definition for a binary tree node.
 *
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
  int pre = 0;
  int in = 0;

  TreeNode *build(
      vector<int> &preorder,
      vector<int> &inorder,
      TreeNode *left,
      TreeNode *right)
  {

    if (in < 0 || in >= inorder.size())
      return nullptr;
    if (pre < 0 || pre >= preorder.size())
      return nullptr;

    if (left && left->val == inorder[in])
    {
      in++;
      return nullptr;
    }

    if (right && right->val == inorder[in])
    {
      in++;
      return nullptr;
    }

    TreeNode *node = new TreeNode(preorder[pre++]);

    node->left = build(preorder, inorder, left, node);
    node->right = build(preorder, inorder, node, right);

    return node;
  }

public:
  TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder)
  {
    return build(preorder, inorder, nullptr, nullptr);
  }
};
