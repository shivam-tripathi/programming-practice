#include <vector>
#include <queue>
using namespace std;

class Solution
{
public:
	int minCostConnectPoints(vector<vector<int>> &points)
	{
		priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
		vector<bool> visited(points.size(), false);
		int done = 0, ans = 0;
		pq.push({0, 0});

		while (done < points.size())
		{
			pair<int, int> position = pq.top();
			pq.pop();
			int dist = position.first, idx = position.second;
			if (!visited[idx])
			{
				visited[idx] = true;
				done++;
				ans += dist;
				for (int i = 0; i < points.size(); i++)
				{
					if (!visited[i])
					{
						pq.push({abs(points[idx][0] - points[i][0]) + abs(points[idx][1] - points[i][1]), i});
					}
				}
			}
		}
		return ans;
	}
};