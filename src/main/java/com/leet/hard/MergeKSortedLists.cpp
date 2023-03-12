#include <bits/stdc++.h>
using namespace std;

/**
 * Definition for singly-linked list.
 */

struct ListNode
{
	int val;
	ListNode *next;
	ListNode() : val(0), next(nullptr) {}
	ListNode(int x) : val(x), next(nullptr) {}
	ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution
{
public:
	ListNode *mergeKLists(vector<ListNode *> &lists)
	{
		auto cmp = [](ListNode *left, ListNode *right)
		{
			return left->val > right->val;
		};
		priority_queue<ListNode *, vector<ListNode *>, decltype(cmp)> pq(cmp);
		for (auto list : lists)
		{
			if (list)
			{
				pq.push(list);
			}
		}
		ListNode *sentinel = new ListNode(0);
		ListNode *prev = sentinel;
		while (!pq.empty())
		{
			auto node = pq.top();
			pq.pop();
			prev->next = node;
			if (node->next)
			{
				pq.push(node->next);
			}
			prev = node;
			prev->next = nullptr;
		}
		return sentinel->next;
	}
};