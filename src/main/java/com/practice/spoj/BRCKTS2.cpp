#pragma GCC optimize ("O3")
#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;
typedef long long ll;
const int infs = 1e5+100;
const int inf = 1e9 + 100;
const ll infll = 1e18 + 500;
const ll base = 1e9 + 7;

string s;
struct node {
    bool color;
    ll x, y, sub;
    node(bool _color, ll _x, ll _y) {
        color = _color;
        x = _x;
        y = _y;
        sub = 0;
    }
};

stack<node *> _stack;
const bool black = true;

ll solve() {
    ll blackArea = 0;

    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '(') {
            bool color = black;
            if (!_stack.empty()) {
                node *top = _stack.top();
                color = !top->color;
            }
            _stack.push(new node(color, 1, 1));
        } else {
            node *top = _stack.top();
            bool stackColor = top->color;
            ll area = top->x * top->y;
            if (stackColor == black) {
                blackArea += area - top->sub;
            }

            _stack.pop();

            if (!_stack.empty()) {
                node *prev = _stack.top();
                prev->x = prev->x + top->x + 1;
                prev->y = max(prev->y, top->y + 1);
                prev->sub += top->x * top->y;
            }
        }
    }
    return blackArea;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t--) {
        cin >> s;
        cout << solve() << endl;
    }
    return 0;
}
