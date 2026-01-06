#include <vector>
#include <string>
using namespace std;

/*
1202. Smallest String With Swaps
https://leetcode.com/problems/smallest-string-with-swaps/
Medium

You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] =
[a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.



Example 1:
Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"

Example 2:
Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
Output: "abcd"
Explaination:
Swap s[0] and s[3], s = "bcad"
Swap s[0] and s[2], s = "acbd"
Swap s[1] and s[2], s = "abcd"

Example 3:
Input: s = "cba", pairs = [[0,1],[1,2]]
Output: "abc"
Explaination:
Swap s[0] and s[1], s = "bca"
Swap s[1] and s[2], s = "bac"
Swap s[0] and s[1], s = "abc"

Constraints:
		1 <= s.length <= 10^5
		0 <= pairs.length <= 10^5
		0 <= pairs[i][0], pairs[i][1] < s.length
		s only contains lower case English letters.
*/

class Solution
{
public:
	void dfs(vector<vector<int>> &graph, int node, vector<bool> &visited, vector<int> &ans)
	{
		if (visited[node])
		{
			return;
		}
		visited[node] = true;
		ans.push_back(node);
		for (auto sub : graph[node])
		{
			if (!visited[sub])
			{
				dfs(graph, sub, visited, ans);
			}
		}
	}

	string smallestStringWithSwaps(string s, vector<vector<int>> &pairs)
	{
		vector<vector<int>> graph(s.size());
		for (auto edge : pairs)
		{
			graph[edge[0]].push_back(edge[1]);
			graph[edge[1]].push_back(edge[0]);
		}

		vector<bool> visited(s.size(), false);

		for (int i = 0; i < s.size(); i++)
		{
			if (!visited[i] && graph[i].size() > 0)
			{
				vector<int> connected;
				vector<char> chars;

				dfs(graph, i, visited, connected);
				sort(connected.begin(), connected.end());
				for (auto idx : connected)
					chars.push_back(s[idx]);
				sort(chars.begin(), chars.end());

				for (int i = 0; i < connected.size(); i++)
					s[connected[i]] = chars[i];
			}
		}

		return s;
	}
};