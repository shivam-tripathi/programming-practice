#include <bits/stdc++.h>
using namespace std;

typedef long long int lld;

lld solve(vector<lld> dp, int pos, vector<lld> op, priority_queue<lld, vector<lld>, std::greater<lld> > pq) {
	if (dp[pos] != -1) return dp[pos];
	lld ans = 0;
	ans += solve(dp, pos+1, op, pq);
	if (op[pos] < 0) {
		if (pq.size() > 0) {
			pq
		}
	} else {
		pq.push(op[pos]);

	}
}

int main() {
	int n;
	cin >> n;
	vector<lld> ops(n);
	for (int i = 0; i < n; i++) {
		string tmp;
		cin >> tmp;
		if (tmp[0] == '-') {
			ops[i] = -1;
		} else {
			ops[i] = stoll(tmp.substr(2));
		}
	}

	vector<lld> dp(n, -1);
	priority_queue<lld, vector<lld>, std::greater<lld> > pq;
	solve()
}