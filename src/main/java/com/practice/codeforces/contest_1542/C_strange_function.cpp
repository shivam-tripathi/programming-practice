#include <bits/stdc++.h>
using namespace std;

typedef long long int lld;

lld mod = 1e9+7;

lld sum(lld num, lld a, lld b) {
	lld pairs = num / 2; // how many pairs exist
	lld ans = ((pairs % mod) * (a + b) % mod) % mod; // sum for pairs
	if (num % 2 == 0) {
		return ans;
	}
	return (ans + a) % mod; // extra elem
}

// 2*3
// 2*2*3
// 2*3*3
// 2*3*5
// 2*2*3*3

int main() {
	int t;
	cin >> t;
	while (t--) {
		lld num;
		cin >> num;
		lld ans = (sum(num, 2, 3) + sum(num/6, 1, 2)) % mod;
		cout << ans << endl;
	}
}
