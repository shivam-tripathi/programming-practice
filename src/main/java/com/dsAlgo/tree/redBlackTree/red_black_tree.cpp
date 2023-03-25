#include <bits/stdc++.h>
using namespace std;

enum t_color
{
	red,
	black
};

// Basic structure of a red black tree. Each node, apart from regular item, left and right subtrees
// also has a field called color which is either red or black. The terminating node NULL are called
// leaf nodes and are assumed to be colored black.
struct t_red_black_node
{
	t_color color;
	void *item;
	struct t_red_black_node *left, *right;
};

// Properties of red_black_tree:
// 1. Every node is either red or black.
// 2. Every leaf (NULL) is black.
// 3. If a node is red, then both it's children are black.
// 4. Every simple path from node to it's descendant leaf contains same number of black nodes.

t_red_black_node *root = nullptr;

bool is_leaf(t_red_black_node *node)
{
	return (node == nullptr);
}

t_color get_color(t_red_black_node *node)
{
	if (is_leaf(node))
	{
		return black;
	}

	return node->color;
}

int main(int argc, char **argv)
{
}
