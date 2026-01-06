#pragma GCC optimize ("O3")
#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
typedef long long ll;
const int infs = 1e5+100;
const int inf = 1e9 + 100;
const ll infll = 1e18 + 500;
const ll base = 1e9 + 7;

int r, c, types;
int final_r, final_c;
bool maps[11][21][21];

struct pos {
    int x, y, dist, type;
};

void solve(int testCase) {
    queue<pos> _queue;

    int shortest = inf;
    bool visited[types][r][c];
	memset(visited, false, sizeof (visited[0][0][0]) * types * r * c);

    _queue.push((pos){0, 0, 0, 0});
    while (!_queue.empty()) {
        pos _pos = _queue.front();
        int x = _pos.x, y = _pos.y, dist = _pos.dist, type = _pos.type;

        if (x < r && x >= 0 && y < c && y >= 0 && maps[type][x][y]) {
            if (!visited[type][x][y]) {
                _queue.push((pos){ x + 1, y, dist + 1, (type + 1) % types });
                _queue.push((pos){ x - 1, y, dist + 1, (type + 1) % types });
                _queue.push((pos){ x, y + 1, dist + 1, (type + 1) % types });
                _queue.push((pos){ x, y - 1, dist + 1, (type + 1) % types });
                _queue.push((pos){ x, y, dist + 1, (type + 1) % types });
                visited[type][x][y] = true;
            }
            if (x == final_r && y == final_c) {
                shortest = min(shortest, dist);
            }
        }
        _queue.pop();
    }

    if (shortest == inf) {
        cout << "Case " << testCase << ": Luke and Leia cannot escape." << endl;
    } else {
        cout << "Case " << testCase << ": Luke and Leia can escape in " << shortest << " steps." << endl;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int testCase = 1;
    while (true) {
        cin >> r >> c >> types;
        final_r = r - 1;
        final_c = c - 1;
        if (r == 0) {
            break;
        }
        string s;
        for (int i = 0; i < types; i++) {
            for (int j = 0; j < r; j++) {
                cin >> s;
                for (int k = 0; k < c; k++) {
                    maps[i][j][k] = s[k] == '0';
                }
            }
        }
        solve(testCase++);
    }
    return 0;
}
