#include <bits/stdc++.h>
using namespace std;

struct Point
{
	int x, y;
};

int orientation(Point p, Point q, Point r)
{
	return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
}

int dist(Point a, Point b)
{
	return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
}

void convexHull(Point points[], int size)
{
	// There must be atleast three points to form a convex hull
	if (size < 3)
		return;

	int leftmost = 0;
	for (int i = 1; i < size; i++)
	{
		if (
				points[i].x < points[leftmost].x ||
				(points[i].x == points[leftmost].x && points[i].y < points[leftmost].y))
		{
			leftmost = i;
		}
	}

	cout << "leftmost = " << points[leftmost].x << ", " << points[leftmost].y << endl;

	int cur = leftmost;
	vector<bool> done(size);
	vector<Point> hull;

	do
	{
		int next = (cur + 1) % size;
		for (int i = 0; i < size; i++)
		{
			int oriented = orientation(points[cur], points[i], points[next]);
			if (oriented < 0)
			{
				next = i;
			}
		}

		for (int i = 0; i < size; i++)
		{
			int oriented = orientation(points[cur], points[i], points[next]);
			// collinear
			if (oriented == 0)
			{
				// collect point with maximum distance
				if (dist(points[cur], points[i]) > dist(points[cur], points[next]))
				{
					next = i;
				}

				// if not already added, add it now
				if (!done[i])
				{
					done[i] = true;
					hull.push_back(points[i]);
				}
			}
		}

		cur = next;
	} while (cur != leftmost);

	for (int i = 0; i < hull.size(); i++)
	{
		cout << "(" << hull[i].x << ", " << hull[i].y << ") -> ";
	}
	cout << endl;
}

int main()
{
	Point points[] = {{0, 3}, {2, 2}, {1, 1}, {2, 1}, {3, 0}, {0, 0}, {3, 3}};
	int size = sizeof(points) / sizeof(points[0]);
	for (int i = 0; i < size; i++)
	{
		cout << "(" << points[i].x << ", " << points[i].y << ") ";
	}
	cout << endl;

	convexHull(points, size);

	return 0;
}